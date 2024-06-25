Feature: New vCard user
  As an new vCard user
  I want to create an vCard
  So that I can have access to all the features

  Scenario: Create vCard Fail
    Given I start the application
    And I dont have a vCard associated
    And I dont insert my credencials
    When I tap the "createVCard" button
    Then I can see emptyFields message

  Scenario: Create vCard Fail(Phone number already exists)
    Given I start the application
    And I dont have a vCard associated
    And I insert an phone number that already has card
    When I tap the "createVCard" button
    Then I can see Phone number already exists message

  Scenario: Create vCard Successfully
    Given I start the application
    And I dont have a vCard associated
    And I insert my credencials
    When I tap the "createVCard" button
    Then I can see vCard created message

  Scenario: Login Successfuly
    Given I start the application
    And I have a vCard associated
    And I insert my "pin code"
    When I tap the "Login" button
    Then I have access to the dashboard

  Scenario: Login Fail
    Given I start the application
    And I have a vCard associated
    And I insert the wrong "pin code"
    When I tap the "Login" button
    Then I can see Pin code error message
