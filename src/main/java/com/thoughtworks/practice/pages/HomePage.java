package com.thoughtworks.practice.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.thoughtworks.practice.base.BaseClass;

public class HomePage extends BaseClass{

	//PageFactory

	@FindBy(xpath="//button[@class='menu-item__link non-js-hide careers']/child::*[contains(text(),'Careers')]")
	private WebElement careerTab;

	@FindBy(xpath="//a[text()[normalize-space()='Search jobs']]")
	private WebElement searchJobsLink;

	//Initializing the page objects
	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	//Actions or Methods

	public String getHomePageTitle() {
		return driver.getTitle();
	}

	public String getHomePageCurrentURL() {
		return driver.getCurrentUrl();
	}
	

	public boolean isCareerTabDisplayed() {
		return careerTab.isDisplayed();
	}

	public boolean isSearchJobsLinkDisplayed() {
		return searchJobsLink.isDisplayed();
	}

	public void clickOnCareerTab() {
		//careerTab.click();
		javascriptClick(careerTab);
	}

	public JobSearchPage clickOnSearchJobsLink() {
		//searchJobsLink.click();
		javascriptClick(searchJobsLink);
		return new JobSearchPage();
	}

	public void findBrokenLinks() throws Throwable {
		brokenLinksFinder();
	}

}
