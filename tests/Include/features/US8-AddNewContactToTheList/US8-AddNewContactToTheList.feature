Feature: Add new contact to Contact Page
  As a user
  I want to tap on "Send Money" button
  So I can see all the contacts

  Scenario: Add new contact to the contacts list success
    Given I start the application
    And I have a vCard
    And I insert my "pin code"
    And I tap the "Login" button
    And I tap the "SendMoney" button
    When I tap the "NewContact" button
    And I insert a new contact
    Then The new contact is added to the list of contacts

  Scenario: Add new contact to the contacts list empty fields
    Given I start the application
    And I have a vCard
    And I insert my "pin code"
    And I tap the "Login" button
    And I tap the "SendMoney" button
    When I tap the "NewContact" button
    And I have empty fields
    Then I can see Empty fields message
