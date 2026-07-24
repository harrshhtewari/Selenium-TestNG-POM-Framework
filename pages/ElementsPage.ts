import { Page } from '@playwright/test';

export class ElementsPage {
  readonly page: Page;

  constructor(page: Page) {
    this.page = page;
  }

  async clickTextBox() {
    await this.page.getByText('Text Box').click();
    await this.page.waitForURL(/.*text-box/);
  }

  async clickCheckBox() {
    await this.page.getByText('Check Box').click();
    await this.page.waitForURL(/.*checkbox/);
  }

  async clickRadioButton() {
    await this.page.getByText('Radio Button').click();
    await this.page.waitForURL(/.*radio-button/);
  }

  async clickWebTables() {
    await this.page.getByText('Web Tables').click();
    await this.page.waitForURL(/.*webtables/);
  }

  async clickUploadAndDownload() {
    await this.page.getByText('Upload and Download').click();
    await this.page.waitForURL(/.*upload-download/);
  }
}
