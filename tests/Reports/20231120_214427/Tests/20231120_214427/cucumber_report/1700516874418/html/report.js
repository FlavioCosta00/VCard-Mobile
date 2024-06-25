$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("C:/projetoTaes/tests/Include/features/US5-DepositMoneyOnThePiggyBank/DepositMoneyOnThePiggyBank.feature");
formatter.feature({
  "name": "Deposit money on Piggy bank page",
  "description": "As a vCard user\nI want to click on \"PiggyBank\" button\nSo that I can deposit money on the piggybank",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "Deposit a valid amount of money on the Piggy bank",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "I start the application",
  "keyword": "Given "
});
formatter.match({
  "location": "US1AT1.i_start_the_application()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I have a vCard",
  "keyword": "And "
});
formatter.match({
  "location": "US3AT1.i_have_a_vCard()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I insert my \"pin code\"",
  "keyword": "And "
});
formatter.match({
  "location": "US1AT4.i_insert_my(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I tap the  \"Login\" button",
  "keyword": "And "
});
formatter.match({
  "location": "us5.i_tap__the_button(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I tap the  \"PiggyBank\"  button",
  "keyword": "When "
});
formatter.match({
  "location": "us5.i_tap__the__button(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I insert a valid amount of money",
  "keyword": "And "
});
formatter.match({
  "location": "us5.i_insert_a_valid_amount_of_money()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I tap on \"Add\" button",
  "keyword": "And "
});
formatter.match({
  "location": "us5.i_tap_on_button(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I can see the money addded on the Piggy bank",
  "keyword": "Then "
});
formatter.match({
  "location": "us5.i_can_see_the_money_addded_on_the_Piggy_bank()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Deposit an invalid amount of money on the Piggy bank",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "I start the application",
  "keyword": "Given "
});
formatter.match({
  "location": "US1AT1.i_start_the_application()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I have a vCard",
  "keyword": "And "
});
formatter.match({
  "location": "US3AT1.i_have_a_vCard()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I insert my \"pin code\"",
  "keyword": "And "
});
formatter.match({
  "location": "US1AT4.i_insert_my(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I tap the  \"Login\" button",
  "keyword": "And "
});
formatter.match({
  "location": "us5.i_tap__the_button(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I tap the  \"PiggyBank\"  button",
  "keyword": "When "
});
formatter.match({
  "location": "us5.i_tap__the__button(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I insert an invalid amount of money",
  "keyword": "And "
});
formatter.match({
  "location": "us5.i_insert_an_invalid_amount_of_money()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I can’t see the “Add” button",
  "keyword": "Then "
});
formatter.match({
  "location": "us5.i_can_t_see_the_Add_button()"
});
formatter.result({
  "status": "passed"
});
});