Feature: Compare weather information of particular location from UI and Rest-API


 Scenario: Retreive weather information for any City using NDTV website
 Given User able to launch NDTV website
 And Navigate to weather section
 When User provide location as "Coimbatore" 
 Then Weather details should get displayed
 And Save temperature details in external file
 Then Close the browser 
 
 @test
  Scenario Outline: Retreive weather information for any City using API
  Given User set URI for "<ServiceName>" service with protocol as "<Protocol>" 
  When User hit the get request with city as "<Location>"
  Then Service should return status code "<Status>" 
  Then Validate response with schema
  And Check whether response has the node "<Node>" 
  Then Verify weather against weather from NDTV weather data
  Examples:
  |ServiceName	 |Protocol|Location  |Status|Node|
  |openWeatherMap|secured |Coimbatore|200		|temp|   