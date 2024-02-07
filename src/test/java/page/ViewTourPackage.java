package page;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class ViewTourPackage {
	WebDriver driver;
	
	By holiday=By.xpath("//*[@id=\"bs-navbar\"]/ul/li[1]/a[1]");
	By kerala=By.xpath("//*[@id=\"bs-navbar\"]/ul/li[1]/ul/div[2]/div/li[5]/a");
	By hills=By.xpath("//*[@id=\"package_detail_PKG009198\"]/div[1]/div[1]/div/div[2]/div/div[1]/div[1]/h2/a");
	By overview=By.xpath("//*[@id=\"packageDetails\"]/div[2]/div/div[4]/div[6]/div[1]/div[2]/ul/li[2]/a");
	By itinerary=By.xpath("//*[@id=\"packageDetails\"]/div[2]/div/div[4]/div[6]/div[1]/div[2]/ul/li[3]/a");
	By selector=By.xpath("//*[@id=\"pdpItinerary\"]/div[1]/div/div/div/a[2]");
	By price=By.xpath("//*[@id=\"packageDetails\"]/div[2]/div/div[4]/div[6]/div[1]/div[2]/ul/li[1]/a");
	By checkbox=By.xpath("//*[@id=\"termsAndConditions\"]");
	
	public ViewTourPackage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void pkgselect()
	{
		Actions act=new Actions(driver);
		act.moveToElement(driver.findElement(holiday)).perform();
		driver.findElement(kerala).click();
		
		JavascriptExecutor jse=(JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,1500)", "");
		
		String parentwin=driver.getWindowHandle();
		driver.findElement(hills).click();
		
		Set<String> allwin = driver.getWindowHandles();
		for(String handle:allwin)
		{
			if(!handle.equalsIgnoreCase(parentwin))
			{
				driver.switchTo().window(handle);
				jse.executeScript("window.scrollBy(0,800)", "");
				
				driver.findElement(overview).click();
				jse.executeScript("window.scrollBy(0,document.body.scrollHeight)");
				
				driver.findElement(itinerary).click();
				jse.executeScript("window.scrollBy(0,document.body.scrollHeight)");
				driver.findElement(selector).click();
				jse.executeScript("window.scrollBy(0,document.body.scrollHeight)");
				driver.findElement(selector).click();
				jse.executeScript("window.scrollBy(0,document.body.scrollHeight)");
				driver.findElement(selector).click();
				jse.executeScript("window.scrollBy(0,document.body.scrollHeight)");
				driver.findElement(price).click();
				
				if(driver.findElement(checkbox).isSelected())
				{
					System.out.println("Checkbox is selected");
				}
				else
				{
					driver.findElement(checkbox).click();
				}
				driver.close();
				driver.switchTo().window(parentwin);
			}
		}
		driver.navigate().back();
	}

}
