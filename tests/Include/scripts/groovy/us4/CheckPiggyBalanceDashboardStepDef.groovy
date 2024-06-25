package us4

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
import io.appium.java_client.AppiumDriver



public class CheckPiggyBalanceDashboardStepDef {
	@Then("I can see my piggyBank balance")
	public void i_can_see_my_piggyBank_balance() {
		// Replace 'YourTextViewObject' with the actual Test Object identifier representing the mobile text view
		def textViewObject = findTestObject('Object Repository/dashboardPiggyBalance')

		// Get the actual value from the mobile text view
		def actualValue = Mobile.getText(textViewObject, 10)
		// Specify the expected value that you want to ensure is not equal
		def expectedValue = "0.00 €"
		println(actualValue)
		// Verify that the attribute value is equal to the expected value
		Mobile.verifyNotEqual(actualValue, expectedValue)

		def maxPauseDuration = 2000 // 2 seconds

		def startTime = System.currentTimeMillis()

		while ((System.currentTimeMillis() - startTime) < maxPauseDuration) {
		}


		Mobile.closeApplication()
	}

	@Then("I can see my piggy bank balance is {string}")
	public void i_can_see_my_piggy_bank_balance_is(String string) {
		Mobile.delay(5, FailureHandling.STOP_ON_FAILURE)

		Mobile.verifyElementText(findTestObject('Object Repository/dashPiggy0'), '0.00 €')

		Mobile.closeApplication()
	}
}
