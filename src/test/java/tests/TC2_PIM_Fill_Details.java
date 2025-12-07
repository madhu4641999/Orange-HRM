package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.DashboardPage;
import pageObjects.LandingPage;
import pageObjects.PimPage;
import utilities.BaseTest;

public class TC2_PIM_Fill_Details extends BaseTest {


	@Test
	public void fill_PIM_Details() throws InterruptedException
	{
		log.info("ClassName is "+this.getClass());
		
		LandingPage lp = new LandingPage(driver);
		lp.send_username(properties.getProperty("username"));
		lp.send_password(properties.getProperty("password"));
		lp.click_Login();

		DashboardPage dp = new DashboardPage(driver);
		Assert.assertTrue(dp.verify_Dashboard_exists());
		dp.click_left_panel_PIM_option();
		dp.click_button_Add();

		PimPage pp = new PimPage(driver);
		pp.send_textbox_AddEmployee_FirstName("Madhu");
		pp.send_textbox_AddEmployee_LastName("Palla");
		String empid=pp.buffer_textbox_AddEmployee_EmployeeId(randomNumericDataGenerator(10));
		pp.click_button_AddEmployee_Save();
		System.out.println("employee added toaster message "+pp.get_toaster_Message());
		Assert.assertTrue(pp.verify_Employee_Edit_Details_Section_Opened());

		dp.click_left_panel_PIM_option();
		pp.findElementInTheTable(empid);
		pp.click_button_Delete_Selected();
		Assert.assertEquals(pp.text_AreYouSurePopup_para(), "The selected record will be permanently deleted. Are you sure you want to continue?");
		pp.click_button_AreYouSurePopup_YesDelete();
		Assert.assertTrue(pp.get_toaster_Message().contains("Successfully Deleted"));
	}

}

