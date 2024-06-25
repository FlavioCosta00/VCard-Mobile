Feature: Send Money to another vCard
  As a user
  I want to open the app
  So I can send money to another vCard

  #Directly to a Phone Number
  Scenario: Send Money directly to a phone number Valid Situation
    Given I start the application
    And I have a vCard
    And I insert my "pin code"
    And I tap the "Login" button
    And I tap the "SendMoney" button
    When I insert a phone number in the "PhoneNumber" field
    And I tap the "SendMoneyDirectly" button
    And I insert a valid amount of money in the "AmountOfMoney" field
    And I tap the "Send" button
    Then The money is sent to a phone number

  Scenario: Send Money directly to a phone number Cancel Situation
    Given I start the application
    And I have a vCard
    And I insert my "pin code"
    And I tap the "Login" button
    And I tap the "SendMoney" button
    When I insert a phone number in the "PhoneNumber" field
    And I tap the "SendMoneyDirectly" button
    And I insert a valid amount of money in the "AmountOfMoney" field
    And I tap the "Send" button
    Then I cancel the send money to a phone number

  Scenario: Send Money directly to a phone number Invalid Situation
    Given I start the application
    And I have a vCard
    And I insert my "pin code"
    And I tap the "Login" button
    And I tap the "SendMoney" button
    When I insert a phone number in the "PhoneNumber" field
    And I tap the "SendMoneyDirectly" button
    And I insert an amount bigger than my balance in the "AmountOfMoney" field
    And I tap the "Send" button
    Then The money is not sent to a phone number


  #Selected Phone Number
  Scenario: Send Money to a contact with vCard Valid
    Given I start the application
    And I have a vCard
    And I insert my "pin code"
    And I tap the "Login" button
    And I tap the "SendMoney" button
    When I chose a contact with vCard
    And I insert a valid amount of money in the "AmountOfMoney" field
    And I tap the "Send" button
    Then The money is sent to a phone number

  Scenario: Send Money to a contact with vCard Cancel Situation
    Given I start the application
    And I have a vCard
    And I insert my "pin code"
    And I tap the "Login" button
    And I tap the "SendMoney" button
    When I chose a contact with vCard
    And I insert a valid amount of money in the "AmountOfMoney" field
    And I tap the "Send" button
    Then I cancel the send money to a phone number

  Scenario: Send Money to a contact with vCard Invalid Situation
    Given I start the application
    And I have a vCard
    And I insert my "pin code"
    And I tap the "Login" button
    And I tap the "SendMoney" button
    When I chose a contact with vCard
    And I insert an amount bigger than my balance in the "AmountOfMoney" field
    And I tap the "Send" button
    Then The money is not sent to a phone number
