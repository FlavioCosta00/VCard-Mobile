package us15
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



class SortListOfTransactionsStepDef {
	@When("I select {string}")
	public void i_select(String string) {
		Mobile.tap(findTestObject('Object Repository/button' + string), 0)
	}

	@Then("I can see all my transactions order by date ASC")
	public void i_can_see_all_my_transactions_order_by_date_ASC() {
		Mobile.verifyElementExist(findTestObject('transactionsList'), 0)

		Mobile.delay(4, FailureHandling.STOP_ON_FAILURE)

		Mobile.closeApplication()
	}

	@Then("I can see all my transactions order by date DESC")
	public void i_can_see_all_my_transactions_order_by_date_DESC() {
		Mobile.verifyElementExist(findTestObject('transactionsList'), 0)

		Mobile.delay(4, FailureHandling.STOP_ON_FAILURE)

		Mobile.closeApplication()
	}

	@Then("I can see all my transactions order by amount of money ASC")
	public void i_can_see_all_my_transactions_order_by_amount_of_money_ASC() {
		Mobile.verifyElementExist(findTestObject('transactionsList'), 0)

		Mobile.delay(4, FailureHandling.STOP_ON_FAILURE)

		Mobile.closeApplication()
	}

	@Then("I can see all my transactions order by amount of money DESC")
	public void i_can_see_all_my_transactions_order_by_amount_of_money_DESC() {
		Mobile.verifyElementExist(findTestObject('transactionsList'), 0)

		Mobile.delay(4, FailureHandling.STOP_ON_FAILURE)

		Mobile.closeApplication()
	}
}