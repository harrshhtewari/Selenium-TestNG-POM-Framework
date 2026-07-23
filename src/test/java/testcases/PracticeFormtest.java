package testcases;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.ElementsPage;

//public class PracticeForm {
	
	public class PracticeFormtest extends BaseTest 
	{
		
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
		
		
		
	}



