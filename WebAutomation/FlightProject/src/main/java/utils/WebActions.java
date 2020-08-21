package utils;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebActions {
	
	public static WebDriver driver;	
	 private static Logger log = Logger.getLogger("WebActions");
	
	public static WebDriver openBrowser(String browser)
	{			
		log.info("inside openBrowser() method");
		
		if(browser.equals("firefox"))
		{			
			System.setProperty("webdriver.gecko.driver", Constants.geckoDriverPath);
			driver = new FirefoxDriver();
			
			log.info("firefox browser initialized");
		}
		else 
		if(browser.equals("chrome"))
		{			
			System.setProperty("webdriver.chrome.driver", Constants.chromeDriverPath);
			ChromeOptions options = new ChromeOptions();
			options.setPageLoadStrategy(PageLoadStrategy.EAGER);
			driver = new ChromeDriver(options);
			
			log.info("chrome browser initialized");
		}		
		
		driver.manage().window().maximize();		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		
		driver.get(Constants.HomePageURL);				
		return driver;
	}
	
	public static void closeBrowser(WebDriver driver)
	{
		log.info("inside closeBrowser() method");
		try 
		{			
			driver.close();
		}
		catch (Exception e) 
	    {			
		log.error("browser not closed Exception = " +e.getMessage());	
		 throw(e);
		 
		}
	}
	
	public static void openPage(String url)
	{		
		log.info("inside openPage() method");
		try 
		{
		driver.get(url);
		}
	   catch (Exception e) 
       {			
	     log.error(url + "not opened .  Exception = " +e.getMessage());	
	    throw(e);	 
	   }
    
	}
	
	public static WebElement getElement(WebDriver driver, By by)
	{
		log.info("inside getElement() method");
		WebElement ele = null;
		try {			
				
			 WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));	
			ele = driver.findElement(by);	
			log.info("Webelement founded = " +ele);	
			} 
		 catch (Exception e) 
		    {				
			log.error("Webelement not founded Exception = " +e.getMessage());			
			 
			}
		
		return ele;
	}
	
	public static List<WebElement> getElements(WebDriver driver, By by)
	{
		log.info("inside getElement() method");
		List<WebElement> eles = null;
		try {			
				
			 WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));	
			eles = driver.findElements(by);	
			log.info("Webelement founded = " +eles);	
			} 
		 catch (Exception e) 
		    {				
			log.error("Webelement not founded Exception = " +e.getMessage());			
			 
			}
		
		return eles;
	}
	public static boolean isElementPresent(WebDriver driver , By ele)
	{
		log.info("inside isElementPresent() method");	
		try {
			
		    WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(ele));			
			driver.findElement(ele);		
			log.info(" isElementPresent() sucess");
			return true;
			} 
		 catch (Exception e) {
			 log.error(" isElementPresent() failed. Exception="+e.getMessage());
			 return false;
			}
			
	}
	
	public static WebElement waitForElementPresent(WebDriver driver , By ele)
	{			
		log.info("inside waitForElementPresent() method");	
		
		WebElement element=null;
		try {
		    WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(ele));			
			element =driver.findElement(ele);
			log.info("element present");	
		    }
		catch(Exception e)
		{
			log.error("element not present. Exception="+e.getMessage());
		    
		}
		
		return element;
					
	}
	
	public static boolean waitForElementClickable(WebDriver driver , WebElement ele)
	{			
	    	log.info("inside waitForElementClickable() method");	  	
	    	try {
		    WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			log.info(" waitForElementClickable() succes");			
			return true;
	    	}
	    	catch(Exception e)
	    	{	    		
	    		log.error(" waitForElementClickable() failed. Exception="+e.getMessage());	  
	    		return false;
	    		 
	    	}
			
	}
	public static boolean waitForElementClickable(WebDriver driver , By ele)
	{			
		  log.info("inside waitForElementClickable() method");	
		   try
		   {
		    WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			return true;
		   }
	       catch(Exception e)
	       {	    		
		    log.error(" waitForElementClickable() failed. Exception="+e.getMessage());	 
		    return false;
		    
	       }
			
	}
	
	public static void waitTime(int millisec) throws InterruptedException
	{
		Thread.sleep(millisec);
	}

  
}
