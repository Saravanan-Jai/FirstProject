package testCases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	protected WebDriver driver;
	public Logger logger;
	public Properties p;

	@BeforeClass(groups = { "Master", "Sanity", "Regression", "DataDriven" })
	@Parameters({ "os", "browser" })
	public void setup(String os, String br, ITestContext context) throws IOException {

		// Loading config properties file

		FileReader file = new FileReader("./src//test//resources//config.properties");
		p = new Properties();
		p.load(file);

		logger = LogManager.getLogger(this.getClass());
		
		//if remote environment

		if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {

			DesiredCapabilities capabilities = new DesiredCapabilities();
		
			//Check os name from xml and set platform name
			if (os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN10);
			}
			
			else if (os.equalsIgnoreCase("linux")) {
				capabilities.setPlatform(Platform.LINUX);
			}
			else if (os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			} else {
				System.out.println("No matching os");
				return;
			}

			// Check browser name from xml and set browser name

			switch (br.toLowerCase()) {
			case "chrome":
				capabilities.setBrowserName("chrome");
				break;
			case "edge":
				capabilities.setBrowserName("MicrosoftEdge");
				break;
			default:
				System.out.println("Invalid Browser name..");
				return;

			}
			
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
		}
		//If local environment
		else if(p.getProperty("execution_env").equalsIgnoreCase("local")) {

			logger.info("Starting the test setup...");

			switch (br.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			default:
				System.out.println("Invalid Browser name..");
				return;
			}
		}

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		System.out.println(p.getProperty("appURL"));
		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();
		driver.navigate().refresh();
		
		
		context.setAttribute("WebDriver", driver);
		
		
		logger.info("Browser and URL openened for testing...");
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.navigate().refresh();

	}

	public String randomString() {
		String generatedstring = RandomStringUtils.randomAlphabetic(5);
		return generatedstring;
	}

	@AfterClass(groups = { "Master", "Sanity", "Regression", "DataDriven" })
	public void tearDown() {
		if (driver != null) {
			driver.quit(); // Quit the WebDriver to close the browser
		}
	}

	public String captureScreen(String tname, WebDriver driver) throws IOException {

		// Get the current time using java.time.LocalDateTime
		LocalDateTime now = LocalDateTime.now();

		// Convert LocalDateTime to Date
		Date currentDate = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());

		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(currentDate);

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir") + "\\screenShots\\" + tname + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);

		sourceFile.renameTo(targetFile);
		return targetFilePath;

	}

}
