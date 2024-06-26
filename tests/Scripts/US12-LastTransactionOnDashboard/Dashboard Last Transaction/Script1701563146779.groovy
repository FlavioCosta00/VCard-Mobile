import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

Mobile.startApplication('S:\\TAES\\Projeto\\tests\\AppForTest\\app-release.apk', false)

Mobile.setText(findTestObject('Object Repository/codeConfirmation'), '1234', 0)

Mobile.tap(findTestObject('btn-Login'), 0)

Mobile.delay(4, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('btn-SendMoney'), 0)

Mobile.setText(findTestObject('sendMoneyPhoneNumber'), '910333444', 0)

Mobile.tap(findTestObject('btn-SendMoneyDirectly'), 0)

Mobile.setText(findTestObject('sendMoneyAmount'), '0.5', 0)

Mobile.tap(findTestObject('btn-Send'), 0)

Mobile.setText(findTestObject('codeConfirmationDirectly'), '1234', 0)

Mobile.tap(findTestObject('buttonSendMoneyOk'), 0)

Mobile.delay(4, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementExist(findTestObject('lastTransactionArea'), 0)

Mobile.closeApplication()
