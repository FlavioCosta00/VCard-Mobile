Feature: Enable & Disable savings
  As a user
  I want to able to enable/disable savings
  So I can save or not money in piggy bank

  Scenario: Enable savings
    Given I start the application
    And I have a vCard
    And I insert my "pin code"
    And I tap the "Login" button
    When I tap the "Settings" button
    And I tap on enable savings toggle button
    Then I can start saving money into my piggy bank

  Scenario: Disable savings
    Given I start the application
    And I have a vCard
    And I insert my "pin code"
    And I tap the "Login" button
    When I tap the "Settings" button
    And I tap on disable savings toggle button
    Then I dont save money into my piggy bank
