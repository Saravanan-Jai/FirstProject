package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	
	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	//email id
	@FindBy(xpath="//input[@id=\"input-email\"]")
	WebElement email;
	
	//password
	@FindBy(xpath="//input[@id=\"input-password\"]")
	WebElement psword;
	
	@FindBy(xpath="//input[@value=\"Login\"]")
	WebElement login;
	
	
	public void enterEmail(String emailid) {
		email.sendKeys(emailid);	
		}
	
	public void enterPassword(String pword) {
		psword.sendKeys(pword);	
	}
	
	public void clickLogin() {
		login.click();
	}
}
