package util;

import java.io.IOException;
import java.util.Properties;

public class TestAutomationProperties {

	private String browserChrome;
	private String browserIE;
	private String browserFireFox;
	private String environmentProduction;
	private String environmentQA;
	private String defaultEnvironment;
	
	private final String PROPERTIES_FILE = 
			"config/testAutomation.properties";
	
	public void loadProperties() {
		Properties propertiesFile = new Properties();
		 
    	try {
    		propertiesFile.load(getClass().getClassLoader().getResourceAsStream(
					PROPERTIES_FILE));
 
    		browserFireFox = GetDriver.fireFox();
    		browserChrome = propertiesFile.getProperty("BROWSER_CHROME");
    		browserIE = propertiesFile.getProperty("BROWSER_IE");
    		environmentProduction = propertiesFile.getProperty("ENVIRONMENT_PROD");
    		environmentQA = propertiesFile.getProperty("ENVIRONMENT_QA");
    		defaultEnvironment = propertiesFile.getProperty("DEFAULT_ENVIRONMENT");
    		
    	} catch (IOException ex) {
    		System.out.println("Could not load properties file. \r\n");
    		ex.printStackTrace();
        }
	}

	public String getBrowserChrome() {
		return browserChrome;
	}

	public String getBrowserIE() {
		return browserIE;
	}

	public String getBrowserFireFox() {
		return browserFireFox;
	}

	public String getEnvironmentProduction() {
		return environmentProduction;
	}

	public String getEnvironmentQA() {
		return environmentQA;
	}

	public String getDefaultEnvironment() {
		return defaultEnvironment;
	}
	
}
