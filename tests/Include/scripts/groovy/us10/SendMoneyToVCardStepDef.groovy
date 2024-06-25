package us10
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When



class SendMoneyToVCardStepDef {
	@When("I insert a phone number in the {string} field")
	public void i_insert_a_phone_number_in_the_field(String string) {
		Mobile.setText(findTestObject('sendMoneyPhoneNumber'), '911000002', 0)
	}

	@When("I insert a valid amount of money in the {string} field")
	public void i_insert_a_valid_amount_of_money_in_the_field(String string) {
		Mobile.setText(findTestObject('sendMoneyAmount'), '5', 0)
	}

	@Then("The money is sent to a phone number")
	public void the_money_is_sent_to_a_phone_number() {
		Mobile.setText(findTestObject('codeConfirmationDirectly'), '1234', 0)

		Mobile.tap(findTestObject('buttonSendMoneyOk'), 0)

		Mobile.delay(5, FailureHandling.STOP_ON_FAILURE)

		Mobile.closeApplication()
	}

	//Cancel
	@Then("I cancel the send money to a phone number")
	public void i_cancel_the_send_money_to_a_phone_number() {
		Mobile.setText(findTestObject('codeConfirmationDirectly'), '1234', 0)

		Mobile.tap(findTestObject('buttonSendMoneyCancel'), 0)

		Mobile.delay(5, FailureHandling.STOP_ON_FAILURE)

		Mobile.closeApplication()
	}


	//Invalid Situation
	@When("I insert an amount bigger than my balance in the {string} field")
	public void i_insert_an_amount_bigger_than_my_balance_in_the_field(String string) {
		Mobile.setText(findTestObject('sendMoneyAmount'), '1000', 0)
	}

	@Then("The money is not sent to a phone number")
	public void the_money_is_not_sent_to_a_phone_number() {
		Mobile.delay(5, FailureHandling.STOP_ON_FAILURE)

		Mobile.closeApplication()
	}

	//Select Phone Number
	@When("I chose a contact with vCard")
	public void i_chose_a_contact_with_vCard() {
		Mobile.tap(findTestObject('selectedPhoneNumber'), 0)
	}
}