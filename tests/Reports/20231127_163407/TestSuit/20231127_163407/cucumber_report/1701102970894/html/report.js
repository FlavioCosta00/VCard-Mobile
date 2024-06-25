$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("C:/projetoTaes/tests/Include/features/US4 -CheckPiggyBankBalanceOnTheDashboard/US4-CheckPiggyBankBalanceOnTheDashBoard.feature");
formatter.feature({
  "name": "vCard user see piggy bank balance",
  "description": "As a vCard user\nI want to see my piggy bank balance\nSo i know my savings",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "Check piggy bank balance is present",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "I start the application",
  "keyword": "Given "
});
formatter.match({
  "location": "US1.i_start_the_application()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I have a vCard",
  "keyword": "And "
});
formatter.match({
  "location": "US3.i_have_a_vCard()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I insert my \"pin code\"",
  "keyword": "And "
});
formatter.match({
  "location": "US1.i_insert_my(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I tap  the \"Login\" button",
  "keyword": "When "
});
formatter.match({
  "location": "US3.i_tap_the_button(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I can see my “piggyBank” balance",
  "keyword": "Then "
});
formatter.match({
  "location": "US4.i_can_see_my_piggyBank_balance()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Check piggy bank balance is 0",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "I start the application",
  "keyword": "Given "
});
formatter.match({
  "location": "US1.i_start_the_application()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I have a vCard",
  "keyword": "And "
});
formatter.match({
  "location": "US3.i_have_a_vCard()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I insert my \"pin code\"",
  "keyword": "And "
});
formatter.match({
  "location": "US1.i_insert_my(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I tap  the \"Login\" button",
  "keyword": "When "
});
formatter.match({
  "location": "US3.i_tap_the_button(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I can see my piggy bank balance is \"0.00 €\"",
  "keyword": "Then "
});
formatter.match({
  "location": "US4.i_can_see_my_piggy_bank_balance_is(String)"
});
formatter.result({
  "status": "passed"
});
});