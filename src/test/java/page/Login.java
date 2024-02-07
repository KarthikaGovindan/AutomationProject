package page;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login {
	WebDriver driver;
	
	//Login web elements
	
	By login=By.id("LoginLogoutToggel");
	By logbutton=By.id("mainLogIn");
	By email=By.id("loginId");
	By password=By.id("existloginPass");
	By loginbutton=By.id("loginButton");
	
	//constructor
	public Login(WebDriver driver)
	{
		this.driver=driver;
	}
	
	//to get row count
	public static int getrowcount(String xl, String sheet) 
	{
		try
		{
		File f=new File(xl);
		FileInputStream fi=new FileInputStream(f);
		XSSFWorkbook wb=new XSSFWorkbook(fi);
		return wb.getSheet(sheet).getLastRowNum();
		}
		catch (Exception e)
		{
			return 0;
		}
	}
	public static String getvalues(String xl, String sheet, int r, int c)
	{
		try
		{
			File f=new File(xl);
			FileInputStream fi=new FileInputStream(f);
			XSSFWorkbook wb=new XSSFWorkbook(fi);
			XSSFCell cell=(XSSFCell) wb.getSheet(sheet).getRow(r).getCell(c);
			if(cell.getCellType()==CellType.STRING)
			{
				return cell.getStringCellValue();
			}
			else
			{
				return cell.getRawValue();
			}
		}
		catch (Exception e)
		{
			return "";
		}
	}
	
	public void setvalues(String emailid, String loginpassword)
	{
		driver.findElement(email).clear();;
		driver.findElement(email).sendKeys(emailid);
		driver.findElement(password).clear();
		driver.findElement(password).sendKeys(loginpassword);
	}
	
	public void click()
	{
		driver.findElement(login).click();
		driver.findElement(logbutton).click();
	}
	
	public void loginclick()
	{
		driver.findElement(loginbutton).click();
	}
	
	public void loginvalidation()
	{
		String actualurl=driver.getCurrentUrl();
		System.out.println("Actual url is:"+actualurl);
		String expectedurl="https://www.thomascook.in/";
		if(actualurl.equals(expectedurl))
		{
			System.out.println("Login successful");
		}
		else
		{
			System.out.println("Login failed");
		}
	}


}
