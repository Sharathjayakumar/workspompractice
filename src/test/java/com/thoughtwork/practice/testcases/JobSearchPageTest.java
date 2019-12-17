package com.thoughtwork.practice.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.thoughtworks.practice.base.BaseClass;
import com.thoughtworks.practice.pages.HomePage;
import com.thoughtworks.practice.pages.JobSearchPage;

public class JobSearchPageTest extends BaseClass {

	HomePage homepage;
	JobSearchPage jobSearch;
	
	@BeforeMethod
	public void setUp() throws Throwable {
		initializeDriver();
		homepage = new HomePage();
		homepage.clickOnCareerTab();
		homepage.clickOnSearchJobsLink();
		
		jobSearch = new JobSearchPage();
	}
	
	
	@Test(priority=1)
	public void jobSearchPageTitle() {
		String title = jobSearch.getSearchPageTitle();
		Assert.assertEquals(title, "Browse Jobs | ThoughtWorks","Title is not matching");
	}
	
	@Test(priority=2)
	public void jobSearchPageURL() {
		Assert.assertEquals(jobSearch.getSearchPageCurrentURL(), "https://www.thoughtworks.com/careers/jobs");
		
	}
	
	@Test(priority=3)
	public void isJobSearchTextBoxDisplayed() {
		boolean flag = jobSearch.searchBtnIsDisplayed();
		Assert.assertEquals(flag, true);
	}
	
	@Test(priority=4)
	public void isCountrySelectorDisplayed() throws Throwable {
		Thread.sleep(2000);
		Assert.assertEquals(jobSearch.countrySelectorIsDisplayed(), true);
	}
	
	@Test(priority=5)
	public void IsCountrySelectorEnabled() throws Throwable {
		Thread.sleep(2000);
		Assert.assertEquals(jobSearch.countrySelectorIsEnabled(), true);
	}
	@Test(priority=6)
	public void verifyJobSearch() throws Throwable {
		
		jobSearch.searchByJobTitle("Quality");
		Thread.sleep(2000);
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
		
	}
	
	
	
	
	
	
	
	
	
}
