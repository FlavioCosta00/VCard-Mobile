package us9
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



class DeleteVCardStepDef {
	@Given("I have no balance")
	public void i_have_balance() {
		//Mobile.verifyElementExist(findTestObject('Object Repository/dashBalance0'), 0)
		Mobile.delay(4, FailureHandling.STOP_ON_FAILURE)
	}
	
	@Then("I can delete my vCard")
	public void i_can_delete_my_vCard() {
		Mobile.setText(findTestObject('Object Repository/codeDeleteVCard'), '1234', 0)
		
		Mobile.tap(findTestObject('Object Repository/buttonConfirmDeleteVCard'), 0)
		
		Mobile.delay(4, FailureHandling.STOP_ON_FAILURE)
		
		Mobile.closeApplication()
	}
	
	@Given("I have balance on my vCard")
	public void i_have_balance_on_my_vCard() {
		//Mobile.verifyElementExist(findTestObject('Object Repository/dashTotalBalance'), 0)
		Mobile.delay(4, FailureHandling.STOP_ON_FAILURE)
	}
	
	@Given("I login into the app")
	public void i_login_into_the_app() {
		Mobile.setText(findTestObject('Object Repository/txt-number'), '911666999', 0)

		Mobile.setText(findTestObject('Object Repository/txt-password'), 'qwer1234', 0)

		Mobile.setText(findTestObject('Object Repository/txt-pinCode'), '1234', 0)
		
		Mobile.tap(findTestObject('Object Repository/btn-createVCard'), 0)
	}
	
	@Then("I can disassociate my VCard")
	public void i_can_disassociate_my_VCard() {
		Mobile.setText(findTestObject('Object Repository/codeDeleteVCard'), '1234', 0)
		
		Mobile.tap(findTestObject('Object Repository/buttonConfirmDeleteVCard'), 0)
		
		Mobile.delay(4, FailureHandling.STOP_ON_FAILURE)
		
		Mobile.closeApplication()
	}
}