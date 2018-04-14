Feature: Duplicate Contacts
  As a user, I should be able to merge duplicate contacts, so that we can prevent future errors.

  Background: 
    Given I logged into suiteCRM

  @duplicates
  Scenario Outline: Merging duplicate contacts
    And duplicated contact "<firstname> <lastname>" exists
    And remove duplicates for this contact
    Then there should be only 1 John Doe in the contacts page

    Examples: 
      | firstname | lastname |
      | John      | Doe      |
