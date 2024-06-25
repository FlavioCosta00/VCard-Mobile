$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("C:/projetoTaes/tests/Include/features/US7-ListOfContactsPage/ListOfContactsPage.feature");
formatter.feature({
  "name": "Contacts Page",
  "description": "As a user\nI want to tap on \"Send Money\" button\nSo I can see all the contacts",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "See all the contacts",
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
  "name": "I tap the  \"Login\" button",
  "keyword": "And "
});
formatter.match({
  "location": "US5.i_tap__the_button(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I tap on \"SendMoney\"  button",
  "keyword": "And "
});
formatter.match({
  "location": "US7.i_tap_on_button(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I can see all the contacts",
  "keyword": "Then "
});
formatter.match({
  "location": "US7.i_can_see_all_the_contacts()"
});
formatter.result({
  "status": "passed"
});
});