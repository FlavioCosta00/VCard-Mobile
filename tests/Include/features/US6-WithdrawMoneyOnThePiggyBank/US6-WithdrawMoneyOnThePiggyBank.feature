Feature: Withdraw money on Piggy bank page
  As a vCard user
  I want to click on "PiggyBank" button
  So that I can withdraw money on the piggybank

  Scenario: Withdraw a valid amount of money on the Piggy bank
    Given I start the application
    And I have a vCard
    And I insert my "pin code"
    And I tap the "Login" button
    When I tap the "PiggyBank" button
    And I insert a valid amount of money
    And I tap the "Remove" button
    Then I can see the money removed on the Piggy bank

  Scenario: Withdraw an invalid amount of money on the Piggy bank
    Given I start the application
    And I have a vCard
    And I insert my "pin code"
    And I tap the "Login" button
    When I tap the "PiggyBank" button
    And I insert an invalid amount of money
    Then I cant see "Remove" button