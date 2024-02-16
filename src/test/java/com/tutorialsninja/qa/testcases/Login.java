package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.utils.Utilities;

public class Login extends Base{
	
	public Login() {
		super();
	}
	
    WebDriver driver;
    @BeforeMethod
    public void setup() {	
    	
    	driver=initializeBrowserAndOpenAppURL(prop.getProperty("browserName"));
        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.linkText("Login")).click();
    	
    }
  
	
	@Test(priority=1,enabled=true,dataProvider="validCredentialsSupplier")
	public void verifyLoginWithValidCredentials(String email, String password) {
				
    driver.findElement(By.id("input-email")).sendKeys(email);
    driver.findElement(By.id("input-password")).sendKeys(password);
    driver.findElement(By.xpath("//input[@value='Login']")).click();
    
    Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
	
	}
	
	// for one test , we are supplying data through data provider and not through prop files.
	@DataProvider(name="validCredentialsSupplier")
	public Object[][] SupplyData() {
		Object [][] data = {{"testqa2308@gmail.com","Welcome1"},{"testqa2309@gmail.com","Welcome1"},{"testqa2310@gmail.com","Welcome1"}};
	  return data;
	}
	
	@Test(priority=2)
	public void verifyLoginWithInvalidCrdentials() {
	   
	    driver.findElement(By.id("input-email")).sendKeys("testqa23089@gmail.com");
	    driver.findElement(By.id("input-password")).sendKeys("Welcome211");
	    driver.findElement(By.xpath("//input[@value='Login']")).click();
	    
	    //Warning: No match for E-Mail Address and/or Password.
	    String actualWarningMessage =driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
	    String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
	    Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected Warning message is not displayed");
	}
	
	@Test(priority=5)
	public void verifyLoginWithInvalidUsername() {
		
	    driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailwithTimeStamp());
	    driver.findElement(By.id("input-password")).sendKeys("Welcome1");
	    driver.findElement(By.xpath("//input[@value='Login']")).click();
	    
	    //Warning: No match for E-Mail Address and/or Password.
	    String actualWarningMessage =driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
	    String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
	    Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected Warning message is not displayed");
	}
	
	@Test(priority=3)
	public void verifyLoginWithInvalidPassword() {
		
	    driver.findElement(By.id("input-email")).sendKeys("testqa2308@gmail.com");
	    driver.findElement(By.id("input-password")).sendKeys("Welcome123");
	    driver.findElement(By.xpath("//input[@value='Login']")).click();
	    
	    //Warning: No match for E-Mail Address and/or Password.
	    String actualWarningMessage =driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
	    String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
	    Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected Warning message is not displayed");

	}
	
	@Test(priority=4)
	public void VerifyLoginwithoutAnyCredentials() {
	
	    driver.findElement(By.id("input-email")).sendKeys("");
	    driver.findElement(By.id("input-password")).sendKeys("");
	    driver.findElement(By.xpath("//input[@value='Login']")).click();
	    
	    //Warning: No match for E-Mail Address and/or Password.
	    String actualWarningMessage =driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
	    String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
	    Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected Warning message is not displayed");
		
	}
	
	  @AfterMethod
	    public void tearDown() {
	    	driver.quit();
	    }
	
	//This is to generate time stamp for negative scenarios
	
}
