import { Page } from '@playwright/test';

export class CheckBoxPage {
  readonly page: Page;

  constructor(page: Page) {
    this.page = page;
  }

  async expandAll() {
    await this.page.getByRole('button', { name: /Expand all/i }).click();
  }

  async checkAll() {
    await this.expandAll();
    const rootCheckbox = this.page.locator('.rct-checkbox').first();
    await rootCheckbox.click();
  }
}
