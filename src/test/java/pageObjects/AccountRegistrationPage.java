package pageObjects;

import java.time.Duration;

import javax.xml.xpath.XPath;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountRegistrationPage extends BasePage {
	
	public AccountRegistrationPage(WebDriver driver) {
		super(driver);	}
	
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement firstName;

	@FindBy(xpath="//input[@id=\"input-lastname\"]")
	WebElement lastName;
	
	@FindBy(xpath="//input[@id=\"input-email\"]")
	WebElement email;
	
	@FindBy(xpath="//input[@id=\"input-telephone\"]")
	WebElement tele;
	

	@FindBy(xpath="//input[@id=\"input-password\"]")
	WebElement password;
	
	
	@FindBy(xpath="//input[@id=\"input-confirm\"]")
	WebElement cpassword;
	
	@FindBy(xpath="//input[@type='checkbox' and @name='agree']")
	WebElement ppolicy;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement submit;
	
	@FindBy(xpath="//*[contains(text(),'Your Account Has Been Created!')]")
	WebElement cmessage;
	
		

	public void updateFirstName(String fname) {
		firstName.sendKeys(fname);
	
	}
	public void updateLastName(String lname) {
		lastName.sendKeys(lname);
	}
	
	public void updateEmail(String emailId) {
		email.sendKeys(emailId);
		
	}
	
	public void telephoneNumber(String telephone) {
		tele.sendKeys(telephone);
	}

	public void updatePassword(String psword) {
		password.sendKeys(psword);
		
	}
	public void updateCPassword(String cpsword) {
		cpassword.sendKeys(cpsword);
		
	}
	
	public void clickPrivacyPolicy() {
			
			 // Wait until the element is Clickable
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds timeout

            WebElement ppolicy = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='checkbox' and @name='agree']")));
			ppolicy.click();
		}
	
	public void clickSubmit() {
		submit.click();

	}
	
	
	public String getConfirmationmsg() {
		try {
			return (cmessage.getText());
			} catch (Exception e) {
				return(e.getMessage());
		}
		
	}
}