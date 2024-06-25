Feature: See transaction on Transaction Page
  As a user
  I want to open the app
  So I can see my all transaction

  Scenario: List of Transaction on Transaction Page
    Given I start the application
    And I have a vCard
    And I insert my "pin code"
    And I tap the "Login" button
    And I tap the "Transactions" button
    Then I can all my transactions
