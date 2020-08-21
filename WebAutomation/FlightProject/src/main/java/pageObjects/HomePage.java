package pageObjects;

import java.text.ParseException;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.CommonActions;
import utils.WebActions;

public class HomePage {
	
	WebDriver driver;
	private static Logger log = Logger.getLogger("HomePage");

	   public HomePage(WebDriver driver)
	   {
		this.driver = driver;				
	   }

	   
	   By menuFlights = By.xpath("//a[text()='Flights']");	    
	   By radioOneWay = By.xpath("//input[@id='tripTypeOW']//parent::label/span[@class='tgl-btn']");
	   By txtbxFrom = By.xpath("//input[@id='from1']");
	   By txtbxTo = By.xpath("//input[@id='to1']");
	   By txtbxDepartureDate = By.xpath("//div[@id='dates']");
       By btnSearch = By.xpath("//button[@type='submit']");
       By popupElement = By.xpath("//button[@id='signUpButton']");
	   By linkNoThanks = By.xpath("//span[text()='No Thanks']");
	   By flightList = By.xpath("//ol[@data-hk='fareOptionCollection']/li[1]/div[@data-hk='fareOption']");
	   By dateReview = By.xpath("//div[@class='src__Box-sc-1sbtrzs-0 fsZetV']");
	//  Get elements methods	   	   
	 
	   
	   public WebElement menuFlights()
	   {
		  
		   log.info("inside menuFlights()");
		   return  WebActions.getElement(driver, menuFlights);
	   }
	   
	   public WebElement radioOneWay()
	   {
		  
		   log.info("inside radioOneWay()");
		   return  WebActions.getElement(driver, radioOneWay);
	   }
	   	  
	   public WebElement txtbxFrom()
	   {
		  
		   log.info("inside txtbxFrom()");
		   return  WebActions.getElement(driver, txtbxFrom);
	   }
	   
	   public WebElement txtbxTo()
	   {
		  
		   log.info("inside txtbxTo()");
		   return  WebActions.getElement(driver, txtbxTo);
	   }
	   
	   public WebElement txtbxDepartureDate()
	   {
		  
		   log.info("inside departureDate()");
		   return  WebActions.getElement(driver, txtbxDepartureDate);
	   }
	   public WebElement departureDate(By by)
	   {
		  
		   log.info("inside departureDate()");
		   return  WebActions.getElement(driver, by);
	   }
	   	 
	   public WebElement btnSearch()
	   {
		  
		   log.info("inside btnSearch()");
		   return  WebActions.getElement(driver, btnSearch);
	   }
	   
	   public WebElement noThanks()
	   {
		  
		   log.info("inside noThanks()");
		   return  WebActions.getElement(driver, linkNoThanks);
	   }
	   
	   public List<WebElement> flightList()
	   {
		  
		   log.info("inside flightList()");
		   return  WebActions.getElements(driver, flightList);
	   }
	   
	   public WebElement dateReview()
	   {
		  
		   log.info("inside dateReview()");
		   return  WebActions.getElement(driver, dateReview);
	   }
	   
	   
	   
	 //Actions methods 
	   
	   public boolean verifyHomePage()
	   {
		   log.info("inside verifyHomePage()");
		 return  WebActions.isElementPresent(driver, menuFlights );
	   }
	  
	   public void menuFlightsClick()
	   {
		  
		   log.info("inside menuFlightsClick()");
		     menuFlights().click();
	   }
	   
	   public void radioOneWaySelect()
	   {
		  
		   log.info("inside radioOneWaySelect()");
		   radioOneWay().click();
	   }
	   
	   public void enterDepartureCity(String city)
	   {
		  
		   log.info("inside enterDepartureCity()");
		   txtbxFrom().clear();
		   txtbxFrom().sendKeys(city);
	   }
	   
	   public void enterArrivalCity(String city)
	   {
		  
		   log.info("inside enterArrivalCity()");
		   txtbxTo().sendKeys(city);
	   }
	   
	   public void selectDepartureDate(String date) throws InterruptedException
	   {
		  log.info("inside selectDepartureDate()");
		  String month;
		  txtbxDepartureDate().click();
		  WebActions.waitTime(1000);
		  String [] dateValues = date.split("-");
		  String day= dateValues[0];
		  if(dateValues[1].charAt(0)=='0') 
			  month =  dateValues[1].replace("0", "");
		  else
			  month =  dateValues[1];
		  
		   By dateby = By.xpath("//table[@class='ui-datepicker-calendar']/tbody/tr[*]/td[@data-month='"+ month +"']/a[text()='"+ day +"']");
		  WebElement selectedDate = departureDate(dateby);	
		  WebActions.waitTime(1000);
		  selectedDate.click();
					
	   }
	   
	   public void searchFlightsClick()
	   {
		  
		   log.info("inside searchFlightsClick()");
		   btnSearch().click();
	   }
	   
	   public void handlePopup()
	   {		  
		   log.info("inside verifyPopup()");
		Set<String>  handles = driver.getWindowHandles();
		for(String handle: handles)
		{
			
			driver.switchTo().window(handle);
		}
		   
		  if( WebActions.isElementPresent(driver, popupElement))
		  {
			  noThanks().click();	
		  }
		     
	   } 
	   
	   public void selectFlight()
	   {
		   log.info("inside selectFlight()");
		   flightList().get(0).click();
	   }
	  
	   public String getReviewPageDate() throws ParseException
	   {
		   log.info("inside getReviewPageDate()");
		   String reviewDate = dateReview().getText();
		   log.info("reviewDate="+reviewDate);
		   String modifiedDate = reviewDate.split("-")[1].replace("th,", "");	
		   log.info("modifiedDate="+modifiedDate);
		   return CommonActions.formatDate(modifiedDate);
		   
	   }
	  
	   
	   
}
