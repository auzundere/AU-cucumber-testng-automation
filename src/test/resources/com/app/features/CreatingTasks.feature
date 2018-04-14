
Feature: Creating tasks

@smoke @regression
  Scenario: Create a task
    Given I logged into suiteCRM
    When I click on create task
    And Set subject as "Refactor code"
    And Set status as "In Progress"
    And Start date is today's date
    And Due date is 5 days after today's date
    And Set "Medium" priority
    And Set description as "Automate test cases using Cucumber and Selenium"
    And Save the task
    Then Taks details page should be displayed 
    And Data should match with created task data
		Then logging out suiteCRM