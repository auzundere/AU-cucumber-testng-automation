Feature: Creating contacts using beans

  Background: 
    Given I logged into suiteCRM

  Scenario: Creating contact
    When I save a new contact:
      | firstName | lastName | department | officePhone | cellPhone | email           |
      | Steve     | Gates    | IT         |     1234567 |   7654321 | gates@apple.com |
    Then I should see contact information for "Steve Gates"

  @create_contact
  Scenario Outline: Create way more contacts
    When I save a new contact:
      | firstName | lastName | department | officePhone | cellPhone | email  |
      | <fName>   | <lName>  | <dpt>      | <oPhone>    | <cPhone>  | <mail> |
    Then I should see contact information for "<firstName> <lastName>"

    Examples: 
      | fName  | lName      | dpt    | oPhone     | clPhone     | mail                   |
      | Tom    | Hanks      | Cinema | 1234123567 | 12312441235 | hankst@cinema.com      |
      | Denzel | Washington | Cinema |   12323567 |     1441235 | washingtond@cinema.com |
