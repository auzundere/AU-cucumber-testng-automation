@thisone
Feature: Note on Dashboard
  
  Background: 
   Given I logged into suiteCRM
   
  Scenario: Post a note on Dashboard
    When I post "Hello Everyone by Uzundere at last"
    Then Post should be displayed
    #Then logging out suiteCRM


    Scenario: Post a note on Dashboard
    #Given I logged into suiteCRM
    When I post "Hello Everyone by Uzundere at last"
    Then Post should be displayed
    #Then logging out suiteCRM