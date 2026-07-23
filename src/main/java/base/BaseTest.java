


//////////////////////////////////////////////////////////////////
/// 
package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;// Extent Report import
import com.aventstack.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ExtentManager;
import utils.ScreenshotUtil;

public class BaseTest 
{
    protected WebDriver driver; // so it can be accessible to the child class(s)
    protected WebDriverWait wait; // same as above
    protected final String URL = "https://demoqa.com/elements"; // string is made immutable usimng final

    protected static ExtentReports extent;
    protected ExtentTest test;

    // START REPORT
    @BeforeSuite
    public void startReport()
    {
        extent = ExtentManager.getInstance();
    }

    
    @Parameters("browser")  // making the chrome or edge or firefox useable 
    
    @BeforeMethod
    public void setup(@Optional("chrome") String browser)
    {
        if(browser.equalsIgnoreCase("chrome"))    // for CHROME 
        {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            driver = new ChromeDriver(options);
        }

        else if(browser.equalsIgnoreCase("edge"))   // for EDGE 
        {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }

        else if(browser.equalsIgnoreCase("firefox")) // FOR firefox
        {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));// Explicit wait
    }

    // TEST RESULT CAPTURE
    
    @AfterMethod
    public void BrowserTeardown(ITestResult result)
    {
                
        if(result.getStatus() == ITestResult.FAILURE)
        {
            // Capture Screenshot
            String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getName());

            // Log Failure
           test.fail(result.getThrowable());
           
    	   
           
           
           
           

            // Attach Screenshot to Extent Report
            try
            {
                test.addScreenCaptureFromPath(screenshotPath);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        
        
        
        

        else if(result.getStatus() == ITestResult.SUCCESS)
        {
            test.pass("Test Passed");
        }

        else if(result.getStatus() == ITestResult.SKIP)
        {
            test.skip("Test Skipped");
        }

        if(driver != null)
        {
            driver.quit();
        }
    }

    // END REPORT
    @AfterSuite
    public void endReport()
    {
        extent.flush();
    }
}













