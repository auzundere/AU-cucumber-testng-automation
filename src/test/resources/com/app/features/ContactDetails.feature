Feature: Contact Details
  As a user, I should be able to access the contact_s
  user personal information, so that I have detailed information about that person
	

  Scenario: Verifying Contact Details
    Given I logged into suiteCRM
    And I open contact "John Doe"
    Then contact name should be "John Doe"
    And contact email should be "johnDoe@example.org"
    Then logging out suiteCRM
