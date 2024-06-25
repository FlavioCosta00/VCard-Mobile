Feature: See transaction on Transaction Page filtered between dates
  As a user
  I want to open the app
  So I can see my all transactions filtered between dates

  Scenario: List of Transaction on Transaction Page filtered between dates
    Given I start the application
    And I have a vCard
    And I insert my "pin code"
    And I tap the "Login" button
    When I tap the "Transactions" button
    And I choose a start date
    And I chosse a end date
    And I tap the "Filter" button
    Then I can all my transactions filtered
