package driver;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import util.TestAutomationProperties;

public class DriverLoader {

	private TestAutomationProperties properties;
	private WebDriver webDriver;
	
	public DriverLoader() {
		properties = new TestAutomationProperties();
		properties.loadProperties();
	}
	public void getDriver(EnumBrowser browser, DesiredCapabilities capabilities){
		
		File file;
		
		if(browser != null){
			switch (browser) {
				case FIREFOX:
					try {
						file = new File(properties.getBrowserFireFox());
						System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());
						webDriver = new FirefoxDriver();
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				
				case CHROME:
					file = new File(properties.getBrowserChrome());
					System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());

					try {
						webDriver = new ChromeDriver();
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
					
				case IE:
					file = new File(properties.getBrowserIE());
					System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
					
					try {
						webDriver = new InternetExplorerDriver();
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					break;
					
				default:
					System.out.println("Not a valid browser.");
					break;
			}
		} else if(browser == null){
			try {
				file = new File(properties.getBrowserFireFox());
				System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());
				webDriver = new FirefoxDriver();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
