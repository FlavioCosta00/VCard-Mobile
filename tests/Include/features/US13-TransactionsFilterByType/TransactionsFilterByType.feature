Feature: See transaction on Transaction Page filtered by type
  As a user
  I want to open the app
  So I can see my all transactions filtered by type

  Scenario: List of Transaction on Transaction Page filtered by type
    Given I start the application
    And I have a vCard
    And I insert my "pin code"
    And I tap the "Login" button
    And I tap the "Transactions" button
    When I select "All" in selectbox
    Then I can see all my transactions

  Scenario: Transaction list filter by Credit
    Given I start the application
    And I have a vCard
    And I insert my "pin code"
    And I tap the "Login" button
    And I tap the "Transactions" button
    When I select "Credit" in selectbox
    Then I can see all my credit transactions

  Scenario: Transaction list filter by Debit
    Given I start the application
    And I have a vCard
    And I insert my "pin code"
    And I tap the "Login" button
    And I tap the "Transactions" button
    When I select "Debit" in selectbox
    Then I can see all my debit transactions
