Feature: See transaction on Transaction Page filtered by ASC/DESC
  As a user
  I want to open the app
  So I can see my all transactions filtered by asc/desc

  Scenario: List of Transaction on Transaction Page filtered by Date ASC
    Given I start the application
    And I have a vCard
    And I insert my "pin code"
    And I tap the "Login" button
    And I tap the "Transactions" button
    When I select "DateASC"
    Then I can see all my transactions order by date ASC

  Scenario: List of Transaction on Transaction Page filtered by Date DESC
    Given I start the application
    And I have a vCard
    And I insert my "pin code"
    And I tap the "Login" button
    And I tap the "Transactions" button
    When I select "DateDESC"
    Then I can see all my transactions order by date DESC

  Scenario: Transaction list filter by amount of money ASC
    Given I start the application
    And I have a vCard
    And I insert my "pin code"
    And I tap the "Login" button
    And I tap the "Transactions" button
    When I select "MoneyASC"
    Then I can see all my transactions order by amount of money ASC

  Scenario: Transaction list filter by amount of money DESC
    Given I start the application
    And I have a vCard
    And I insert my "pin code"
    And I tap the "Login" button
    And I tap the "Transactions" button
    When I select "MoneyDESC"
    Then I can see all my transactions order by amount of money DESC
