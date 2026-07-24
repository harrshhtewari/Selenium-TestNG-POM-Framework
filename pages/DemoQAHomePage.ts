import { Page } from '@playwright/test';

export class DemoQAHomePage {
  readonly page: Page;

  constructor(page: Page) {
    this.page = page;
  }

  async goto() {
    await this.page.goto('https://demoqa.com/');
  }

  async clickElements() {
    const elementsCard = this.page.locator('div.top-card', { hasText: 'Elements' }).first();
    await elementsCard.click();
    await this.page.waitForURL(/.*elements/);
  }
}
