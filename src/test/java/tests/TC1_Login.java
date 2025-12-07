package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.DashboardPage;
import pageObjects.LandingPage;
import utilities.BaseTest;

public class TC1_Login extends BaseTest {
	
	@Test
	public void validate_Login()
	{
		log.info("ClassName is "+this.getClass());
		
		LandingPage lp = new LandingPage(driver);
		lp.send_username(properties.getProperty("username"));
		lp.send_password(properties.getProperty("password"));
		lp.click_Login();
		
		DashboardPage dp = new DashboardPage(driver);
		Assert.assertTrue(dp.verify_Dashboard_exists());
	}

}
