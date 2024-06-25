Feature: Contacts Page
  As a user
  I want to tap on "Send Money" button
  So I can see all the contacts

  Scenario: See all the contacts
    Given I start the application
    And I have a vCard
    And I insert my "pin code"
    And I tap the "Login" button
    And I tap the "SendMoney" button
    Then I can see all the contacts
