package us14
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



class TransactionsFilterBetweenDate {
	@When("I choose a start date")
	public void i_choose_a_start_date() {
		Mobile.tap(findTestObject('startDate'), 0)

		Mobile.tap(findTestObject('dayFrom'), 0)

		Mobile.tap(findTestObject('buttonCalendarOK'), 0)

		Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)
	}

	@When("I chosse a end date")
	public void i_chosse_a_end_date() {
		Mobile.tap(findTestObject('endDate'), 0)

		Mobile.tap(findTestObject('dayTo'), 0)

		Mobile.tap(findTestObject('buttonCalendarOK'), 0)

		Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)
	}

	@Then("I can all my transactions filtered")
	public void i_can_all_my_transactions_filtered() {
		Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

		Mobile.verifyElementExist(findTestObject('transactionsList'), 0)

		Mobile.delay(4, FailureHandling.STOP_ON_FAILURE)

		Mobile.closeApplication()
	}
}