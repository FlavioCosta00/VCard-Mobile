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
import org.openqa.selenium.WebElement as WebElement

CucumberKW.runFeatureFile('Include/features/US3-CheckBalanceOnTheDashBoard/US3-CheckBalanceOnTheDashBoard.feature')

/*
Mobile.startApplication('C:\\projetoTaes\\code\\app\\build\\outputs\\apk\\debug\\app-debug.apk', false)

Mobile.setText(findTestObject('null'), '1234', 0)

Mobile.tap(findTestObject('Object Repository/btn-Login'), 0)

// Replace 'YourTextViewObject' with the actual Test Object identifier representing the mobile text view
def textViewObject = findTestObject('dashBalance0')

// Get the actual value from the mobile text view
def actualValue = Mobile.getText(textViewObject, 10)

// Specify the expected value that you want to ensure is not equal
def expectedValue = '0.00 €'

// Verify that the attribute value is equal to the expected value
Mobile.verifyEqual(actualValue, expectedValue)

def maxPauseDuration = 2000 // 2 seconds

def startTime = System.currentTimeMillis()

while ((System.currentTimeMillis() - startTime) < maxPauseDuration) {
}

Mobile.closeApplication()*/

