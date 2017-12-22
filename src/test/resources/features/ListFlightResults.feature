Feature: Flight results listing
	As an anonymous customer
	I need to be able to search for flights
	So that I can choose the best prices available 

@PositiveScenario
Scenario: Search results should list flights available
	Given I want to fly from "Heathrow" airport in "London" to "Dublin" airport in "Dublin"
	And I want to depart on "10/01/2018" and return on "20/01/2018" 
	And I select 2 for the number of adults flying
	When I submit the search
	Then the search results page should list the available flights
	And the first flight option should cost "£87.90"
	And the first flight option should not cost "£0.92"
	And the panel Airlines included should be  visible
	And the bottom of the  page should contain the text "© 2017 Expedia, Inc. All rights reserved."
	