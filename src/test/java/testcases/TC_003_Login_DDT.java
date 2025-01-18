package testCases;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import utilities.DataProviders;

public class TC_003_Login_DDT extends BaseClass {

	public TC_003_Login_DDT() {
		super();
	}

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups="DataDriven")
	public void verifyLoginDDT(String email, String pwd, String exp) {

		logger.info("Login DDT verification test started...");
		try{
			HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();

		LoginPage lp = new LoginPage(driver);
		lp.enterEmail(email);
		lp.enterPassword(pwd);
		lp.clickLogin();

		System.out.println(driver.getTitle());
		String pageTitle = driver.getTitle();

		// valid scenario testing
		if (exp.equalsIgnoreCase("Valid")) {
			if (pageTitle.equals("My Account")) {
				Assert.assertTrue(true);
				logger.info("TC_003_LoginTest...Passed");
				hp.clicklogout();
			} else {
				Assert.assertTrue(false);
				logger.info("TC_003_LoginTest...failed");
			}
		}

		// invalid scenario testing
		if (exp.equalsIgnoreCase("Invalid")) {
			if (pageTitle.equals("My Account")) {
				Assert.assertTrue(false);
				logger.info("TC_003_LoginTest...failed");
				hp.clicklogout();
			} else {
				Assert.assertTrue(true);
				logger.info("TC_002_LoginTest...Passed");
			}
		}

	}
		
		catch(Exception e) {
			Assert.assertTrue(false);
		}
		
		
	}
}