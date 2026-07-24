

import { DemoQAHomePage } from './pages/DemoQAHomePage.js';
import { ElementsPage } from './pages/ElementsPage.js';
import { TextBoxPage } from './pages/TextBoxPage.js';
import { CheckBoxPage } from './pages/CheckBoxPage.js';
import { RadioButtonPage } from './pages/RadioButtonPage.js';

import { test, expect } from '@playwright/test';


test('demoqa POM interaction across TextBox, CheckBox, RadioButton', async ({ page }) => {

  const homePage = new DemoQAHomePage(page);
  const elementsPage = new ElementsPage(page);
  const textBoxPage = new TextBoxPage(page);
  const checkBoxPage = new CheckBoxPage(page);
  const radioButtonPage = new RadioButtonPage(page);

  await homePage.goto();
  await homePage.clickElements();

  // Text Box
  await elementsPage.clickTextBox();
  await textBoxPage.fillForm(
    'HARSH TEWARI',
    'harsh.tiwaari@gmail.com',
    'Mohali, Punjab (India)',
    'Lucknow, Uttar Pradesh (India)'
  );
  await textBoxPage.submit();
  await textBoxPage.waitForSuccess();

  // Check Box
  await elementsPage.clickCheckBox();
  await checkBoxPage.checkAll();

  // Radio Button
  await elementsPage.clickRadioButton();
  await radioButtonPage.chooseAllSelectable();

  await expect(page.getByText(/Impressive/i)).toBeVisible();
});
