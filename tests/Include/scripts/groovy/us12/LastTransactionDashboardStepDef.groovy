package us12
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



class LastTransactionDashboardStepDef {
	@Then("I can see I dont have a transaction")
	public void i_can_see_I_dont_have_a_transaction() {
		Mobile.delay(4, FailureHandling.STOP_ON_FAILURE)
		
		Mobile.verifyElementExist(findTestObject('dashboardNOTransactions'), 0)
		
		Mobile.closeApplication()
	}
	
	@When("I create a transaction")
	public void i_create_a_transaction() {
		Mobile.delay(4, FailureHandling.STOP_ON_FAILURE)
		
		Mobile.tap(findTestObject('btn-SendMoney'), 0)
		
		Mobile.setText(findTestObject('sendMoneyPhoneNumber'), '910333444', 0)
		
		Mobile.tap(findTestObject('btn-SendMoneyDirectly'), 0)
		
		Mobile.setText(findTestObject('sendMoneyAmount'), '0.5', 0)
		
		Mobile.tap(findTestObject('btn-Send'), 0)
		
		Mobile.setText(findTestObject('codeConfirmationDirectly'), '1234', 0)
		
		Mobile.tap(findTestObject('buttonSendMoneyOk'), 0)
	}
	
	@Then("I can see my last transaction")
	public void i_can_see_my_last_transaction() {
		Mobile.delay(4, FailureHandling.STOP_ON_FAILURE)
		
		Mobile.verifyElementExist(findTestObject('lastTransactionArea'), 0)
		
		Mobile.closeApplication()
	}
}