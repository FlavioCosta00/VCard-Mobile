package us8
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
class AddNewContactStepDef {

	@When("I insert a new contact")
	public void i_insert_a_new_contact() {
		Mobile.setText(findTestObject('Object Repository/newContactName'), 'Cristiano Ronaldo', 0)

		Mobile.setText(findTestObject('Object Repository/newContactPhoneNumber'), '920000007', 0)

		Mobile.tap(findTestObject('Object Repository/addNewContact'), 0)
	}

	@Then("The new contact is added to the list of contacts")
	public void the_new_contact_is_added_to_the_list_of_contacts() {

		Mobile.delay(5, FailureHandling.STOP_ON_FAILURE)

		Mobile.tap(findTestObject('Object Repository/contactList'), 0)

		Mobile.closeApplication()
	}

	@When("I have empty fields")
	public void i_have_empty_fields() {
		Mobile.tap(findTestObject('Object Repository/addNewContact'), 0)
	}

	@Then("I can see Empty fields message")
	public void i_can_see_Empty_fields_message() {
		AppiumDriver<?> driver = MobileDriverFactory.getDriver()
		def toast = driver.findElementByXPath("//android.widget.Toast[@text='Please fill all fields']")
		println("Toast element: " + toast)
		if (toast == null) {
			KeywordUtil.markFailed('ERROR: Toast object not found!')
		}

		Mobile.closeApplication()
	}
}