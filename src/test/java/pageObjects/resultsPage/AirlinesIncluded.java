package pageObjects.resultsPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import driver.ElementFinder;
import pageObjects.PageObjectInterface;

public class AirlinesIncluded implements PageObjectInterface {

	private ElementFinder finder;

	@FindBy(how = How.ID, using = "airlines")
	private WebElement airLinesPanel;

	public AirlinesIncluded(WebDriver webDriver) {
		finder = new ElementFinder(webDriver);
	}

	public void verifyElementsPresent() {
		finder.waitForElementPresent(airLinesPanel);
	}

	public void airLinesPanelShouldBeVisible() {
		Assert.assertTrue(finder.verifyElementPresent(airLinesPanel));
		Assert.assertTrue(finder
				.verifyThatInputElementsAreVisible(airLinesPanel
						.findElements(By.tagName("input"))));
	}
}