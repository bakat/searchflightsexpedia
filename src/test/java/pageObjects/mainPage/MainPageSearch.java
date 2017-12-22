package pageObjects.mainPage;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import pageObjects.PageObjectInterface;
import pageObjects.resultsPage.ResultsPage;
import driver.ElementFinder;

public class MainPageSearch implements PageObjectInterface {

	private ElementFinder finder;

	@FindBy(how = How.ID, using = "flight-origin-hp-flight")
	private WebElement inputFrom;
	@FindBy(how = How.ID, using = "flight-destination-hp-flight")
	private WebElement inputTo;
	@FindBy(how = How.ID, using = "flight-departing-hp-flight")
	private WebElement departureDate;
	@FindBy(how = How.ID, using = "flight-returning-hp-flight")
	private WebElement returningDate;
	@FindBy(how = How.ID, using = "flight-adults-hp-flight")
	private WebElement numberOfAdults;
	@FindBy(how = How.ID, using = "typeaheadDataPlain")
	private WebElement airportOptionsDropDown;
	@FindBy(how = How.ID, using = "gcw-flights-form-hp-flight")
	private WebElement searchForm;
	@FindBy(how = How.ID, using = "tab-flight-tab-hp")
	private WebElement flightsButton;
	@FindBy(how = How.ID, using = "tab-hotel-tab-hp")
	private WebElement hotelsButton;
	@FindBy(how = How.ID, using = "tab-package-tab-hp")
	private WebElement bundleAndSaveButton;

	private List<String> airPortElementDataTypePrecedence = new ArrayList<String>();

	public MainPageSearch(WebDriver webDriver) {
		finder = new ElementFinder(webDriver);
		airPortElementDataTypePrecedence.add("AIRPORT");
		airPortElementDataTypePrecedence.add("MULTICITY");
	}

	public void verifyElementsPresent() {
		finder.verifyElementPresent(flightsButton);
		finder.verifyElementPresent(hotelsButton);
		finder.verifyElementPresent(bundleAndSaveButton);
	}

	public ResultsPage searchForFlights(String fromAirport,
			String fromCity, String toAirport, String toCity,
			String departureDate, String returningDate, int numberOfAdults) {
		finder.clickElement(flightsButton);
		
		finder.fillInElement(inputFrom, fromCity);
		finder.clickDropDownOptionWithDataTypePrecendence(airportOptionsDropDown, airPortElementDataTypePrecedence, fromAirport);
		finder.fillInElement(inputTo, toCity);
		finder.clickDropDownOptionWithDataTypePrecendence(airportOptionsDropDown, airPortElementDataTypePrecedence, toAirport);
		finder.fillInElement(this.departureDate, departureDate);
		finder.moveToElement(this.returningDate);
		finder.clearElementText(this.returningDate);
		finder.fillInElement(this.returningDate, returningDate);
		finder.clickValueOnSelectElement(this.numberOfAdults, String.valueOf(numberOfAdults));
		finder.clickSubmitButtonInsideForm(searchForm);

		return PageFactory.initElements(finder.getWebDriver(),
				ResultsPage.class);
	}
}