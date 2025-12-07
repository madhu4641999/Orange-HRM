package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;

public class BaseTest {
	
	public WebDriver driver;
	public Properties properties;
	public Logger log;
	public WebDriverWait wait;
	
	
	@BeforeTest
	public void setup() throws IOException
	{
		log = LogManager.getLogger(this.getClass());
		properties = new Properties();
		FileInputStream fis = new FileInputStream(".//src//test//resources//config.properties");
		properties.load(fis);
		String browserName = properties.getProperty("browser");
		switch(browserName)
		{
		case "chrome" : 
			driver = new ChromeDriver();
			break;
		case "edge" :
			driver = new EdgeDriver();
			break;
		default:
			log.info("Wrong browser. Check your browser name");
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(properties.getProperty("url"));
	}
	
	@AfterClass
	public void tearDown()
	{
//		driver.close();
		System.out.println("test case successful");
	}
	
	public void waitForElementToBeEnabled(WebElement locator,int seconds)
	{
	    wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	public void waitForElementToBeVisible(WebElement locator, int seconds)
	{
		wait = new WebDriverWait(driver,Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.visibilityOf(locator));
	}
	
	public void javascriptClick(WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", element);
	}
	
	public void javascriptScroll(WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);
	}
	
	public String randomNumericDataGenerator(int length)
	{
		String randomNumber = RandomStringUtils.randomNumeric(length);
		return randomNumber;
	}

}
