package tests;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(plugin = {"html:target/test-report"}, 
features = {"src/test/resources/features/ListFlightResults.feature"}, 
glue = {"steps", "driver"},
tags = {})
public class ListFlightResultsTest extends AbstractTestNGCucumberTests{

}