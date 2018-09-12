package com.test.mainlinetestvismc.pages;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	private WebDriver driver;
	private String title = "MAINLINETEST";

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(id = "userName")
	public WebElement userName;

	@FindBy(id = "password")
	public WebElement password;
	
	@FindBy(id= "errorMsg")
	public WebElement errorMessage;

	public boolean userNavigateToOTPPage() {
		if (driver.getTitle().contains(title)) {
			return true;
		}
		return false;
	}

	public AddPatientPage clickonLoginButton() {
		try {
			WebElement login = driver.findElement(By.id("Login_submit"));
			if (login.isDisplayed() || login.isEnabled()) {
				Thread.sleep(5000);

				login.click();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Login button is not clicked");
		}
		return PageFactory.initElements(driver, AddPatientPage.class);
	}

	public boolean enterLoginDetail(HashMap<String, String> map) {
		try {
			if (userName.isEnabled() && password.isEnabled()) {
				userName.sendKeys(map.get("Login"));
				password.sendKeys(map.get("Password"));
				Thread.sleep(5000);
				clickonLoginButton();
				if(errorMessage.isDisplayed()) {
					return false;
				}
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Issue Detected on Passing Value on Login Page");
		}
		return false;
	}
	

}
