$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("C:/projetoTaes/tests/Include/features/US9-DeleteVCard/US9-DeleteVCard.feature");
formatter.feature({
  "name": "Delete vCard",
  "description": "As a user\nI want to tap on Delete vCard button\nSo I can delete my vCard",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "Delete my vCard Invalid",
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
  "name": "I verify that my balance is not 0",
  "keyword": "And "
});
formatter.match({
  "location": "US9.i_verify_that_my_balance_is(Integer)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I can see a message “Balance \u003e 0.00 Delete Card Disabled”",
  "keyword": "Then "
});
formatter.match({
  "location": "US9.i_can_see_my_a_message_Balance_Delete_Card_Disabled(Double)"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Delete my vCard valid",
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
  "name": "I verify that my balance is bigger than 0.00",
  "keyword": "And "
});
formatter.match({
  "location": "US9.i_verify_that_my_balance_is_bigger_than(Double)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I tap on   \"Delete\" button",
  "keyword": "When "
});
formatter.match({
  "location": "US9.i_tap_on_button(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I insert my   \"pin code\"",
  "keyword": "And "
});
formatter.match({
  "location": "US9.i_insert_my(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I can delete my VCard",
  "keyword": "Then "
});
formatter.match({
  "location": "US9.i_can_delete_my_VCard()"
});
formatter.result({
  "status": "passed"
});
});