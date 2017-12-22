package steps;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import pageObjects.mainPage.MainPage;
import pageObjects.resultsPage.AirlinesIncluded;
import pageObjects.resultsPage.FlightsAvailable;
import pageObjects.resultsPage.ResultsPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import driver.ElementFinder;
import driver.TestEnvironment;

public class Steps {

	private TestEnvironment testEnvironment;
	private MainPage mainPage;
	private ResultsPage resultsPage;
	private FlightsAvailable flightsAvailable;
	private AirlinesIncluded airlinesIncluded;
	
	public Steps(TestEnvironment testEnvironment) {
		this.testEnvironment = testEnvironment;
	}
	
	@Given("^I want to fly from \"([^\"]*)\" airport in \"([^\"]*)\" to \"([^\"]*)\" airport in \"([^\"]*)\"$")
	public void i_want_to_fly_from_airport_in_to_airport_in(String airPortFrom, String cityFrom, String airPortTo, String cityTo) throws Throwable {
		ElementFinder finder = new ElementFinder(testEnvironment.getDriver());
		finder.LoadPage(testEnvironment.getDriver(), testEnvironment.getUrl());

		mainPage = PageFactory.initElements(testEnvironment.getDriver(), MainPage.class);
		mainPage.verifyElementsPresent();
		mainPage.flyingFrom(airPortFrom).in(cityFrom).toAirport(airPortTo).inCity(cityTo);
	}

	@Given("^I want to depart on \"([^\"]*)\" and return on \"([^\"]*)\"$")
	public void i_want_to_depart_on_and_return_on(String departureDate, String returningDate) throws Throwable {
		mainPage.departingOn(departureDate).returningOn(returningDate);
	}
	
	@Given("^I select (\\d+) for the number of adults flying$")
	public void i_select_for_the_number_of_adults_flying(int adults) throws Throwable {
		 mainPage.withNumberOfAdultsFlying(adults);
	}

	@When("^I submit the search$")
	public void i_submit_the_search() throws Throwable {
	    resultsPage = mainPage.performSearch();
	}

	@Then("^the search results page should list the available flights$")
	public void the_search_results_page_should_list_the_available_flights() throws Throwable {
		resultsPage.verifyElementsPresent();
		flightsAvailable = resultsPage.getFlightsAvailable();
		airlinesIncluded = resultsPage.getAirlinesIncluded();
		flightsAvailable.verifyElementsPresent();
		airlinesIncluded.verifyElementsPresent();
	}

	@Then("^the first flight option should cost \"([^\"]*)\"$")
	public void the_first_flight_option_should_cost(String flightPrice) throws Throwable {
		String flightPriceFromResultsPage = flightsAvailable.getPriceForFirstFlightOption();
	    Assert.assertTrue(flightPriceFromResultsPage.equals(flightPrice));
	}

	@Then("^the first flight option should not cost \"([^\"]*)\"$")
	public void the_first_flight_option_should_not_cost(String flightPrice) throws Throwable {
		String flightPriceFromResultsPage = flightsAvailable.getPriceForFirstFlightOption();
		Assert.assertFalse(flightPriceFromResultsPage.equals(flightPrice));
	}

	@Then("^the panel Airlines included should be  visible$")
	public void the_panel_should_be_visible() throws Throwable {
	    airlinesIncluded.airLinesPanelShouldBeVisible();
	}

	@Then("^the bottom of the  page should contain the text \"([^\"]*)\"$")
	public void the_bottom_of_the_page_should_contain_the_text(String text) throws Throwable {
	    Assert.assertTrue(resultsPage.getFooterCopyrightMessage().equals(text));
	}
}