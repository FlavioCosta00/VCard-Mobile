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
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import io.appium.java_client.AppiumDriver
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import io.appium.java_client.AppiumDriver
import com.kms.katalon.core.util.KeywordUtil

CucumberKW.runFeatureFile('Include/features/US1-CreateVCard/AT4-LoginSucessfull.feature')


/*
Mobile.startApplication('C:\\projetoTaes\\code\\app\\build\\outputs\\apk\\debug\\app-debug.apk', false)

Mobile.setText(findTestObject('null'), '911000001', 0)

Mobile.setText(findTestObject('null'), '123', 0)

Mobile.setText(findTestObject('null'), '1234', 0)

Mobile.tap(findTestObject('null'), 0)

// Set the maximum pause duration in milliseconds
def maxPauseDuration = 2000 // 2 seconds
def startTime = System.currentTimeMillis()

while (System.currentTimeMillis() - startTime < maxPauseDuration) {
}

Mobile.closeApplication()*/

/*
Mobile.startApplication('C:\\projetoTaes\\code\\app\\build\\outputs\\apk\\debug\\app-debug.apk', false)

Mobile.setText(findTestObject('Object Repository/btn-pinCode'), '1234', 0)

Mobile.tap(findTestObject('Object Repository/btn-Login'), 0)

AppiumDriver<?> driver = MobileDriverFactory.getDriver()
def toast1 = driver.findElementByXPath("//android.widget.Toast[@text='Logged with success !']")
println("Toast element: " + toast1)
if (toast1 == null) {
	KeywordUtil.markFailed('ERROR: Toast object not found!')
}

Mobile.closeApplication()*/

