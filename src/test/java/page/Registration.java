package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Registration {
	WebDriver driver;
	
	//Registration web elements
	
	By login=By.id("LoginLogoutToggel");
	By register=By.xpath("//*[@id=\"newUserId\"]/p/a");
	By title=By.name("title");
	By fname=By.id("registerFName");
	By lname=By.id("registerLName");
	By email=By.id("registerEmailId");
	By mobile=By.id("registerMobileNo");
	By paswrd=By.id("registerPwd");
	By conpaswrd=By.id("registerConfirmPwd");
	By regbutton=By.id("registerButton");
	
	
	//Constructor
	public Registration(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void setregvalues(String firstname, String lastname, String emailid, String mobileno, String password, String confpassword)
	{
		driver.findElement(fname).sendKeys(firstname);
		driver.findElement(lname).sendKeys(lastname);
		driver.findElement(email).sendKeys(emailid);
		driver.findElement(mobile).sendKeys(mobileno);
		driver.findElement(paswrd).sendKeys(password);
		driver.findElement(conpaswrd).sendKeys(confpassword);
	}
	
	public void click()
	{
		driver.findElement(login).click();
		driver.findElement(register).click();
	}
	
	public void dropdown(String titl)
	{
		Select t=new Select(driver.findElement(title));
		t.selectByValue(titl);
	}
	
	public void regbuttonclick()
	{
		driver.findElement(regbutton).click();
	}
}
