package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.utils.Utilities;


public class Register extends Base {
	
	public Register() {
		super();
	}
	
	WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		driver=initializeBrowserAndOpenAppURL(prop.getProperty("browserName"));
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		
	}
	
	
	@Test (priority=1)
	public void verifyRegisteringAccount() {
		
		
		driver.findElement(By.id("input-firstname")).sendKeys("Prathap");
		driver.findElement(By.id("input-lastname")).sendKeys("Ram");
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailwithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.id("input-confirm")).sendKeys("12345");
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String ActualSuccessHeading=driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		Assert.assertEquals(ActualSuccessHeading, "Your Account Has Been Created!","Account success heading is not displayed");
		
		
	}
	@Test(priority=2)
	public void verifyRegisteringByProvidingAllFields() {
	    
		driver.findElement(By.id("input-firstname")).sendKeys("Prathap");
		driver.findElement(By.id("input-lastname")).sendKeys("Ram");
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailwithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.id("input-confirm")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='0']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String ActualSuccessHeading=driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		Assert.assertEquals(ActualSuccessHeading, "Your Account Has Been Created!","Account success heading is not displayed");
		
		
	}
	@Test(priority=3)
	public void verifyRegisteringAccountWithExistingPassword() {
		
	   
		driver.findElement(By.id("input-firstname")).sendKeys("Prathap");
		driver.findElement(By.id("input-lastname")).sendKeys("Ram");
		driver.findElement(By.id("input-email")).sendKeys("testqa2308@gmail.com");
		driver.findElement(By.id("input-telephone")).sendKeys("9003056229");
		driver.findElement(By.id("input-password")).sendKeys("Welcome1");
		driver.findElement(By.id("input-confirm")).sendKeys("Welcome1");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='0']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String warningMessage=driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		Assert.assertEquals(warningMessage, "Warning: E-Mail Address is already registered!","Warning got failed");
	}
	
	@Test(priority=4)
	
	public void verifyRegisteringWithoutUserdetails() {
	    
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String actualPrivacyWarning=driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		Assert.assertTrue(actualPrivacyWarning.contains("Warning: You must agree to the Privacy Policy!"));
		
		String actualFirstName=driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText();
		Assert.assertTrue(actualFirstName.contains("First Name must be between 1 and 32 characters!"));
		
		String actualLastName=driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText();
		Assert.assertEquals(actualLastName,"Last Name must be between 1 and 32 characters!");
		
		String actualEmailAddress=driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText();
		Assert.assertEquals(actualEmailAddress,"E-Mail Address does not appear to be valid!");
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	

}
