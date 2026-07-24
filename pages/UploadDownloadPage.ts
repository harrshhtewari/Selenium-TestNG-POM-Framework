import { Page, expect } from '@playwright/test';

export class UploadDownloadPage {
  readonly page: Page;
  readonly uploadInput = '#uploadFile';
  readonly uploadedFilePath = '#uploadedFilePath';

  constructor(page: Page) {
    this.page = page;
  }

  async uploadFile(filePath: string) {
    await this.page.setInputFiles(this.uploadInput, filePath);
  }

  async expectUploadedFileName(fileName: string) {
    const filePathText = await this.page.locator(this.uploadedFilePath).textContent();
    await expect(filePathText).toContain(fileName);
    return filePathText;
  }
}
