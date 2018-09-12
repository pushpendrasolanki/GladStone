package com.test.mainlinetestvismc.pages;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import com.test.mainlinetestvismc.utilities.*;

import java.io.IOException;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(GenerateReport.class)

public class Launch {
	WebDriver driver = null;
	Logger log = null;
	@BeforeTest
	public void driversetUp() {
		System.setProperty("webdriver.gecko.driver", "C:/Software/Selenium/Selenium_3/geckodriver.exe");

		driver = new FirefoxDriver();
		driver.get("http://google.com");

	}

	

	@Test
	public void launchpad() throws IOException {
		log = Logger.getLogger("devpinoyLogger");

		PropertyConfigurator.configure(
				"C:\\Software\\Selenium_workspace\\Vismc\\src\\com\\test\\mainlinetestvismc\\repository\\Log4j.properties");

		log.debug("Entering Launch for extraordinary journey");

		ExcelReader excelReader = new ExcelReader(
				"C:/Software/Selenium_workspace/Vismc/src/com/test/mainlinetestvismc/repository/TestData.xlsx");
		int row = excelReader.getRowCount("OrderDetail");
		int col = excelReader.getColumnCount("OrderDetail");
		int Patientcol = excelReader.getColumnCount("PatientDetail");
		String str = excelReader.getCellData("OrderDetail", row - 1, col - 2);
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		AddPatientPage addPatientPage = PageFactory.initElements(driver, AddPatientPage.class);
		HashMap<String, String> logindata = new HashMap<String, String>();
		HashMap<String, String> patientdata = new HashMap<String, String>();

		log.debug("Fetching the data from excel");
		for (int i = 0; i < col; i++) {
			logindata.put(excelReader.getCellData("OrderDetail", 0, i), excelReader.getCellData("OrderDetail", 1, i));
		}
		for (int i = 0; i < Patientcol; i++) {
			patientdata.put(excelReader.getCellData("PatientDetail", 0, i),
					excelReader.getCellData("PatientDetail", 1, i));

		}

		log.debug("Entering login Entering details");
		AssertJUnit.assertTrue(loginPage.enterLoginDetail(logindata));

		log.debug("Navigating to patient enter details");
		try {
			AssertJUnit.assertTrue(addPatientPage.userNavigateToAddPage());

			addPatientPage.savePatient(patientdata);
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
