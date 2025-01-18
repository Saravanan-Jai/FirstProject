package testCases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class firstClass {

	WebDriver driver;

	@Test
	public void firstname() throws InterruptedException {

		driver = new EdgeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		driver.get("https://tutorialsninja.com/demo/");
		driver.manage().window().maximize();
		driver.navigate().refresh();
		Thread.sleep(10000);
		driver.navigate().refresh();


		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds timeout

        try {
            // Wait until the element is Clickable
            WebElement myAccountLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='dropdown']//i[@class='fa-solid fa-user']/following-sibling::span")));
            
           // Create an Actions instance
            Actions actions = new Actions(driver);

            // Perform right-click on the element
            actions.moveToElement(myAccountLink).click().perform();
            
            System.out.println("Click performed successfully on 'My Account' link.");

            // Optional wait to ensure the dropdown is visible
            Thread.sleep(5000); // Wait for half a second

            // Now perform left-click on a menu item
            WebElement menuItem = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='https://demo.opencart.com/en-gb?route=account/register' and @class='dropdown-item']")));
            
            menuItem.click();

            // Alternative: If the above does not work, use JavaScript
            // JavascriptExecutor js = (JavascriptExecutor) driver;
            // js.executeScript("arguments[0].click();", menuItem);*/

            System.out.println("Left-click performed successfully on the menu item.");
        } catch (Exception e) {
            System.out.println("Error performing actions: " + e.getMessage());
        }
	}}
