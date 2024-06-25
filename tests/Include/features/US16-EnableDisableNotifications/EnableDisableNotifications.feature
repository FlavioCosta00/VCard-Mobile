Feature: Enable & Disable notifications
  As a user
  I want to able to enable/disable notifications
  So I can know or not when I received money

  Scenario: Enable notifications
    Given I start the application
    And I have a vCard
    And I insert my "pin code"
    And I tap the "Login" button
    When I tap the "Settings" button
    And I tap on enable toggle button
    Then I can see the notification pop-up of the money that i received
    
   Scenario: Disable notifications
    Given I start the application
    And I have a vCard
    And I insert my "pin code"
    And I tap the "Login" button
    When I tap the "Settings" button
    And I tap on disable toggle button
    Then I dont see the notification pop-up of the money that i received


