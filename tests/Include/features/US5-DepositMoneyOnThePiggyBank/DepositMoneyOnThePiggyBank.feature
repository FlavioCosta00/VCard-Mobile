Feature: Deposit money on Piggy bank page
  As a vCard user
  I want to click on "PiggyBank" button
  So that I can deposit money on the piggybank

  Scenario: Deposit a valid amount of money on the Piggy bank
    Given I start the application
    And I have a vCard
    And I insert my "pin code"
    And I tap the "Login" button
    When I tap the "PiggyBank" button
    And I insert a valid amount of money
    And I tap the "Add" button
    Then I can see the money addded on the Piggy bank

  Scenario: Deposit an invalid amount of money on the Piggy bank
    Given I start the application
    And I have a vCard
    And I insert my "pin code"
    And I tap the "Login" button
    When I tap the "PiggyBank" button
    And I insert an invalid amount of money
    Then I cant see the "Add" button
