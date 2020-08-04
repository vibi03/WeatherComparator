Feature: Compare weather information of particular location from UI and Rest-API

@test
 Scenario: Retreive weather information from NDTV website for Coimbatore
 Given User able to launch NDTV website
 Then Navigate to weather section
 When User provide location as "Coimbatore" 