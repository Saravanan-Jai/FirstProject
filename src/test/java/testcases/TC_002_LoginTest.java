package testCases;

import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;

public class TC_002_LoginTest extends BaseClass {

	public TC_002_LoginTest() {
		super();
	}

	@Test(groups={"Sanity","Master"})
	public void verifyLogin(){
		
		logger.info("Login verification test started...");
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp = new LoginPage(driver);
		lp.enterEmail(p.getProperty("email"));
		lp.enterPassword(p.getProperty("password"));
		lp.clickLogin();
		
		System.out.println(driver.getTitle());
		String pageTitle = driver.getTitle();
		
		if(pageTitle.equals("My Account")){
			assert(true);
			logger.info("TC_002_LoginTest...Passed");
		}
		else {
			assert(false);
			logger.info("TC_002_LoginTest...failed");
		}
				
			
	}

}
