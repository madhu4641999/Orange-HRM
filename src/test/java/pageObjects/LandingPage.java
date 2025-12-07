package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utilities.BaseTest;

public class LandingPage extends BaseTest {
	
	public LandingPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath = "//input[@name='username']")
	WebElement textbox_username;
	
	@FindBy(css = "input[type='password']")
	WebElement textbox_password;
	
	@FindBy(css = "button[type*='submi']")
	WebElement button_Login;
	
	public void send_username(String name)
	{
		Assert.assertTrue(textbox_username.isDisplayed());
		textbox_username.sendKeys(name);
	}
	
	public void send_password(String password)
	{
		Assert.assertTrue(textbox_password.isDisplayed());
		textbox_password.sendKeys(password);
	}
	
	public void click_Login()
	{
		waitForElementToBeEnabled(button_Login, 30);
		button_Login.click();
	}
}
