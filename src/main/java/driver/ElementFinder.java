package driver;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.TestAutomationProperties;

import com.google.common.base.Function;

public class ElementFinder {
	
	private static final Long TIMEOUT = 30L;
	private TestAutomationProperties properties;
	private WebDriver webDriver;
	
	public ElementFinder() {
		properties = new TestAutomationProperties();
		properties.loadProperties();
	}
	
	public ElementFinder(WebDriver driver){
		properties = new TestAutomationProperties();
		properties.loadProperties();
		webDriver = driver;
	}

	public WebDriver getWebDriver(){
		return webDriver;
	}

	public void navigateToUrl(String url){
		webDriver.navigate().to(url);
	}
	
	public void LoadPage(WebDriver webDriver, String url){
		webDriver.get(url);
		webDriver.manage().window().maximize();
	}
	
	public void closeBrowser(){
		webDriver.quit();
	}
	
	public String getPageTitle(){
		return webDriver.getTitle();
	}
	
	public Boolean verifyPageTitle(String pageTitle){
		return webDriver.getTitle().equals(pageTitle);
	}
	
	public void waitForElementPresent(final By by) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(webDriver)
			       .withTimeout(30, TimeUnit.SECONDS)
			       .pollingEvery(5, TimeUnit.SECONDS)
			       .ignoring(NoSuchElementException.class);

