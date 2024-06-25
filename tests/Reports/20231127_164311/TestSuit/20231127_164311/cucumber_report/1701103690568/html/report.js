$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("C:/projetoTaes/tests/Include/features/US8-AddNewContactToTheList/US8-AddNewContactToTheList.feature");
formatter.feature({
  "name": "Add new contact to Contact Page",
  "description": "As a user\nI want to tap on \"Send Money\" button\nSo I can see all the contacts",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "Add new contact to the contacts list success",
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
  "name": "I  tap on \"NewContact\" button",
  "keyword": "When "
});
formatter.match({
  "location": "US8.i_tap_on_button(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I insert a new contact",
  "keyword": "And "
});
formatter.match({
  "location": "US8.i_insert_a_new_contact()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "The new contact is added to the list of contacts",
  "keyword": "Then "
});
formatter.match({
  "location": "US8.the_new_contact_is_added_to_the_list_of_contacts()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Add new contact to the contacts list empty fields",
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
  "name": "I  tap on \"NewContact\" button",
  "keyword": "When "
});
formatter.match({
  "location": "US8.i_tap_on_button(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I have empty fields",
  "keyword": "And "
});
formatter.match({
  "location": "US8.i_have_empty_fields()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "i can see \"Empty fields\" message",
  "keyword": "Then "
});
formatter.match({
  "location": "US8.i_can_see_message(String)"
});
formatter.result({
  "status": "passed"
});
});