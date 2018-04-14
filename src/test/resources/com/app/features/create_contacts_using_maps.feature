Feature: Creating cnotacts
  I want to use this template for my feature file

  Background: 
    Given I logged into suiteCRM

  Scenario: Create contact using a map
    When I create a new contact:
      | first_name | John         |
      | last_name  | Smith        |
      | cell_phone | 801-888-8888 |
    Then I should see contact information for "John Smith"

  
  Scenario Outline: Create contact using a map
    When I create a new contact:
      | first_name   | <first_name>   |
      | last_name    | <lname>        |
      | cell_phone   | <cell_phone>   |
      | office_phone | <office_phone> |
    Then I should see contact information for "<first_name> <lname>"

    Examples: 
      | first_name | lname | cell_phone | office_phone |
      | Bruce      | Willis | 1131231234 | 234523456788 |
      | Bonni      | Garcia |   31231234 |   2323456788 |
      | John       | Nash   |   11312234 |     23452348 |
      | Will       | Smith  |    1131234 |        26788 |
