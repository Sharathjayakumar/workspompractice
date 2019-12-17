package com.thoughtwork.practice.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.thoughtworks.practice.base.BaseClass;
import com.thoughtworks.practice.pages.HomePage;



public class HomePageTest extends BaseClass {

	HomePage homePage;
	/*public HomePageTest() {
		super();
	}*/
	
	@BeforeMethod
	public void setUp() throws Throwable {
		initializeDriver();
		homePage = new HomePage();
	}
	
	@Test(priority=1)
	public void homePageTitletest() {
		String homePageTitle = homePage.getHomePageTitle();
		AssertJUnit.assertEquals(homePageTitle, "ThoughtWorks: A Global Software Consultancy | ThoughtWorks");
	}
	
	@Test (priority=2)
	public void careerTabCheck() {
		boolean flag = homePage.isCareerTabDisplayed();
		AssertJUnit.assertEquals(flag, true);
	}
	
	@Test (priority=3)
	public void searchJobLinkTest() throws Throwable {
		Thread.sleep(5000);
		homePage.clickOnCareerTab();
		boolean flag = homePage.isSearchJobsLinkDisplayed();
		AssertJUnit.assertEquals(flag, true);
	}
	
	@Test (priority=4)
	public void clickCareerTabTest() throws Throwable {
		Thread.sleep(5000);
		homePage.clickOnCareerTab();
		homePage.clickOnSearchJobsLink();
	}
	
	@Test (priority=5)
	public void brokenLinksCheck() throws Throwable {
		homePage.findBrokenLinks();
	}
	

	@AfterMethod
	public void tearDown() {
		
		driver.quit();
		System.out.println("Test PASS");
	}
}