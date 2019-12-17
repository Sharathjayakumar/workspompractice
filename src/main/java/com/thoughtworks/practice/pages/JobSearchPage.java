package com.thoughtworks.practice.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import com.thoughtworks.practice.base.BaseClass;

public class JobSearchPage extends BaseClass {

	
	
	
	@FindBy(xpath="//input[@name='search']")
	private WebElement searchBtn;

	@FindBy(xpath="//div[@class='multi-select-container']/child::span[text()[normalize-space()='Country']]/..")
	private WebElement countrySelector;

	@FindBy(xpath="//span[text()[normalize-space()='City']]")
	private WebElement citySelector;

	@FindBy (xpath="//span[text()[normalize-space()='Role']]")
	private WebElement roleSelector;

	@FindBy (xpath="//label[text()[normalize-space()='India']]")
	private WebElement chooseIndia;

	@FindBy (xpath="//div[@class='multi-select disable-dropdown']")
	private WebElement disableCity;

	@FindBy (xpath="//select[@class='city']/..")
	private WebElement enableCity;


	public JobSearchPage () {
		PageFactory.initElements(driver, this);
	}


	public String getSearchPageTitle() {
		return driver.getTitle();
	}

	public String getSearchPageCurrentURL() {
		return driver.getCurrentUrl();
	}

	public boolean searchBtnIsDisplayed() {
		return searchBtn.isDisplayed();
	}

	public boolean countrySelectorIsDisplayed() {
		return countrySelector.isDisplayed();
	}

	public boolean countrySelectorIsEnabled() {
		return countrySelector.isEnabled();
	}
	
	public void searchByJobTitle(String title) {
		searchBtn.sendKeys(title);
		searchBtn.sendKeys(Keys.ENTER);
	}



}
