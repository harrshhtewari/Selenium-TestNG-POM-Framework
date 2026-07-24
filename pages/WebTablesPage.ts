import { Page, expect } from '@playwright/test';

export class WebTablesPage {
  readonly page: Page;
  readonly addButton = '#addNewRecordButton';
  readonly firstNameInput = '#firstName';
  readonly lastNameInput = '#lastName';
  readonly emailInput = '#userEmail';
  readonly ageInput = '#age';
  readonly salaryInput = '#salary';
  readonly departmentInput = '#department';
  readonly submitButton = '#submit';
  readonly tableRows = '.rt-tbody .rt-tr-group';

  constructor(page: Page) {
    this.page = page;
  }

  async clickWebTables() {
    await this.page.getByText('Web Tables').click();
    await this.page.waitForURL(/.*webtables/);
  }

  async addRecord(record: {
    firstName: string;
    lastName: string;
    email: string;
    age: string;
    salary: string;
    department: string;
  }) {
    await this.page.locator(this.addButton).click();
    await this.page.fill(this.firstNameInput, record.firstName);
    await this.page.fill(this.lastNameInput, record.lastName);
    await this.page.fill(this.emailInput, record.email);
    await this.page.fill(this.ageInput, record.age);
    await this.page.fill(this.salaryInput, record.salary);
    await this.page.fill(this.departmentInput, record.department);
    await this.page.locator(this.submitButton).click();
  }

  async expectRecordPresent(record: { firstName: string; lastName: string; email: string; age: string; salary: string; department: string; }) {
    const expectedText = `${record.firstName} ${record.lastName}`;
    await expect(this.page.locator(this.tableRows)).toContainText(expectedText);
    await expect(this.page.locator(this.tableRows)).toContainText(record.email);
    await expect(this.page.locator(this.tableRows)).toContainText(record.salary);
    await expect(this.page.locator(this.tableRows)).toContainText(record.department);
  }
}
