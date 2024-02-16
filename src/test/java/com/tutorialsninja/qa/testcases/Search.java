package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;

public class Search extends Base{
	
	public Search() {
		super();
	}
	
	WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		driver=initializeBrowserAndOpenAppURL(prop.getProperty("browserName"));
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	@Test(priority=1)
	public void verifySearchWithValidProduct() {
		
		driver.findElement(By.name("search")).sendKeys("HP");
		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
		
		Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed());
		
		
	}
	@Test(priority=2)
   public void verifySearchWithInValidProduct() {
		
		driver.findElement(By.name("search")).sendKeys("Honda");
		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
String actualSearchMeassge = driver.findElement(By.xpath("//input[@id='button-search']/following-sibling::p")).getText();		
		Assert.assertEquals(actualSearchMeassge,"There is no product that matches the search criteria.");
		
		
	}
	@Test(priority=3)
    public void verifySearchWithoutProduct() {
		
		driver.findElement(By.name("search")).sendKeys("");
		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
String actualSearchMeassge = driver.findElement(By.xpath("//input[@id='button-search']/following-sibling::p")).getText();		
		Assert.assertEquals(actualSearchMeassge,"There is no product that matches the search criteria.");
		
		
	}

}
