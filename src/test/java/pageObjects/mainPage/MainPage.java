package pageObjects.mainPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import pageObjects.PageObjectInterface;
import pageObjects.resultsPage.ResultsPage;
import driver.ElementFinder;

public class MainPage implements PageObjectInterface{

	private ElementFinder finder;
	
	@FindBy(how = How.ID, using="all-in-home-header-link")
	private WebElement homeHeader;
	
	private String pageTitle = "Book Cheap Holidays: Flights, Hotels, Car hire | Expedia";

	private String fromCity;
	private String fromAirport;
	private String toAirport;
	private String toCity;

	private int adults;

	private String departureDate;

	private String returnDate;
	
	public MainPage(WebDriver webDriver) {
		finder = new ElementFinder(webDriver);
		Assert.assertTrue(finder.getPageTitle().equals(pageTitle));
	}

	public void verifyElementsPresent() {
		finder.verifyElementPresent(homeHeader);
	}
	
	public MainPage flyingFrom(String fromAirport){
		this.fromAirport = fromAirport;
		return this;
	}
	
	public MainPage in(String fromCity){
		this.fromCity = fromCity;
		return this;
	}
	
	public MainPage toAirport(String toAirport){
		this.toAirport = toAirport;
		return this;
	}
	
	public MainPage inCity(String toCity){
		this.toCity = toCity;
		return this;
	}
	
	public MainPage departingOn(String departureDate){
		this.departureDate = departureDate;
		return this;
	}
	
	public MainPage returningOn(String returnDate){
		this.returnDate = returnDate;
		return this;
	}
	
	public MainPage withNumberOfAdultsFlying(int adults){
		this.adults = adults;
		return this;
	}
	
	public ResultsPage performSearch(){
		MainPageSearch search = PageFactory.initElements(finder.getWebDriver(), MainPageSearch.class);
		search.verifyElementsPresent();
		
		return search.searchForFlights(fromAirport, fromCity, toAirport, toCity, departureDate, returnDate, adults);
	}
}