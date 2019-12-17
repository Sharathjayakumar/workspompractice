package com.thoughtworks.practice.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.thoughtworks.practice.util.Utility;

public class BaseClass {

	public static WebDriver driver;
	public static Properties prop;
	public BaseClass() {

		try {
			prop = new Properties();
			FileInputStream propFile = new FileInputStream("/Users/sds-sarath.kj/Documents/eclipse-workspace/ThoughtWorksPracticeProject"
					+ "/src/main/java/com/thoughtworks/practice/config/config.properties");
			prop.load(propFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	public static void initializeDriver() throws Throwable{


		String browserName = prop.getProperty("browserName");
		if(browserName.equals("localChrome")){
			System.setProperty("webdriver.chrome.driver", "/Users/sds-sarath.kj/Documents/eclipse-workspace/ThoughtWorksPracticeProject/chromedriver");
			driver = new ChromeDriver();
		}
		else if(browserName.equals("RemoteChrome")) {

			/*MutableCapabilities sauceOptions = new MutableCapabilities();
			sauceOptions.setCapability("username", prop.getProperty("sauceUserName"));
			sauceOptions.setCapability("accesskey", prop.getProperty("sauceAccessKey"));

			ChromeOptions browserOptions = new ChromeOptions();
			browserOptions.setExperimentalOption("w3c", true);
			browserOptions.setCapability("platformName", "Windows 10");
			browserOptions.setCapability("browserVersion", "64.0");
			browserOptions.setCapability("sauce:options", sauceOptions)*/;

			//ChromeOptions chromeOpts = new ChromeOptions();
			// chromeOpts.setExperimentalOption("w3c", true);

			MutableCapabilities sauceOpts = new MutableCapabilities();

			sauceOpts.setCapability("build", "Java-W3C-Examples");
			sauceOpts.setCapability("seleniumVersion", "3.141.59");
			sauceOpts.setCapability("username", prop.getProperty("sauceUserName"));
			sauceOpts.setCapability("accesskey", prop.getProperty("sauceAccessKey"));
			// sauceOpts.setCapability("tags", "w3c-chrome-tests");

			DesiredCapabilities caps = new DesiredCapabilities();
			// caps.setCapability(ChromeOptions.CAPABILITY,  chromeOpts);
			caps.setCapability("sauce:options", sauceOpts);
			caps.setCapability("browserName", "googlechrome");
			caps.setCapability("browserVersion", "latest");
			caps.setCapability("platformName", "windows 10");

			URL sauceURL = new URL(prop.getProperty("sauceURL"));
			driver = new RemoteWebDriver(sauceURL, caps);
		}
		Thread.sleep(2000);
		//driver.manage().window().maximize();
		driver.manage().window().setSize(new Dimension(1600,900));
		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("url"));
		driver.manage().timeouts().pageLoadTimeout(Utility.pageLoadTimeOut, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Utility.implicitWait, TimeUnit.SECONDS);
	}

	public void getUrl(String URL) {
		driver.get(URL);
	}

	public static void javascriptClick(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", element);
	}
	
	public static void brokenLinksFinder() throws Throwable {

		List <WebElement> element = driver.findElements(By.tagName("a"));
		for(int i=0;i<element.size();i++) {	
			String linkURL = element.get(i).getAttribute("href");	
			//System.out.println(linkURL);
			try {
				URL url = new URL(linkURL);

				HttpURLConnection httpURLConnection =(HttpURLConnection)url.openConnection();
				httpURLConnection.setConnectTimeout(2000);
				httpURLConnection.connect();
				int responseCode = httpURLConnection.getResponseCode();
				/*if(responseCode==200)
			System.out.println(url +"=====>"+httpURLConnection.getResponseMessage());
		else*/ if(responseCode==HttpURLConnection.HTTP_NOT_FOUND)
			System.out.println(url +"=====>"+httpURLConnection.getResponseMessage());
			} catch (MalformedURLException e) {
				//e.printStackTrace();
			}	
		}
	}
}
