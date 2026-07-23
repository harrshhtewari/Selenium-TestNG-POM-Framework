
package testcases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import base.BaseTest;
import pages.ElementsPage;
import org.testng.annotations.Test;

@SuppressWarnings("unused")
public class BrowserOpenCloseTestNGtest extends BaseTest {

    @Test(priority = 1)
    public void OpenBrowser() 
    {
        test = extent.createTest("Open Browser Test");

        test.info("Opening browser and navigating to URL");

        driver.get(URL);

        String expectedURL = "https://demoqa.com/elements";
        String expectedTitle = "demosite";

        String currentUrl = driver.getCurrentUrl();
        String title = driver.getTitle();

        test.info("Captured URL: " + currentUrl);
        test.info("Captured Title: " + title);

        assertEquals(currentUrl, expectedURL, "URLs are not matching");
        assertEquals(title, expectedTitle, "Titles are not matching");

        test.pass("Browser opened and validated successfully");
    }

    @Test(priority = 2)
    public void ClickOnElements() throws InterruptedException 
    {
        test = extent.createTest("TextBox Form Test");

        ElementsPage elementsPage = new ElementsPage(driver);

        test.info("Opening Elements Page");

        elementsPage.open();

        test.info("Executing Textbox Form");

        elementsPage.textBoxForm();

        test.pass("Textbox Form Test Passed");
    }

    @Test(priority = 3)
    public void ClickOnTextBox() throws InterruptedException
    {
        test = extent.createTest("Checkbox Test");

        ElementsPage elementsPage = new ElementsPage(driver);

        test.info("Opening Elements Page");

        elementsPage.open();

        test.info("Executing Checkbox Test");

        elementsPage.checkboxTest();

        test.pass("Checkbox Test Passed");
    }

    @Test(priority = 4)
    public void ClickOnRadioButton() throws InterruptedException
    {
        test = extent.createTest("Radio Button Test");

        ElementsPage elementsPage = new ElementsPage(driver);

        test.info("Opening Elements Page");

        elementsPage.open();

        test.info("Executing Radio Button Test");

        elementsPage.radioButtonTest();

        test.pass("Radio Button Test Passed");
    }

    @Test(priority = 5)
    public void ClickOnTheWebTables() throws InterruptedException
    {
        test = extent.createTest("Web Tables Test");

        ElementsPage elementsPage = new ElementsPage(driver);

        test.info("Opening Elements Page");

        elementsPage.open();

        test.info("Executing Web Tables Test");

        elementsPage.webTablesTest();

        test.pass("Web Tables Test Passed");
    }
}

