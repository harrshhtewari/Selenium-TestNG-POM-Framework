import { test, expect } from '@playwright/test';

test.describe.configure({ mode: 'serial' });

test('kfc order flow slow demo', async ({ page, context }) => {
  test.setTimeout(180000);

  // Grant geolocation permission and set a Dubai coordinate
  await context.grantPermissions(['geolocation']);
  await context.setGeolocation({ latitude: 25.197197, longitude: 55.279372 });

  await page.goto('https://uae.kfc.me/en/home', { waitUntil: 'domcontentloaded', timeout: 60000 });
  await page.waitForLoadState('load', { timeout: 60000 }).catch(() => {});
  await page.waitForTimeout(3000);

  // Click cookie accept if present
  const acceptButton = page.locator('button:has-text("Accept & Continue"), button:has-text("Accept and Continue"), button:has-text("Accept")');
  if (await acceptButton.count()) {
    await acceptButton.first().click().catch(() => {});
    await page.waitForTimeout(1500);
  }

  // Handle in-page 'Allow' buttons (use role-based locator to avoid CSS parsing issues)
  const allowButton = page.getByRole('button', { name: /allow/i }).first();
  if (await allowButton.count()) {
    await allowButton.click().catch(() => {});
    await page.waitForTimeout(1000);
  }

  // Wait for the location modal to appear on the home page. If it does not appear, open the add-address modal.
  let addAddressVisible = await page.locator('#search_location').first().isVisible().catch(() => false);
  if (!addAddressVisible) {
    addAddressVisible = await page.waitForSelector('#search_location', { state: 'visible', timeout: 12000 }).then(() => true).catch(() => false);
  }
  if (!addAddressVisible) {
    await page.goto('https://uae.kfc.me/en/home?modal=addaddress', { waitUntil: 'domcontentloaded', timeout: 60000 });
    await page.waitForLoadState('load', { timeout: 60000 }).catch(() => {});
    await page.waitForTimeout(3000);
  }

  const cityInput = page.locator('#search_location').first();
  if (await cityInput.count()) {
    await cityInput.click({ force: true }).catch(() => {});
    await cityInput.fill('Dubai Mall,UAE');
    await page.waitForTimeout(2500);

    const suggestionLocator = page.locator('[role="option"], li[role="option"], div[role="option"], button[role="option"]');
    if (await suggestionLocator.count()) {
      await page.keyboard.press('ArrowDown').catch(() => {});
      await page.waitForTimeout(1500);
      await page.keyboard.press('Enter').catch(() => {});
      await page.waitForTimeout(3000);
    } else {
      await page.keyboard.press('ArrowDown').catch(() => {});
      await page.waitForTimeout(1500);
      await page.keyboard.press('Enter').catch(() => {});
      await page.waitForTimeout(3000);
    }
  }

  const confirmLocationButton = page.getByRole('button', { name: /^Confirm(?: Location)?$/i }).first();
  if (await confirmLocationButton.count()) {
    await expect(confirmLocationButton).toBeEnabled({ timeout: 20000 });
    await confirmLocationButton.click({ force: true }).catch(() => {});
    await page.waitForTimeout(3000);
  }

  // Fill address details and enable Continue
  const buildingInput = page.locator('input[placeholder*="Building"], input[placeholder*="Building No"], input[placeholder*="Building No./Name"], input[placeholder*="Building Name"], input[name="buidling"], input[name="building"], input[id*="building"]').first();
  if (await buildingInput.count()) {
    await expect(buildingInput).toBeVisible({ timeout: 15000 });
    await buildingInput.fill('STAR LINK APTS');
    await page.waitForTimeout(500);
  }
  const flatInput = page.locator('input[placeholder*="Flat"], input[placeholder*="Flat No"], input[placeholder*="Flat No.*"], input[name="flat"], input[id*="flat"]').first();
  if (await flatInput.count()) {
    await expect(flatInput).toBeVisible({ timeout: 15000 });
    await flatInput.fill('5');
    await page.waitForTimeout(500);
  }
  const officeTag = page.getByRole('button', { name: /office/i }).first();
  if (await officeTag.count()) {
    await officeTag.click().catch(() => {});
    await page.waitForTimeout(1000);
  }
  const continueButton = page.getByRole('button', { name: /^Continue$/i }).first();
  if (await continueButton.count()) {
    await expect(continueButton).toBeEnabled({ timeout: 20000 });
    await continueButton.click({ force: true }).catch(() => {});
    await page.waitForTimeout(3000);
  }

  // Cycle order modes, leave on Delivery
  const modeButton = page.locator('button:has-text("Delivery")').first();
  if (await modeButton.count()) {
    await modeButton.click().catch(() => {});
    await page.waitForTimeout(2000);
  }

  const exploreMenu = page.getByRole('button', { name: /Explore KFC Menu/i }).first();
  if (await exploreMenu.count()) {
    await expect(exploreMenu).toBeVisible({ timeout: 10000 });
    await exploreMenu.click({ force: true }).catch(() => {});
    await page.waitForTimeout(3000);
  }

  // Exclusive Deals -> Super 30 customization and checkout
  try {
    await page.goto('https://uae.kfc.me/en/exclusive-deals/647', { waitUntil: 'domcontentloaded', timeout: 60000 });
    await page.waitForLoadState('load', { timeout: 60000 }).catch(() => {});
    await page.waitForTimeout(2000);

    const super30 = page.locator('text=Super 30, h3:has-text("Super 30"), *:has-text("Super 30")').first();
    if (await super30.count()) {
      await super30.click({ force: true }).catch(() => {});
      await page.waitForTimeout(2000);
    }

    const clickIfVisible = async (loc: any) => {
      if (await loc.count()) { await loc.first().click({ force: true }).catch(() => {}); await page.waitForTimeout(800); return true; }
      return false;
    };

    // Try to set quantity to 15 PC if option exists
    await clickIfVisible(page.locator('text=/15\\s*PC/i'));
    await clickIfVisible(page.locator('text=/15pc/i'));
    await clickIfVisible(page.locator('button:has-text("15 PC")'));

    // Select strips flavour: prefer Original, then Spicy, else pick first under Choice of Strips
    if (!(await clickIfVisible(page.locator('label:has-text("Crispy Strips Original")')))) {
      await clickIfVisible(page.locator('label:has-text("Crispy Strips Spicy")'));
    }
    if (!(await page.locator('label:has-text("Crispy Strips Original")').count()) && !(await page.locator('label:has-text("Crispy Strips Spicy")').count())) {
      const choiceSection = page.locator('h2:has-text("Choice of Strips")').first();
      if (await choiceSection.count()) {
        const firstLabel = choiceSection.locator('..').locator('label').first();
        if (await firstLabel.count()) { await firstLabel.click({ force: true }).catch(() => {}); await page.waitForTimeout(500); }
      }
    }

    // Select favorite side item (prefer Family Fries)
    if (!(await clickIfVisible(page.locator('label:has-text("Family Fries")')))) {
      if (!(await clickIfVisible(page.locator('label:has-text("Family Spicy Fries")')))) {
        const sideSection = page.locator('h2:has-text("Select Your Favorite Side Item")').first();
        if (await sideSection.count()) {
          const firstSide = sideSection.locator('..').locator('label').first();
          if (await firstSide.count()) { await firstSide.click({ force: true }).catch(() => {}); await page.waitForTimeout(500); }
        }
      }
    }

    // Click Add to cart
    const addBtn = page.getByRole('button', { name: /\+?Add to cart/i }).first();
    if (await addBtn.count()) { await addBtn.click({ force: true }).catch(() => {}); await page.waitForTimeout(2000); }
  } catch (e) {
    console.log('Exclusive deals flow failed', e);
  }

  const viewCart = page.getByRole('button', { name: /View Cart/i }).first();
  if (await viewCart.count()) {
    await expect(viewCart).toBeVisible({ timeout: 15000 });
    await viewCart.click({ force: true }).catch(() => {});
    await page.waitForTimeout(3000);
  }

  const placeOrder = page.getByRole('button', { name: /Place Order/i }).first();
  if (await placeOrder.count()) {
    await expect(placeOrder).toBeVisible({ timeout: 15000 });
    await placeOrder.click({ force: true }).catch(() => {});
    await page.waitForTimeout(3000);
  }

  await page.screenshot({ path: 'tests/kfc_order_flow_end.png', fullPage: true });
});
