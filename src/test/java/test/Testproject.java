package test;

import java.io.FileNotFoundException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import page.ViewTourPackage;
import page.BookHotel;
import page.Login;
import page.Registration;
import page.Verification;

public class Testproject {
	WebDriver driver;
	@BeforeTest
	public void setup()
	{
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver=new ChromeDriver(options);	
	}
	@BeforeMethod
	public void openurl()
	{
		driver.get("https://www.thomascook.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));	
	}
	@Test(enabled=false)
	//Registration
	public void reg()
	{
		Registration obj=new Registration(driver);
		obj.click();
		
		obj.dropdown("Mr");
		obj.setregvalues("John", "Sha", "kjhgbn@gmail.com", "8254556632", "Zxcvb@345", "Zxcvb@345");
		
		obj.regbuttonclick();
	}
	@Test
	public void login() throws Exception 
	{
		//login
		Login obj=new Login(driver);
		obj.click();
		
		String xl="E:\\Karthika\\ST_Karthika\\AutomationProj\\Logindata.xlsx";
		String sheet="Sheet1";
		int rowcount=Login.getrowcount(xl, sheet);
		for(int i=1;i<=rowcount;i++)
		{
			String email=Login.getvalues(xl, sheet, i, 0);
			String password=Login.getvalues(xl, sheet, i, 1);
			
			obj.setvalues(email, password);
			obj.loginclick();	
		}
		//login validation
		obj.loginvalidation();

		//verification of title, logo, content, links
		/*Verification obv=new Verification(driver);
		obv.titleverif();
		obv.logoverif();
		obv.contentverif();
		obv.linkverif();*/
		
		//view a tour package
		ViewTourPackage obp= new ViewTourPackage(driver);
		obp.pkgselect();
		
	    //book a hotel
		BookHotel obb=new BookHotel(driver);
		obb.citysel("Cochin");
		obb.datesel("February", "20");
		obb.count();
		obb.search();
		obb.book("bright heritage");	
	}
}
