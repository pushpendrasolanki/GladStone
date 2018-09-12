package com.test.mainlinetestvismc.utilities;

import org.apache.log4j.Logger;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class GenerateReport implements ITestListener {
	Logger log = Logger.getLogger("devpinoyLogger");


	public void onTestStart(ITestResult result) {
		System.out.println("Begin Test");
		log.info("Begin Test");

	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("Test Success");
		log.info("Test Success");

	}

	
	public void onTestFailure(ITestResult result) {
		System.out.println("Failure Test");
		log.info("Failure Test");

	}

	
	public void onTestSkipped(ITestResult result) {
		System.out.println("Skipped Test");
		log.info("Skipped Test");

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("Failed But WithinSuccess");
		log.info("Failed but within success");

	}

	
	public void onStart(ITestContext context) {
		System.out.println("Starting the Test");
		log.info("starting the Test");

	}

	
	public void onFinish(ITestContext context) {
		System.out.println("Begin Test");
		log.info("Finish Test");

	}}
