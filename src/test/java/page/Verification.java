package page;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Verification {
	WebDriver driver;
	
	By logo=By.xpath("//*[@id=\"top\"]/div/div[1]/a/img");
	By tag=By.tagName("a");
	
	public Verification(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void titleverif()
	{
		String actualt=driver.getTitle();
		System.out.println("Actual title is:"+actualt);
		String expectedt="Thomas Cook Tours and Travels: Flights, Hotels, Forex & Holidays Packages";
		if(actualt.equals(expectedt))
		{
			System.out.println("Title verification passed");
		}
		else
		{
			System.out.println("Title verification failed");
		}
	}
	
	public void logoverif()
	{
		if(driver.findElement(logo).isDisplayed())
		{
			System.out.println("Logo is diaplayed");
		}
		else
		{
			System.out.println("Logo is not displayed");
		}
	}
	
	public void contentverif()
	{
		if(driver.getPageSource().contains("I want to Holiday in and around..."))
		{
			System.out.println("Content verification passed");
		}
		else
		{
			System.out.println("Content verification failed");
		}
	}
	
	public void linkverif()
	{
		List<WebElement> li = driver.findElements(tag);
		System.out.println("Number of links is:"+li.size());
		for(WebElement l:li)
		{
			String link=l.getAttribute("href");
			verify(link);
		}
	}
	public void verify(String link)
	{
		try
		{
			URL objc=new URL(link);
			HttpURLConnection con=(HttpURLConnection)objc.openConnection();
			con.connect();
			if(con.getResponseCode()==200)
			{
				System.out.println("Valid link:"+link);
			}
			else if(con.getResponseCode()==404)
			{
				System.out.println("Invalid link:"+link);
			}	
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	
}
