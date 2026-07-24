import { Page } from '@playwright/test';

export class RadioButtonPage {
  readonly page: Page;

  constructor(page: Page) {
    this.page = page;
  }

  async selectYes() {
    await this.page.getByText('Yes').click();
  }

  async selectImpressive() {
    await this.page.getByText('Impressive').click();
  }

  async chooseAllSelectable() {
    await this.selectYes();
    await this.page.waitForTimeout(1000);
    await this.selectImpressive();
  }
}
