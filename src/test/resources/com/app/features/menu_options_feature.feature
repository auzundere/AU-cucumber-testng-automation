Feature: SugarCRM menu options

 @Dev
  Scenario: Verify Collaboration menu options
    Given I logged into suiteCRM
    When I hover over the Collaboration menu
    Then following menu options should be visible for Collaboration:
      | Home      |
      | Emails    |
      | Documents |
      | Projects  |
    
    Scenario: Verify Collaboration menu options
    Given I logged into suiteCRM
    When I hover over the Support menu
    Then following menu options should be visible for Support:
      | Home      |
      | Emails    |
      | Documents |
      | Projects  |
    
    #this is supposed to fail 
    @Dev
    Scenario: Verify Collaboration menu options
    Given I logged into suiteCRM
    When I hover over the Sales menu
    Then following menu options should be visible for Sales:
      | Home      |
      | Emails    |
      | Documents |
      | Projects  |
