package pageObjects.resultsPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import pageObjects.PageObjectInterface;
import driver.ElementFinder;

public class ResultsPage implements PageObjectInterface{

	private ElementFinder finder;
	
	@FindBy(how = How.ID, using="titleBar")
	private WebElement titleBar;
	@FindBy(how = How.ID, using="footer-copyright-msg")
	private WebElement footer;
	
	public ResultsPage(WebDriver webDriver) {
		finder = new ElementFinder(webDriver);
	}

	public void verifyElementsPresent() {
		finder.verifyElementPresent(titleBar);
	}
	
	public String getFooterCopyrightMessage(){
		finder.moveToTheEndOfThePage();
		return finder.getElementsText(footer);
	}
	
	public FlightsAvailable getFlightsAvailable(){
		return PageFactory.initElements(finder.getWebDriver(), FlightsAvailable.class);
	}
	
	public AirlinesIncluded getAirlinesIncluded(){
		return PageFactory.initElements(finder.getWebDriver(), AirlinesIncluded.class);
	}
}