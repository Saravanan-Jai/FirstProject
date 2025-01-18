package testCases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class TC_001_Account_Registration extends BaseClass {
	public TC_001_Account_Registration() {
		super(); // Call the parent constructor to initialize the WebDriver
	}

	@Test(groups={"Regression","Master"})
	public void verify_Account_Registration() throws InterruptedException {

		logger.info("Test Case started TC_001_Account_Registration");
		// Wait for the element to be visible
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// WebElement userIcon =
		// wait.until(ExpectedConditions.visibilityOfElementLocated(
		// By.xpath("//i[@class=\"fa fa-user\"]")));

		// Now interact with the element
		// userIcon.click(); // Perform the click action

		HomePage hp = new HomePage(driver);
		Thread.sleep(10000);
		hp.clickMyAccount();
		logger.info("Clicked on My Account button");
		hp.clickRegister();
		logger.info("Clicked on Register link");
		Thread.sleep(5000);

		AccountRegistrationPage ar = new AccountRegistrationPage(driver);
		ar.updateFirstName("firstnametext");
		ar.updateLastName("Lastnametext");
		ar.updateEmail(randomString() + "@gmail.com");
		ar.updatePassword("Sjaisingh009$");
		ar.updateCPassword("Sjaisingh009$");
		ar.telephoneNumber("994158745");
		logger.info("Account registration information filled");
		Thread.sleep(5000);
		// Create an instance of Actions
		Actions actions = new Actions(driver);

		// Scroll down using the Page Down key
		actions.sendKeys(org.openqa.selenium.Keys.PAGE_DOWN).perform();

		ar.clickPrivacyPolicy();
		ar.clickSubmit();
		Thread.sleep(5000);

		System.out.println("Current url" + driver.getCurrentUrl());
		System.out.println("Current Title" + driver.getTitle());
		logger.info("Validating expected message...");
		String Ctitle = driver.getTitle();

		if (Ctitle.equals("Your Account Has Been Created!")) {
			System.out.println("Title is equal");
			assert (true);
		} else {
			System.out.println("Title is not equal");
			assert (false);
		}

	}
}
