package pageObjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utilities.BaseTest;

public class PimPage extends BaseTest{

	public PimPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	String firstName, lastName = "";

	@FindBy(css = "input[placeholder='First name'], input[placeholder='First Name']")
	WebElement textbox_AddEmployee_FirstName;

	@FindBy(css = "input[placeholder='Last Name']")
	WebElement textbox_AddEmployee_LastName;

	@FindBy(xpath = "//label[normalize-space()='Employee Id']/parent::div/following-sibling::div/input")
	WebElement textbox_AddEmployee_EmployeeId;

	@FindBy(xpath = "//button[normalize-space()='Cancel']")
	WebElement button_AddEmployee_Cancel;

	@FindBy(xpath = "//button[normalize-space()='Save']")
	WebElement button_AddEmployee_Save;

	@FindBy(xpath = "(//input[@placeholder='Type for hints...'])[1]")
	WebElement textbox_EmployeeInformation_EmployeeName;

	@FindBy(xpath = "//label[text()='Employee Id']/parent::div/following-sibling::div/input")
	WebElement textbox_EmployeeInformation_EmployeeId;

	@FindBy(css = "button[type='submit']")
	WebElement button_EmployeeInformation_Submit;

	@FindBy(xpath = "//div[@class='orangehrm-edit-employee-imagesection']")
	WebElement element_employee_Edit_Details_Section_Opened;

	@FindBy(xpath = "//div[@class='oxd-table-card']")
	List<WebElement> rows;
	
	By table_Id_value = By.xpath(".//div[@class='oxd-table-cell oxd-padding-cell'][2]");
	By table_checkbox = By.xpath(".//div[@class='oxd-table-cell oxd-padding-cell'][1]");
	
	

	@FindBy(xpath = "//div[@role='columnheader']")
	List<WebElement> cols;

	@FindBy(xpath = "//div[@class='oxd-table-cell oxd-padding-cell']")
	List<WebElement> cell;

	@FindBy(xpath = "//i[@class='oxd-icon bi-chevron-right']")
	WebElement table_Next_button;

	@FindBy(xpath = "//button[normalize-space()='Delete Selected']")
	WebElement button_Delete_Selected;
	
	@FindBy(xpath = "//p[@class='oxd-text oxd-text--p oxd-text--card-body']")
	WebElement text_AreYouSurePopup_para;
	
	@FindBy(xpath = "//button[normalize-space()='Yes, Delete']")
	WebElement button_AreYouSurePopup_YesDelete;
	
	@FindBy(xpath = "//p[@class='oxd-text oxd-text--p oxd-text--toast-message oxd-toast-content-text']")
	WebElement toaster_Message;




	public void send_textbox_AddEmployee_FirstName(String firstname)
	{
		textbox_AddEmployee_FirstName.sendKeys(firstname);
	}

	public void send_textbox_AddEmployee_LastName(String lastName) throws InterruptedException
	{
		textbox_AddEmployee_LastName.sendKeys(lastName);
	}

	public String buffer_textbox_AddEmployee_EmployeeId(String id) throws InterruptedException
	{
		textbox_AddEmployee_EmployeeId.sendKeys(Keys.CONTROL+"A"+Keys.DECIMAL);
		textbox_AddEmployee_EmployeeId.sendKeys(id);
		System.out.println("Employee id is "+textbox_AddEmployee_EmployeeId.getAttribute("value") );
		return textbox_AddEmployee_EmployeeId.getAttribute("value");

	}

	public void click_button_AddEmployee_Save() throws InterruptedException
	{
		button_AddEmployee_Save.click();
	}

	public boolean verify_Employee_Edit_Details_Section_Opened()
	{
		return element_employee_Edit_Details_Section_Opened.isDisplayed() ;
	}

	public void send_textbox_EmployeeInformation_EmployeeName(String name)
	{
		textbox_EmployeeInformation_EmployeeName.sendKeys(name);
	}

	public void send_textbox_EmployeeInformation_EmployeeId(String id)
	{
		textbox_EmployeeInformation_EmployeeId.sendKeys(id);
	}

	public void click_button_EmployeeInformation_Submit() throws InterruptedException
	{
		Thread.sleep(1000);
		javascriptClick(button_EmployeeInformation_Submit);
	}

	public void findElementInTheTable(String id)
	{
		int count=0;
		int flag = 1;
		while(table_Next_button.isDisplayed())
		{
			System.out.println("rows size is "+rows.size());
			for(WebElement ele : rows)
			{
				System.out.println("id is "+ele.findElement(table_Id_value).getText());
				if(ele.findElement(table_Id_value).getText().equals(id))
				{
					count++;
					javascriptScroll(ele.findElement(table_checkbox));
					ele.findElement(table_checkbox).click();
					flag = 0;
					break;
				}
			}
			if(flag == 0)
				break;
			table_Next_button.click();
		}
		if(count==0)
			Assert.fail();
	}
	
	public void click_button_Delete_Selected()
	{
		javascriptScroll(button_Delete_Selected);
		button_Delete_Selected.click();
	}

	public String text_AreYouSurePopup_para()
	{
		return text_AreYouSurePopup_para.getText();
	}
	
	public void click_button_AreYouSurePopup_YesDelete() {
		button_AreYouSurePopup_YesDelete.click();
	}
	
	public String get_toaster_Message() {
		return toaster_Message.getText();
	}
}
