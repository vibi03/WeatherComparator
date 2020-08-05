Feature: Compare weather information of particular location from UI and Rest-API

@test
 Scenario: Retreive weather information for any City using NDTV website
 Given User able to launch NDTV website
 Then Navigate to weather section
 When User provide location as "Coimbatore" 
 Then Weather details should get displayed
 
 @test
  Scenario Outline: Retreive weather information for any City using API
  Given User set URI for "<ServiceName>" service with protocol as "<Protocol>" 
  When User hit the get request with city as "<Location>"
  Then Service should return status code "<Status>" 
  Examples:
  |ServiceName	 |Protocol|Location  |Status|
  |openWeatherMap|secured |Coimbatore|200|
  |openWeatherMap|nonsecured |London |200|   