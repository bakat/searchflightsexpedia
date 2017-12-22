package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import util.TestAutomationProperties;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class TestEnvironment {
	private static EnumBrowser browser;
	private static EnumEnvironment environment;
	private WebDriver webDriver;
	private String url;
	private static DesiredCapabilities capabilities;
	private DriverLoader driverLoader;
	TestAutomationProperties properties;
	
	public TestEnvironment(DriverLoader driverLoader) {
		this.driverLoader = driverLoader;
		properties = new TestAutomationProperties();
		properties.loadProperties();
	}
	
	@Before
	public void setUp() throws Exception {
		driverLoader.getDriver(getBrowser(), getCapabilities());
		setDriver(driverLoader.getWebDriver());
		setUrl(getTestEnvironment(getEnvironment()));
	}
	
	@After
	public void tearDown() throws Exception {
		driverLoader.getWebDriver().quit();
	}
	
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
		return webDriver;
	}
	
	public void setDriver(WebDriver webDriver){
		this.webDriver = webDriver;
	}
	
	public String getTestEnvironment(EnumEnvironment environment){
		
		String webEnvironment = "";
		
		if(environment != null){
			switch (environment) {
				case PRODUCTION:
					webEnvironment = properties.getEnvironmentProduction();
					break;
				
				case QA:
					webEnvironment = properties.getEnvironmentQA();	
					break;
					
				default:
					System.out.println("Not a valid environment");
					break;
			}
		} else {
			if(properties.getDefaultEnvironment().equals("QA")){
				webEnvironment = properties.getEnvironmentQA();
			}else if(properties.getDefaultEnvironment().equals("PRODUCTION")){
				webEnvironment = properties.getEnvironmentProduction();
			} else{
				webEnvironment = properties.getEnvironmentQA();
			}
		}
		
		return webEnvironment;
	}
	
	public String getUrl(){
		return url;
	}
	
	public void setUrl(String urlEnvironment){
		url = urlEnvironment;
	}
}