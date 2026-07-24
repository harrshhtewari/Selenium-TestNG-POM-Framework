import { Page, expect } from '@playwright/test';

export class TextBoxPage {
  readonly page: Page;
  readonly nameInput = '#userName';
  readonly emailInput = '#userEmail';
  readonly currentAddressInput = '#currentAddress';
  readonly permanentAddressInput = '#permanentAddress';
  readonly submitButton = '#submit';
  readonly outputPanel = '#output';

  constructor(page: Page) {
    this.page = page;
  }

  async fillForm(name: string, email: string, currentAddress: string, permanentAddress: string) {
    await this.page.fill(this.nameInput, name);
    await this.page.fill(this.emailInput, email);
    await this.page.fill(this.currentAddressInput, currentAddress);
    await this.page.fill(this.permanentAddressInput, permanentAddress);
  }

  async submit() {
    await this.page.locator(this.submitButton).scrollIntoViewIfNeeded();
    await this.page.locator(this.submitButton).click();
  }

  async waitForSuccess() {
    const output = this.page.locator(this.outputPanel);
    await output.waitFor({ state: 'visible', timeout: 10000 });
    await expect(output).toContainText('Name:');
    await expect(output).toContainText('Email:');
  }
}
