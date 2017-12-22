package pageObjects.resultsPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import pageObjects.PageObjectInterface;
import driver.ElementFinder;

public class FlightsAvailable implements PageObjectInterface{

	private ElementFinder finder;
	
	@FindBy(how = How.NAME, using="sort")
	private WebElement sortBar;
	@FindBy(how = How.ID, using="flightModuleList")
	private WebElement flightResultsList;
	String buttonClassName = "btn-secondary btn-action t-select-btn";
	String priceAreaClassName = "flex-area-secondary";
	String messageForPriceProcessing = "finalising prices";
	
	private String firstFlightsPrice = null;
	
	public FlightsAvailable(WebDriver webDriver) {
		finder = new ElementFinder(webDriver);
	}

	public void verifyElementsPresent() {
		finder.verifyElementPresent(sortBar);
		finder.waitForElementPresent(flightResultsList);
	}
	
	public String getPriceForFirstFlightOption(){
		finder.waitForTextNotVisibleWithClassName(flightResultsList, priceAreaClassName, messageForPriceProcessing);
		finder.waitForElementWithClassName(flightResultsList, buttonClassName);
		
		if(firstFlightsPrice == null){
			firstFlightsPrice = finder.getPriceForFirstFlightAvailable(flightResultsList, buttonClassName);
		}
		return firstFlightsPrice;
	}
}