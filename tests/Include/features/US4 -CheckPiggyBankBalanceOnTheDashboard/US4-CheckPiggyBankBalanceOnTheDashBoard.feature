Feature: vCard user see piggy bank balance
  As a vCard user
  I want to see my piggy bank balance
  So i know my savings

  Scenario: Check piggy bank balance is present
    Given I start the application
    And I have a vCard
    And I insert my "pin code"
    When I tap the "Login" button
    Then I can see my piggyBank balance

  Scenario: Check piggy bank balance is 0
    Given I start the application
    And I have a vCard
    And I insert my "pin code"
    When I tap the "Login" button
    Then I can see my piggy bank balance is "0"
