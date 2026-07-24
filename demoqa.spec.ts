import { test, expect } from '@playwright/test';
import path from 'path';

test('demoqa text box form submission', async ({ page }) => {
  await page.goto('https://demoqa.com/');

  // Navigate to Elements section.
  const elementsCard = page.locator('div.top-card', { hasText: 'Elements' }).first();
  await elementsCard.click();
  await page.waitForURL(/.*elements/);
  await page.waitForTimeout(2000);

  // Open Text Box page.
  await page.getByText('Text Box').click();
  await page.waitForURL(/.*text-box/);
  await page.waitForTimeout(2000);

  // Fill in the form fields.
  await page.fill('#userName', 'HARSH TEWARI');
  await page.fill('#userEmail', 'harsh.tiwaari@gmail.com');
  await page.fill('#currentAddress', 'Mohali, Punjab (India)');
  await page.fill('#permanentAddress', 'Lucknow,Uttar Pradesh(India)');

  await page.waitForTimeout(3000);

  // Submit the form.
  await page.locator('#submit').scrollIntoViewIfNeeded();
  await page.locator('#submit').click();

  // Validate the success message / output.
  const output = page.locator('#output');
  await output.waitFor({ state: 'visible', timeout: 10000 });
  await expect(output).toContainText('Name:HARSH TEWARI');
  await expect(output).toContainText('Mohali, Punjab (India)');
  await expect(output).toContainText('Lucknow,Uttar Pradesh(India)');

  await page.waitForTimeout(2000);
});

test('demoqa practice form submission', async ({ page }) => {
  const sampleFilePath = path.join(process.cwd(), 'tests', 'resources', 'sample.docx');

  await page.goto('https://demoqa.com/');

  const acceptButtons = page.locator('button', { hasText: /accept|agree|ok|yes|continue/i });
  if (await acceptButtons.count()) {
    for (let i = 0; i < await acceptButtons.count(); i++) {
      const button = acceptButtons.nth(i);
      if (await button.isVisible()) {
        await button.click().catch(() => {});
      }
    }
  }

  const formsCard = page.locator('div.top-card', { hasText: 'Forms' }).first();
  await formsCard.click();
  await page.waitForURL(/.*forms/);

  await page.getByText('Practice Form').click();
  await page.waitForURL(/.*automation-practice-form/);

  await page.fill('#firstName', 'Harsh');
  await page.fill('#lastName', 'R Tewari');
  await page.fill('#userEmail', 'harsh.tiwaari@gmail.com');
  await page.locator('label[for="gender-radio-1"]').click();
  await page.fill('#userNumber', '9125255975');

  await page.click('#dateOfBirthInput');
  await page.locator('.react-datepicker__month-select').selectOption({ label: 'July' });
  await page.locator('.react-datepicker__year-select').selectOption({ label: '2026' });
  await page.click('.react-datepicker__day--015:not(.react-datepicker__day--outside-month)');

  await page.fill('#subjectsInput', 'Maths');
  await page.keyboard.press('Enter');

  const financeHobby = page.locator('label', { hasText: 'Finance Market' });
  if (await financeHobby.count()) {
    await financeHobby.click();
  } else {
    const fallbackHobby = page.locator('label', { hasText: /Music|Reading|Sports/i }).first();
    if (await fallbackHobby.count()) await fallbackHobby.click();
  }

  await page.setInputFiles('#uploadPicture', sampleFilePath);
  await page.fill('#currentAddress', 'Mohali');

  await page.locator('#state').click();
  const stateOption = page.locator('div[id^="react-select-"][id*="-option"]', { hasText: 'Punjab' });
  if (await stateOption.count()) {
    await stateOption.first().click();
  } else {
    await page.locator('div[id^="react-select-"][id*="-option"]').first().click();
  }

  await page.locator('#city').click();
  const cityOption = page.locator('div[id^="react-select-"][id*="-option"]', { hasText: 'Mohali' });
  if (await cityOption.count()) {
    await cityOption.first().click();
  } else {
    await page.locator('div[id^="react-select-"][id*="-option"]').first().click();
  }

  const submitButton = page.getByRole('button', { name: 'Submit' });
  await submitButton.scrollIntoViewIfNeeded();
  await submitButton.click();

  const modal = page.locator('.modal-content');
  await expect(modal).toBeVisible({ timeout: 10000 });
  await expect(modal).toContainText('Harsh R Tewari');
});
