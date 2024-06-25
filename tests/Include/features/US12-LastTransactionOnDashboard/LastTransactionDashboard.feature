Feature: See last transaction on dashboard
  As a user
  I want to open the app
  So I can see my last transaction

  Scenario: See Last Transaction on Dashboard
    Given I start the application
    And I have a vCard
    And I insert my "pin code"
    When I tap the "Login" button
    Then I can see I dont have a transaction

  Scenario: See Last Transaction on Dashboard
    Given I start the application
    And I have a vCard
    And I insert my "pin code"
    And I tap the "Login" button
    When I create a transaction
    Then I can see my last transaction
