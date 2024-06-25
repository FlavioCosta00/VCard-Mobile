package us1
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


class CreateVCardStepDef {
	@Given("I start the application")
	public void i_start_the_application() {
		Mobile.startApplication('appForTest\\app-release.apk', false)
	}

	@Given("I dont have a vCard associated")
	public void i_dont_have_a_vCard_associated() {
		return
	}

	@Given("I dont insert my credencials")
	public void i_dont_insert_my_credencials() {
		return
	}

	@When("I tap the {string} button")
	public void i_tap_the_button(String string) {
		Mobile.tap(findTestObject('Object Repository/btn-' + string), 0)
	}

	@Then("I can see emptyFields message")
	public void i_can_see_emptyFields_message() {
		sleep(3000)

		AppiumDriver<?> driver = MobileDriverFactory.getDriver()
		def toast = driver.findElementByXPath("//android.widget.Toast[@text='Please fill all fields']")
		println("Toast element: " + toast)
		if (toast == null) {
			KeywordUtil.markFailed('ERROR: Toast object not found!')
		}
		Mobile.closeApplication()
	}

	@Given("I insert an phone number that already has card")
	public void i_insert_an_phone_number_that_already_has_card() {
		Mobile.setText(findTestObject('Object Repository/txt-number'), '911000000', 0)

		Mobile.setText(findTestObject('Object Repository/txt-password'), '123', 0)

		Mobile.setText(findTestObject('Object Repository/txt-pinCode'), '1234', 0)
	}

	@Then("I can see Phone number already exists message")
	public void i_can_see_Phone_number_already_exists_message() {
		sleep(3000)

		AppiumDriver<?> driver = MobileDriverFactory.getDriver()
		def toast = driver.findElementByXPath("//android.widget.Toast[@text='Phone number already exists']")
		println("Toast element: " + toast)
		if (toast == null) {
			KeywordUtil.markFailed('ERROR: Toast object not found!')
		}
		Mobile.closeApplication()
	}

	@Given("I insert my credencials")
	public void i_insert_my_credencials() {
		Mobile.setText(findTestObject('Object Repository/txt-number'), '911000001', 0)

		Mobile.setText(findTestObject('Object Repository/txt-password'), 'qwer1234', 0)

		Mobile.setText(findTestObject('Object Repository/txt-pinCode'), '1234', 0)
	}

	@Then("I can see vCard created message")
	public void i_can_see_vCard_created_message() {
		sleep(3000)

		AppiumDriver<?> driver = MobileDriverFactory.getDriver()
		def toast1 = driver.findElementByXPath("//android.widget.Toast[@text='Card created sucessfully']")
		println("Toast element: " + toast1)
		if (toast1 == null) {
			KeywordUtil.markFailed('ERROR: Toast object not found!')
		}
		Mobile.closeApplication()
	}

	@Given("I have a vCard associated")
	public void i_have_a_vCard_associated() {
		return
	}

	@Given("I insert my {string}")
	public void i_insert_my(String string) {
		Mobile.setText(findTestObject('Object Repository/codeConfirmation'), '1234', 0)
	}


	@Then("I have access to the dashboard")
	public void i_have_access_to_the_dashboard() {
		sleep(3000)

		AppiumDriver<?> driver = MobileDriverFactory.getDriver()
		def toast1 = driver.findElementByXPath("//android.widget.Toast[@text='Logged with success !']")
		println("Toast element: " + toast1)
		if (toast1 == null) {
			KeywordUtil.markFailed('ERROR: Toast object not found!')
		}
		Mobile.closeApplication()
	}

	@Given("I insert the wrong {string}")
	public void i_insert_the_wrong(String string) {
		Mobile.setText(findTestObject('Object Repository/codeConfirmation'), '1111', 0)
	}

	@Then("I can see Pin code error message")
	public void i_can_see_Pin_code_error_message() {
		sleep(3000)

		AppiumDriver<?> driver = MobileDriverFactory.getDriver()
		def toast = driver.findElementByXPath('//android.widget.Toast[@text=\'PIN is invalid\']')
		println('Toast element: ' + toast)
		if (toast == null) {
			KeywordUtil.markFailed('ERROR: Toast object not found!')
		}
		Mobile.closeApplication()
	}
}