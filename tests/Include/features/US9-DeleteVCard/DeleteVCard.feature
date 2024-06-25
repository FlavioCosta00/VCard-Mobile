Feature: Delete vCard
  As a user
  I want to tap the Disassociate button
  So I can delete my vCard

  Scenario: Disassociate my vCard
    Given I start the application
    And I have a vCard
    And I insert my "pin code"
    And I tap the "Login" button
    And I have balance on my vCard
    When I tap the "Settings" button
    And I tap the "Disassociate" button
    Then I can disassociate my VCard

  Scenario: Delete my vCard valid
    Given I start the application
    And I have a vCard
    And I login into the app
    And I have no balance
    When I tap the "Settings" button
    And I tap the "Disassociate" button
    And I tap the "Delete" button
    Then I can delete my vCard
