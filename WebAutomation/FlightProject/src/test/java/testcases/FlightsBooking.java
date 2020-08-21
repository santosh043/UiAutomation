package testcases;

import java.text.ParseException;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import utils.Constants;
import utils.WebActions;


public class FlightsBooking {
	
	WebDriver driver ;
	HomePage homePage  ;
	private static Logger log ;
	
	@Parameters("browser")
	@BeforeSuite
	public void openBrowser(String browser )
	{
		DOMConfigurator.configure(Constants.log4jConfigPath);		
		driver =  WebActions.openBrowser(browser);
		homePage = new HomePage(driver);
		log	= Logger.getLogger("FlightsBooking");
		
		
	}
	
	
	
	@Test
	@Parameters ({"departureCity","arrivalCity","departureDate"})
	public void oneWayFlightsBooking(String departureCity, String arrivalCity, String departureDate) throws InterruptedException, ParseException
	{
		log.info("oneWayFlightsBooking test case started");
		homePage.verifyHomePage();
		homePage.menuFlightsClick();
		homePage.radioOneWaySelect();
		homePage.enterDepartureCity(departureCity);
		homePage.enterArrivalCity(arrivalCity);
		homePage.selectDepartureDate(departureDate);
		homePage.searchFlightsClick();
		homePage.handlePopup();
		homePage.selectFlight();
		
		String actualDate = homePage.getReviewPageDate();
		log.info("actualDate="+actualDate);		
		String expectedDate = departureDate;
		log.info("expectedDate="+expectedDate);
		
		Assert.assertEquals(actualDate, expectedDate);
		
	}
	
	@AfterSuite
	public void closeBrowser()
	{
		
		//WebActions.closeBrowser(driver);
	}

}
