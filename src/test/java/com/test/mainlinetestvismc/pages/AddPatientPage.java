package com.test.mainlinetestvismc.pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.test.mainlinetestvismc.testcases.StartApplicationAndLoginTest;

public class AddPatientPage {

	private WebDriver driver;
	private String title = "MAINLINETEST";

	public AddPatientPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(xpath = "/html/body/div[1]/div[3]/div[2]/div[1]/div/ul/li[3]/a/span")
	public WebElement addPatienttab;

	@FindBy(xpath = "//*[@id=\"DASHBOARD_TEXT_PORTAL_ADD_PATIENT\"]")
	public WebElement addPatient;

	@FindBy(id = "firstName")
	public WebElement firstName;

	@FindBy(id = "initName")
	public WebElement initName;

	@FindBy(id = "lastName")
	public WebElement lastName;
	@FindBy(id = "prefName")
	public WebElement preferredName;

	@FindBy(id = "dob")
	public WebElement dateofbirth;

	@FindBy(id = "gender")
	public WebElement gender;

	@FindBy(id = "radios-0")
	public WebElement maleGender;
	@FindBy(id = "radios-1")
	public WebElement feMaleGender;
	@FindBy(id = "isActive")
	public WebElement isActive;
	@FindBy(id = "institutions")
	public WebElement institutions;
	@FindBy(id = "addressLine")
	public WebElement addressLine;
	@FindBy(id = "addressLine2")
	public WebElement addressLine2;
	@FindBy(id = "city")
	public WebElement city;
	@FindBy(id = "state")
	public WebElement state;
	@FindBy(id = "zip")
	public WebElement zip;
	@FindBy(id = "country")
	public WebElement country;
	@FindBy(id = "timeZone")
	public WebElement timeZone;
	@FindBy(id = "DASHBOARD_TEXT_EMAIL")
	public WebElement emailPreffered;
	@FindBy(id = "DASHBOARD_TEXT_PHONE")
	public WebElement phonePreffered;

	@FindBy(id = "hPhone")
	public WebElement homePhone;
	@FindBy(id = "cPhone")
	public WebElement companyPhone;
	@FindBy(id = "wPhone")
	public WebElement workPhone;
	@FindBy(id = "phonePrimary")
	public WebElement phonePrimary;
	@FindBy(id = "phonePrimary")
	public WebElement phonePrimary1;
	@FindBy(id = "phonePrimary2")
	public WebElement phonePrimary2;
	@FindBy(id = "email")
	public WebElement email;
	@FindBy(id = "sEmail")
	public WebElement sEmail;
	@FindBy(id = "password")
	public WebElement password;
	@FindBy(id = "survey")
	public WebElement survey;
	@FindBy(id = "SURVEY_STAGE__MORNING")
	public WebElement SURVEY_STAGE__MORNING;
	@FindBy(id = "SURVEY_STAGE__NOON")
	public WebElement SURVEY_STAGE__NOON;
	@FindBy(id = "SURVEY_STAGE__EVENING")
	public WebElement SURVEY_STAGE__EVENING;

	@FindBy(id = "primaryProv")
	public WebElement primaryProv;

	@FindBy(id = "secondaryProv")
	public WebElement secondaryProv;

	@FindBy(id = "tertiaryProv")
	public WebElement tertiaryProv;

	@FindBy(id = "btnSave")
	public WebElement button_Save;

	@FindBy(id = "btnCancel")
	public WebElement button_Cancel;

	@FindBy(xpath = "/html/body/div[1]/div[3]/div[1]/aside/div/div/div[1]/div/span[2]")
	public WebElement patientAdded;

	@FindBy(xpath = "/html/body/div[1]/div[3]/div[1]/aside/div/div/div[1]/div/span[1]")
	public WebElement patientBack;

	@FindBy(xpath = "/html/body/div[1]/div[3]/div[2]/div[2]/div[1]/div[1]")
	public WebElement patientgrid;

	// This method will click on add button at patient
	public boolean userNavigateToAddPage() throws InterruptedException {

		Thread.sleep(55000);
		if (addPatienttab.isDisplayed()) {
			addPatienttab.click();
			Thread.sleep(15000);

			return true;
		}

		else if (driver.findElement(By.cssSelector(
				"html body div.wrapper2 div#menu-content-container.col-sm-12.nopadding div#page-content.page-content div#menu-content.login_patient_list-1 div.col-md-4 div.sidebar-filter.p-md button#DASHBOARD_TEXT_PORTAL_ADD_PATIENT.btn.btn-secondary.btn-block"))
				.isDisplayed()) {
			driver.findElement(By.cssSelector(
					"html body div.wrapper2 div#menu-content-container.col-sm-12.nopadding div#page-content.page-content div#menu-content.login_patient_list-1 div.col-md-4 div.sidebar-filter.p-md button#DASHBOARD_TEXT_PORTAL_ADD_PATIENT.btn.btn-secondary.btn-block"))
					.click();
			Thread.sleep(15000);
			if (!firstName.isDisplayed()) {
				return false;
			}
			return true;
		}

		else {
			return false;
		}

	}