	   wait.until(new Function<WebDriver, WebElement>() {
	     public WebElement apply(WebDriver driver) {
	       return driver.findElement(by);
	     }
	   });
	}
	
	public void waitForElementWithClassName(WebElement element, String className){
		int attempts = 0;
		while (attempts < 15) {
			try {
				attempts++;
				waitForElementToBeClickable(element.findElements(By.className(className)).iterator().next());
				if(element.findElements(By.className(className)).iterator().next().isDisplayed()){
					break;
				}
			} catch (Exception e) {
			}
		}
	}
	
	public void waitForTextNotVisibleWithClassName(WebElement element, String className, String text){
		int attempts = 0;
		while (attempts < 15) {
			try {
				attempts++;
				if(!element.findElements(By.className(className)).iterator().next().getText().contains(text)){
					break;
				}
			} catch (Exception e) {
			}
		}
	}
	
	public void waitForElementToBeClickable(WebElement element){
		WebDriverWait wait = new WebDriverWait(webDriver, TIMEOUT);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void waitForElementPresent(WebElement element) {
		WebDriverWait wait = new WebDriverWait(webDriver, TIMEOUT);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementsVisible(List<WebElement> elements) {
		WebDriverWait wait = new WebDriverWait(webDriver, TIMEOUT);
		wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}
	
	public void waitForTextPresent(final String text){
		Wait<WebDriver> wait = new FluentWait<WebDriver>(webDriver)
			       .withTimeout(30, TimeUnit.SECONDS)
			       .pollingEvery(5, TimeUnit.SECONDS)
			       .ignoring(NoSuchElementException.class);

	   Boolean element = wait.until(new Function<WebDriver, Boolean>() {
	     public Boolean apply(WebDriver driver) {
	       return driver.getPageSource().contains(text);
	     }
	   });
	   
	   if(element.equals(false)){
		   System.out.println("Text " + text + " not found.");
	   }
	}
	
	public Boolean verifyTextPresent(String text){
		return webDriver.getPageSource().contains(text);
	}
	
	public void fillInElement(WebElement element, String text){
		waitForElementPresent(element);
		element.click();
		element.sendKeys(text);
	}
	
	public void enterKeyStroke(WebElement element){
		waitForElementPresent(element);
		Actions action = new Actions(webDriver);
		action.sendKeys(element, Keys.ENTER);
	}
	
	public void clearElementText(WebElement element){
		element.clear();
	}
	
	public void clickElement(WebElement element){
		waitForElementPresent(element);
		waitForElementToBeClickable(element);
		element.click();
	}
	
	public void clickElementThatContainsNameInsideASpan(WebElement element, String text){
		List<WebElement> elements = element.findElements(By.tagName("span"));
		
		searchForElementAndClick(text, elements);
	}
	
	public void clickDropDownOptionWithDataTypePrecendence(WebElement  element, List<String> elementDataType, String parameterForSearch){
		waitForElementPresent(element);
		List<WebElement> elementLis = element.findElements(By.tagName("li"));
		Iterator<String> dataTypeIterator = elementDataType.iterator();
		String dataTypeCheck = dataTypeIterator.next();
		
		WebElement elementFound = findElementByDataTypeAndValue(parameterForSearch, elementLis,
				dataTypeCheck);
		
		if(elementFound != null){
			moveToElementAndClick(elementFound);
		} else{
			waitForElementPresent(element);
			dataTypeCheck = dataTypeIterator.next();
			elementFound = findElementByDataTypeAndValue(parameterForSearch, elementLis,
					dataTypeCheck);
			moveToElementAndClick(elementFound);
		}
	}

	public void moveToElementAndClick(WebElement elementFound) {
		new Actions(webDriver).moveToElement(elementFound).click().perform();
	}
	
	public void moveToElement(WebElement elementFound) {
		new Actions(webDriver).moveToElement(elementFound).perform();
	}
	
	public void moveToTheEndOfThePage(){
		new Actions(webDriver).sendKeys(Keys.END).perform();
	}

	private WebElement findElementByDataTypeAndValue(String parameterForSearch,
			List<WebElement> elements, String dataTypeCheck) {
		for (WebElement webElement : elements) {
			List<WebElement> aElements = webElement.findElements(By.tagName("a"));
			for (WebElement aElement : aElements) {
				if(hasAttribute(aElement, "data-type")){
					if(aElement.getAttribute("data-type").equals(dataTypeCheck)){
						if(aElement.getAttribute("data-value").contains(parameterForSearch)){
							return webElement.findElement(By.id(aElement.getAttribute("id")));
						}
					}
				}
			}
		}
		return null;
	}

	private void searchForElementAndClick(String text, List<WebElement> elements) {
		for (WebElement webElement : elements) {
			if(webElement.getText().contains(text)){
				waitForElementPresent(webElement);
				waitForElementToBeClickable(webElement);
				webElement.click();
			}
		}
	}
	
	public void clickValueOnSelectElement(WebElement element, String value){
		Select select = new Select(element);
		select.selectByVisibleText(value);
	}
	
	public void clickSubmitButtonInsideForm(WebElement element){
		List<WebElement> buttonsInsideForm = element.findElements(By.tagName("button"));
		for (WebElement webElement : buttonsInsideForm) {
			if(hasAttribute(webElement, "type")){
				if(webElement.getAttribute("type").equals("submit")){
					clickElement(webElement);
				}
			}
		}
	}
	
	public void clickElementByPartialLinkText(WebElement element, String text){
		List<WebElement> elements = element.findElements(By.partialLinkText(text));
		
		searchForElementAndClick(text, elements);
	}
	
	public String getPriceForFirstFlightAvailable(WebElement element, String className){
		waitForElementPresent(element);
		List<WebElement> liElements = element.findElements(By.tagName("li"));
		
		for (WebElement webElement : liElements) {
			waitForElementWithClassName(webElement, className);
			List<WebElement> priceColumn = webElement.findElements(By.className("price-column"));
			for (WebElement price : priceColumn) {
				if(hasAttribute(price, "data-test-price-per-traveler")){
					return price.getAttribute("data-test-price-per-traveler");
				}
			}
		}
		return null;
	}
	
	private boolean hasAttribute(WebElement webElement, String attribute) {
		try {
			if(!webElement.getAttribute(attribute).isEmpty()){
				return true;
			}
		} catch (Exception e) {
			
		}
		return false;
	}

	public String getElementsText(WebElement element){
		waitForElementPresent(element);
		return element.getText();
	}
	
	public void submitForm(WebElement element){
		element.submit();
	}
	
	public Boolean verifyElementPresent(WebElement element){
		waitForElementPresent(element);
		return element.isEnabled();
	}
	
	public Boolean verifyThatInputElementsAreVisible(List<WebElement> element){
		Boolean result = true;
		for (WebElement webElement : element) {
			waitForElementPresent(webElement);
			if(!webElement.isDisplayed()){
				return false;
			}
		}
		return result;
	}

}