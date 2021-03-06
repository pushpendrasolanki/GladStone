package com.test.mainlinetestvismc.testcases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.mainlinetestvismc.pages.AddPatientPage;
import com.test.mainlinetestvismc.pages.LoginPage;
import com.test.mainlinetestvismc.utilities.ExcelReader;
import com.test.mainlinetestvismc.utilities.GlobalMethods;


public class StartApplicationAndLoginTest {


	WebDriver driver = null;
	public static Logger log = null;
	File workingDir = new File("");
	
	@BeforeTest
	public void driversetUp() throws IOException {
		DesiredCapabilities capablity=  DesiredCapabilities.firefox();
		capablity.setBrowserName("firefox");
	//	driver=new RemoteWebDriver(new URL("http://172.20.32.1:4444/wd/hub"), capablity);
		
		System.setProperty("webdriver.gecko.driver", workingDir.getAbsolutePath()+"/geckodriver.exe");

		log = Logger.getLogger("devpinoyLogger");
		driver = new FirefoxDriver();
		driver.get("https://indiastaging.vismc.com/gladstone/portal/bloom/login/pages/login_userName.html");
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		GlobalMethods.updateName();
	}

	@Test
	public void launchpad() throws IOException, InterruptedException {
		
		boolean errorverification[]={true};
		log.debug("Launching Application: Gladstone : Portal");
		PropertyConfigurator.configure(workingDir.getAbsolutePath()+
				"/src/test/java/com/test/mainlinetestvismc/repository/Log4j.properties");

		log.debug("Reading data from Excel");
		
		ExcelReader excelReader = new ExcelReader(workingDir.getAbsolutePath()+
				"/src/test/java/com/test/mainlinetestvismc/repository/Test.xlsx");

		int col = excelReader.getColumnCount("OrderDetail");
		int Patientcol = excelReader.getColumnCount("PatientDetail");
		// This is the way to access excel data.
		// String str = excelReader.getCellData("OrderDetail", row - 1, col - 2);
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		AddPatientPage addPatientPage = PageFactory.initElements(driver, AddPatientPage.class);
		HashMap<String, String> logindata = new HashMap<String, String>();
		HashMap<String, String> patientdata = new HashMap<String, String>();
		HashMap<String, String> inlogindata = new HashMap<String, String>();

		log.debug("Fetching the data from excel");
		for (int i = 0; i < col; i++) {
			logindata.put(excelReader.getCellData("OrderDetail", 0, i), excelReader.getCellData("OrderDetail", 1, i));

			inlogindata.put(excelReader.getCellData("InvalidLoginDetail", 0, i), excelReader.getCellData("InvalidLoginDetail", 1, i));
		
		}
		for (int i = 0; i < Patientcol; i++) {
			patientdata.put(excelReader.getCellData("PatientDetail", 0, i),
					excelReader.getCellData("PatientDetail", 1, i));

		}

		log.debug("Entering invalid login Entering details");
		AssertJUnit.assertFalse("Login fail",loginPage.enterLoginDetail(inlogindata, errorverification));

		Thread.sleep(5000);
		driver.navigate().back();
		
		Thread.sleep(5000);
		
		log.debug("Entering valid login Entering details");
		AssertJUnit.assertTrue(loginPage.enterLoginDetail(logindata));

		log.debug("Navigating to Patient page to enter details");
		try {
			AssertJUnit.assertTrue(addPatientPage.userNavigateToAddPage());
			log.debug("Entering the patient detail, and Saving the patient detail.");
			addPatientPage.savePatient(patientdata);
			log.debug("Verifying the patient detail.");
			addPatientPage.verifyPatient(patientdata);
		} catch (Exception e) {
			AssertJUnit.assertTrue(false);
			e.printStackTrace();
		}

	}

	@AfterTest
	public void closeScript() {
		driver.close();
	}


}
