package us19
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



class SaveRoundUpMoneyStepDef {
	@Given("I have savings enable")
	public void i_have_savings_enable() {
		Mobile.tap(findTestObject('Object Repository/btn-Settings'), 0)

		Mobile.verifyElementExist(findTestObject('Object Repository/toggleSavings'), 0)

		Mobile.tap(findTestObject('Object Repository/btn-Home'), 0)
	}

	@When("I send a non exact amount of money to a contact")
	public void i_send_a_non_exact_amount_of_money_to_a_contact() {
		Mobile.tap(findTestObject('Object Repository/btn-SendMoney'), 0)

		Mobile.tap(findTestObject('selectedPhoneNumber'), 0)

		Mobile.setText(findTestObject('sendMoneyAmount'), '1', 0)

		Mobile.tap(findTestObject('Object Repository/btn-Send'), 0)

		Mobile.setText(findTestObject('codeConfirmationDirectly'), '1234', 0)

		Mobile.tap(findTestObject('buttonSendMoneyOk'), 0)

		Mobile.delay(5, FailureHandling.STOP_ON_FAILURE)
	}

	@Then("I can see the rest of the amount of money added to my piggy bank")
	public void i_can_see_the_rest_of_the_amount_of_money_added_to_my_piggy_bank() {
		//Mobile.tap(findTestObject('Object Repository/btn-PiggyBank'), 0)

		Mobile.delay(5, FailureHandling.STOP_ON_FAILURE)

		Mobile.closeApplication()
	}
}