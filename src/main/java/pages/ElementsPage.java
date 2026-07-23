package pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ElementsPage 
{
    private WebDriver driver;

    // collected all the locators
    private By textBoxLink = By.xpath("//span[normalize-space()='Text Box']");
    private By checkBoxLink = By.xpath("//span[normalize-space()='Check Box']");
    private By radioButtonLink = By.xpath("//span[normalize-space()='Radio Button']");
    private By webTablesLink = By.xpath("//span[normalize-space()='Web Tables']");
    private By userName = By.id("userName");
    private By userEmail = By.id("userEmail");
    private By currentAddress = By.id("currentAddress");
    private By permanentAddress = By.id("permanentAddress");
    private By submitBtn = By.id("submit");
    private By nameField = By.id("name");  // Success verify
    private By checkboxPlus = By.xpath("//span[@class='rc-tree-switcher rc-tree-switcher_close']");
    private By desktopCheckbox = By.xpath("//span[@aria-label='Select Desktop']");
    private By successDesktop = By.xpath("//span[normalize-space()='desktop']");
    private By successNotes = By.xpath("//span[normalize-space()='notes']");
    private By successCommands = By.xpath("//span[normalize-space()='commands']");
    private By radioYes = By.id("yesRadio");
    private By radioMsg = By.className("mt-3");
    private By addRecordBtn = By.id("addNewRecordButton");
    private By regFormModal = By.id("registration-form-modal");
    private By firstName = By.id("firstName");
    

    public ElementsPage(WebDriver driver)  // constructor defined and initialised
    {
        this.driver = driver;
    }

    public void open()
    {
        driver.get("https://demoqa.com/elements");
    }

    public void textBoxForm() throws InterruptedException 
    {
        clickElement(textBoxLink);
        driver.findElement(userName).sendKeys("HARSH R TEWARI");
        driver.findElement(userEmail).sendKeys("harshrtewwari@hotmail.com");
        driver.findElement(currentAddress).sendKeys("MOHALI (PUNJAB)");
        driver.findElement(permanentAddress).sendKeys("LUCKNOW , U.P.");
        WebElement btn = driver.findElement(submitBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);
        Thread.sleep(5000);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        Thread.sleep(5000);
        assertTrue(driver.findElement(nameField).isDisplayed(), "Form submission failed!");
        System.out.println("Form submitted successfully!");
    }

    public void checkboxTest() throws InterruptedException
    {
        clickElement(checkBoxLink);
        clickElement(checkboxPlus);
        Thread.sleep(3000);
        clickElement(desktopCheckbox);
        Thread.sleep(1000);
        // Your concat assert for "desktop notes commands"
        String msg1 = driver.findElement(successDesktop).getText();
        String msg2 = driver.findElement(successNotes).getText();
        String msg3 = driver.findElement(successCommands).getText();
        String finalMsg = msg1 + " " + msg2 + " " + msg3;
        assertEquals(finalMsg, "desktop notes commands", "Checkbox messages mismatch");
        // Add documents, downloads clicks/asserts
    }

    public void radioButtonTest() throws InterruptedException 
    {
        clickElement(radioButtonLink);
        driver.findElement(radioYes).click();
        Thread.sleep(2000);
        String actualMsg = driver.findElement(radioMsg).getText();
        assertEquals(actualMsg, "You have selected Yes", "Radio message mismatch");
    }

    public void webTablesTest() throws InterruptedException 
    {
        clickElement(webTablesLink);
        Thread.sleep(5000);
        driver.findElement(addRecordBtn).click();
        String modalText = driver.findElement(regFormModal).getText();
        assertEquals(modalText, "Registration Form", "Modal mismatch");
        // Fill form fields and submit as in your code
    }

    private void clickElement(By locator)
    {
        driver.findElement(locator).click();
    }
}
