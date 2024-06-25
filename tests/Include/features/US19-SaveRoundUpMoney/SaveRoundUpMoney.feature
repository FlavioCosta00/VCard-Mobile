Feature: Saving money
  As a user
  I want to send a non exact amount of money to a contact
  So I can save the rest of the amount of money into my piggy bank

  Scenario: Save money to piggy bank after sending money
    Given I start the application
    And I have a vCard
    And I insert my "pin code"
    And I tap the "Login" button
    And I have savings enable
    When I send a non exact amount of money to a contact
    Then I can see the rest of the amount of money added to my piggy bank
