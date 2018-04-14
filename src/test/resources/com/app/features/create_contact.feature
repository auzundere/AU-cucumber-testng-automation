Feature: Creating contacts
  I want to use this template for my feature file

Background:
Given I logged into suiteCRM

  Scenario: Create contact using CREATE page
    And I open the create contact page
    And I enter the first name "Abelovich" and the last name "Nash"
    And I enter the phone number "2013456789"
    And I enter the department "IT"
    When click on the save button
    Then I should see contact information for "Abe Nash"

  Scenario: Create contact using CREATE page
    And I open the create contact page
    And I enter the first name "Sarah" and the last name "Connor"
    And I enter the phone number "2013456789"
    And I enter the department "IT"
    When click on the save button
    Then I should see contact information for "Sarah Connor"

  Scenario Outline: Create multiple contacts
    And I open the create contact page
    And I enter the first name "<firstname>" and the last name "<lastname>"
    And I enter the phone number "<phonenumber>"
    And I enter the department "<department>"
    When click on the save button
    Then I should see contact information for "<firstname> <lastname>"
    Examples: 
      | firstname | lastname | phonenumber      | deaprtment  |
      | Satoshi   | Nakamuro | 9876543225364748 | IT          |
      | Maruf     | Jon      |       2021234543 | Testing     |
      | Murodil   | Ramirez  |       2102103456 | Development |
