$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("C:/projetoTaes/tests/Include/features/US1-CreateVCard/US1-CreateVCard.feature");
formatter.feature({
  "name": "New vCard user",
  "description": "As an new vCard user\nI want to create an vCard\nSo that I can have access to all the features",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "Create vCard Fail",
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
  "name": "I dont have a vCard associated",
  "keyword": "And "
});
formatter.match({
  "location": "US1.i_dont_have_a_vCard_associated()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I dont insert my credencials",
  "keyword": "And "
});
formatter.match({
  "location": "US1.i_dont_insert_my_credencials()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I tap the \"create vCard\" button",
  "keyword": "When "
});
formatter.match({
  "location": "US1.i_tap_the_button(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I can see \"empty fields\" message",
  "keyword": "Then "
});
formatter.match({
  "location": "US1.i_can_see___message(String)"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Create vCard Fail(Phone number already exists)",
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
  "name": "I dont have a vCard associated",
  "keyword": "And "
});
formatter.match({
  "location": "US1.i_dont_have_a_vCard_associated()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I insert an phone number that already has card",
  "keyword": "And "
});
formatter.match({
  "location": "US1.i_insert_an_phone_number_that_already_has_card()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I tap the \"create vCard\" button",
  "keyword": "When "
});
formatter.match({
  "location": "US1.i_tap_the_button(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I can see \"Phone number already exists\" message error",
  "keyword": "Then "
});
formatter.match({
  "location": "US1.i_can_see_message_error(String)"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Create vCard Successfully",
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
  "name": "I dont have a vCard associated",
  "keyword": "And "
});
formatter.match({
  "location": "US1.i_dont_have_a_vCard_associated()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I insert my credencials",
  "keyword": "And "
});
formatter.match({
  "location": "US1.i_insert_my_credencials()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I tap the \"create vCard\" button",
  "keyword": "When "
});
formatter.match({
  "location": "US1.i_tap_the_button(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I can see  \"vCard created\" message",
  "keyword": "Then "
});
formatter.match({
  "location": "US1.i_can_see_message(String)"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Login Successfuly",
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
  "name": "I have a vCard associated",
  "keyword": "And "
});
formatter.match({
  "location": "US1.i_have_a_vCard_associated()"
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
  "name": "I tap \"Login\" button",
  "keyword": "When "
});
formatter.match({
  "location": "US1.i_tap_button(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I have access to the dashboard",
  "keyword": "Then "
});
formatter.match({
  "location": "US1.i_have_access_to_the_dashboard()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Login Fail",
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
  "name": "I have a vCard associated",
  "keyword": "And "
});
formatter.match({
  "location": "US1.i_have_a_vCard_associated()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I insert the wrong \"pin code\"",
  "keyword": "And "
});
formatter.match({
  "location": "US1.i_insert_the_wrong(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I tap \"Login\" button",
  "keyword": "When "
});
formatter.match({
  "location": "US1.i_tap_button(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I can see   \"Pin code error\" message",
  "keyword": "Then "
});
formatter.match({
  "location": "US1.i___can_see_message(String)"
});
formatter.result({
  "status": "passed"
});
});