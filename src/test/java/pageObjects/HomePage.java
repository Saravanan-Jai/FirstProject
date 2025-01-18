package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	
	
	public HomePage(WebDriver driver) 
	{
		
		super(driver);
	}
	//MyAccount xpath
	@FindBy(xpath="//i[@class=\"fa fa-user\"]")
	WebElement lnkMyAccount;
	
	//Register xpath
	@FindBy(xpath="//a[text()='Register']")
	WebElement lnkRegister;
	
	@FindBy(xpath="//a[normalize-space()=\"Login\"]")
	WebElement login;
	
	@FindBy(xpath="//a[@class=\"list-group-item\"][normalize-space()=\"Logout\"]")
	WebElement logout;
	
	
	
	public void clickMyAccount() {
		lnkMyAccount.click();	
		}
	
	public void clickRegister() {
	lnkRegister.click();	
	}
	
	public void clickLogin() {
		login.click();
	}
	
	public void clicklogout() {
		logout.click();
	}
}

