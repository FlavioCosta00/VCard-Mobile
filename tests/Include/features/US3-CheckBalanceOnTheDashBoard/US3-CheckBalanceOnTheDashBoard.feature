Feature: vCard user see balance
  As a vCard user
  I want to see my balance
  So i know how much money i have

  Scenario: Check positive balance
    Given I start the application
    And I have a vCard
    And I insert my "pin code"
    When I tap the "Login" button
    Then I can see my positive balance

  Scenario: Check balance is 0
    Given I start the application
    And I have a vCard
    And I insert my "pin code"
    When I tap the "Login" button
    Then I can see my banlace is "0"
