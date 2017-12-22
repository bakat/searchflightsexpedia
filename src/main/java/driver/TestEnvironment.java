package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class TestEnvironment {
	private static EnumBrowser browser;
	private static EnumEnvironment environment;
	private WebDriver driver;
	private ElementFinder finder;
	private String url;
	private static DesiredCapabilities capabilities;
	
	public DesiredCapabilities getCapabilities() {
		return capabilities;
	}

	public static void setCapabilities(DesiredCapabilities desiredCapabilities) {
		capabilities = desiredCapabilities;
	}

	public EnumBrowser getBrowser() {
		return browser;
	}

	public static void setBrowser(EnumBrowser enumBrowser) {
		browser = enumBrowser;
	}

	public EnumEnvironment getEnvironment() {
		return environment;
	}

	public static void setEnvironment(EnumEnvironment enumEnvironment) {
		environment = enumEnvironment;
	}

	public WebDriver getDriver(){
		return driver;
	}
	
	public void setDriver(WebDriver webDriver){
		driver = webDriver;
	}
	
	public ElementFinder getFinder(){
		if(finder == null){
			finder = new ElementFinder();
			return finder;
		}
		return finder;
	}
	
	public String getUrl(){
		return url;
	}
	
	public void setUrl(String urlEnvironment){
		url = urlEnvironment;
	}
	
	@Before
	public void setUp() throws Exception {
		getFinder().getDriver(getBrowser(), getCapabilities());
		setDriver(getFinder().getWebDriver());
		setUrl(getFinder().getEnvironment(getEnvironment()));
	}
	
	@After
	public void tearDown() throws Exception {
		getFinder().closeBrowser();
	}
}