	public void savePatient(HashMap<String, String> patientdata) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		Random rand = new Random();
		int value = rand.nextInt(50);
		int number = 999999900;
		firstName.clear();
		Thread.sleep(2000);
		firstName.sendKeys(patientdata.get("FirstName"));

		initName.clear();
		initName.sendKeys(patientdata.get("InitName"));
		lastName.clear();
		lastName.sendKeys(patientdata.get("LastName"));
		preferredName.clear();
		preferredName.sendKeys(patientdata.get("PrefName"));
		dateofbirth.clear();
		dateofbirth.sendKeys(patientdata.get("DateofBirth"));
		// if (patientdata.get("Gender").equals("Male")) {
		// maleGender.click();
		// } else {
		// feMaleGender.click();
		// }

		Select gender1 = new Select(gender);
		gender1.selectByVisibleText(patientdata.get("Gender"));

		Select activestate = new Select(isActive);
		activestate.selectByVisibleText(patientdata.get("IsActive"));

		Select dropinstitute = new Select(institutions);
		dropinstitute.selectByVisibleText(patientdata.get("Institutions"));

		addressLine.clear();
		addressLine.sendKeys(patientdata.get("AddressLine"));
		addressLine2.clear();
		addressLine2.sendKeys(patientdata.get("AddressLine2"));
		city.clear();
		city.sendKeys(patientdata.get("City"));

		Select dropstate = new Select(state);
		dropstate.selectByVisibleText(patientdata.get("State"));
		zip.clear();
		zip.sendKeys(patientdata.get("Zip"));
		Select dropcountry = new Select(country);
		dropcountry.selectByVisibleText(patientdata.get("Country"));
		timeZone.sendKeys(patientdata.get("TimeZone"));
		if (patientdata.get("Preference").equals("Email")) {
			emailPreffered.click();
		} else if (patientdata.get("Preference").equals("Phone")) {
			phonePreffered.click();
		} else {
			System.out.println("Incorrect option");
		}

		homePhone.clear();
		homePhone.sendKeys(String.valueOf((number * 10) + 1));
		companyPhone.clear();
		companyPhone.sendKeys(String.valueOf((number * 10) + 2));
		workPhone.clear();
		workPhone.sendKeys(String.valueOf((number * 10) + 3));
		if (patientdata.get("HomePhonePrimary").equals("yes"))
			phonePrimary.click();
		if (patientdata.get("CompanyPhonePrimary").equals("yes"))
			phonePrimary1.click();
		if (patientdata.get("WorkPhonePrimary").equals("yes"))
			phonePrimary2.click();

		email.clear();
		email.sendKeys(patientdata.get("Email") + value);
		sEmail.clear();
		sEmail.sendKeys(patientdata.get("SecondaryEmail") + value);
		password.clear();
		password.sendKeys(patientdata.get("Password"));

		if (patientdata.get("MORNING").equals("yes"))
			SURVEY_STAGE__MORNING.click();
		if (patientdata.get("NOON").equals("yes"))
			SURVEY_STAGE__NOON.click();
		if (patientdata.get("EVENING").equals("yes"))
			SURVEY_STAGE__EVENING.click();

		Select dropprimaryprov = new Select(primaryProv);
		dropprimaryprov.selectByIndex(1);
		Select dropsecondaryprov = new Select(secondaryProv);
		dropsecondaryprov.selectByIndex(2);
		Select droptertiaryProv = new Select(tertiaryProv);
		droptertiaryProv.selectByIndex(3);
		// primaryProv.sendKeys(patientdata.get("PrimaryProvider"));
		// secondaryProv.sendKeys(patientdata.get("SecondaryProvider"));
		// tertiaryProv.sendKeys(patientdata.get("TertiaryProvider"));
		button_Save.click();
		/// html/body/div[1]/div[3]/div[2]/div[1]/div/ul/li[4]/a/span
		Thread.sleep(15000);
	}

	public boolean verifyPatient(HashMap<String, String> patientdata) {

		if (patientAdded.isDisplayed()) {
			String patientname[] = patientAdded.getText().split("\n");
			if (patientname[0].equalsIgnoreCase((patientdata.get("FirstName") + " " + patientdata.get("LastName")))) {
				System.out.println("matches");
			}
			Assert.assertTrue(patientname[0].equals((patientdata.get("FirstName") + " " + patientdata.get("LastName"))),
					"Verify patient not added");

			patientBack.click();

		}
		return true;
	}

}
