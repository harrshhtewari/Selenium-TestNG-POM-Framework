package pages;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Forms 
{
	
	private WebDriver driver;

    // collected all the locators of this page i.e. Forms
	
    private By textBoxLink = By.xpath("//span[normalize-space()='Text Box']");
    private By checkBoxLink = By.xpath("//span[normalize-space()='Check Box']");
    
    private By FirstName = By.xpath("//input[@id='firstName']");
    private By LastName = By.xpath("//input[@id='lastName']");
    private By Email = By.xpath("//input[@id='userEmail']");
    private By Gender = By.xpath("//input[@id='gender-radio-1']");
    private By Mobile  = By.xpath("//input[@id='userNumber']");
    private By DOB = By.xpath("//input[@id='dateOfBirthInput']");
    private By Subject = By.xpath("//div[@class='subjects-auto-complete__input-container css-19bb58m']");
    ////div[@class='subjects-auto-complete__input-container css-19bb58m']
    
    private By Hobbies = By.xpath("//input[@id='hobbies-checkbox-1']");
    private By Pictures = By.xpath("//input[@id='uploadPicture']");
    private By CurrentAddress = By.xpath("//textarea[@id='currentAddress']");
    private By State = By.xpath("//div[@class='css-15lsz6c-indicatorContainer']");
    private By City = By.xpath("//div[@class='css-t3ipsp-control']//div[@class='css-19bb58m']");
    private By Submit = By.xpath("//button[@id='submit']");
    
    
    public Forms(WebDriver driver)  // constructor defined and initialised
    {
        this.driver = driver;
    }

    public void open()
    {
        driver.get("https://demoqa.com/elements");
        

        
    }
    
  
    
    public void enterFirstName(String name)
    {

        //FirstName.sendKeys(name);
    	driver.findElement(FirstName).sendKeys("HARSH");
    	   	

    }

    public void enterLastName(String lname)
    {

    	driver.findElement(LastName).sendKeys("R Tewari");

    }

    public void enterEmail(String mail)
    {

    	driver.findElement(Email).sendKeys("Email");

    }

    public void selectGender(String gender)
    {

        driver.findElement(Gender).click();

    }

    public void clickSubmit()
    {

    	driver.findElement(Gender).click();

    }
    
    
    
	

}
