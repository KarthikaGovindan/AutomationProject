package page;

import java.io.File;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;

public class BookHotel {
	WebDriver driver;
	By hotel= By.xpath("//*[@id=\"bs-navbar\"]/ul/li[3]/a");
	By cityname=By.id("find-hotel");
	By cochin=By.xpath("//*[@id=\"hotelAuto\"]/div/ul/li/a");
	By datefield=By.id("check-in");
	By month=By.xpath("//*[@id=\"ui-datepicker-div\"]/div[1]/div/div/span[1]");
	By monthsel=By.xpath("//*[@id=\"ui-datepicker-div\"]/div[1]/div/a");
	By date=By.xpath("/html/body/div[9]/div[1]/table/tbody/tr/td/a");
	By countdropdown=By.id("travellerDetail");
	By childcount=By.xpath("//*[@id=\"flight-search\"]/div/div[4]/div/div/div/div[1]/div/div/div[2]/div[2]/button[2]");
	By donebutton=By.xpath("//*[@id=\"flight-search\"]/div/div[4]/div/div/div/div[2]/button[2]");
	By search=By.id("search-button");
	By hotelsearch=By.id("findhotelName");
	By hotelbook=By.xpath("/html/body/div[3]/div[2]/div[2]/div/div[2]/div[4]/div[1]/div[1]/div/div[3]/div/button");
	By bookb=By.xpath("//*[@id=\"hotelPdpSidebar\"]/div/div[6]/button");
	
	
	public BookHotel(WebDriver driver)
	{
		this.driver=driver;
		
	}
	public void citysel(String city)
	{
		driver.navigate().refresh();
		driver.findElement(hotel).click();
		driver.findElement(cityname).sendKeys(city);
		driver.findElement(cochin).click();
	}
	
	public void datesel(String mnth, String dte)
	{
		driver.findElement(datefield).click();
		while(true)
		{
			String actualmonth = driver.findElement(month).getText();
			System.out.println(actualmonth);
			if(actualmonth.equals(mnth))
			{
				break;
			}
			else
			{
				driver.findElement(monthsel).click();
			}
		}
		
		List<WebElement> alldates = driver.findElements(date);
		for(WebElement date:alldates)
		{
			String actualdate=date.getText();
			System.out.println(actualdate);
			if(actualdate.equals(dte))
			{
				date.click();
				break;
			}
		}
	}
	public void count()
	{
		driver.findElement(countdropdown).click();
		driver.findElement(childcount).click();
		driver.findElement(donebutton).click();
	}
	public void search()
	{
		driver.findElement(search).click();
	}
	public void book(String hotelname) throws Exception
	{
		driver.findElement(hotelsearch).sendKeys(hotelname);
		String parentwind=driver.getWindowHandle();
		driver.findElement(hotelbook).click();
		
		Set<String> allwind=driver.getWindowHandles();
		for(String handle:allwind)
		{
			if(!handle.equalsIgnoreCase(parentwind))
			{
				driver.switchTo().window(handle);
				//jse.executeScript("window.scrollBy(0,document.body.scrollHeight)");
				driver.findElement(bookb).click();
				
				File scrnshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileHandler.copy(scrnshot,new File("./screenshot//booking.png"));
				driver.close();
				driver.switchTo().window(parentwind);
			}
			
		}
	}

}
