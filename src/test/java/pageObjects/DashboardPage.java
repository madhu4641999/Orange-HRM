package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utilities.BaseTest;

public class DashboardPage extends BaseTest {
	
	
	public DashboardPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath = "//h6[text()='Dashboard']")
	WebElement text_Dashboard;
	
	@FindBy(linkText = "PIM")
	WebElement left_panel_PIM_option;
	
	@FindBy(xpath = "//button[text()=' Add ']")
	WebElement button_Add;
	
	public boolean verify_Dashboard_exists()
	{
		waitForElementToBeVisible(text_Dashboard, 20);
		return text_Dashboard.isDisplayed();
	}
	
	public void click_left_panel_PIM_option()
	{
		left_panel_PIM_option.click();
	}

	public void click_button_Add()
	{
		Assert.assertTrue(button_Add.isEnabled());
		button_Add.click();
	}
}
