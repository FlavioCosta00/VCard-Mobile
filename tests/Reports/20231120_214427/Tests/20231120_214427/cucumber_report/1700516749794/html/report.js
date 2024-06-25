$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("C:/projetoTaes/tests/Include/features/US3-CheckBalanceOnTheDashBoard/US3-CheckBalanceOnTheDashBoard.feature");
formatter.feature({
  "name": "vCard user see balance",
  "description": "As a vCard user\nI want to see my balance\nSo i know how much money i have",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "Check positive balance",
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
  "name": "I tap  the \"Login\" button",
  "keyword": "When "
});
formatter.match({
  "location": "US3AT1.i_tap_the_button(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I can see my positive balance",
  "keyword": "Then "
});
formatter.match({
  "location": "US3AT2.i_can_see_my_positive_balance()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Check balance is 0",
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
  "name": "I tap  the \"Login\" button",
  "keyword": "When "
});
formatter.match({
  "location": "US3AT1.i_tap_the_button(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I can see my banlace is \"0\"",
  "keyword": "Then "
});
formatter.match({
  "location": "US3AT1.i_can_see_my_banlace_is(String)"
});
formatter.result({
  "status": "passed"
});
});