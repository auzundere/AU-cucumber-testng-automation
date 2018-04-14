Feature: User Interface for SuiteCRM
 
  Scenario: CRM Name and Module Menu
    Given I logged into suiteCRM
    Then CRM Name should be SuiteCRM
    And Modules should be displayed
    Then logging out suiteCRM
  