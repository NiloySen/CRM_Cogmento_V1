package com.qa.ExtentReportListener;

//Listener class used to generate Extent reports

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.crm.qa.util.Util;

public class Reporting_Aventstack extends TestListenerAdapter {
	WebDriver driver;
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;

	public void onStart(ITestContext testContext) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// time stamp
		String repName = "Test-Report-" + timeStamp + ".html";

		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/" + repName);// specify
																											// location
																											// of the
																											// report
		htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");

		extent = new ExtentReports();

		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environemnt", "QA");
		extent.setSystemInfo("user", "pavan");

		// Tile of the report
		htmlReporter.config().setDocumentTitle("FREE CRM Cogmento");
		// Name of the report
		htmlReporter.config().setReportName("FREE CRM Test Automation Report");
		// Location of the chart
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		// Theme of the chart
		htmlReporter.config().setTheme(Theme.DARK);

	}

	public void onTestSuccess(ITestResult successResult) {
		logger = extent.createTest(successResult.getName()); // create new entry in the report
		logger.log(Status.PASS, MarkupHelper.createLabel(successResult.getName(), ExtentColor.GREEN)); // send the
																										// passed
		// information to the report
		// with GREEN color
		// highlighted
	}

	public void onTestFailure(ITestResult failureResult) {
		// logger = extent.createTest(failureResult.getName()); // create new entry in
		// the report
		logger = extent.createTest(failureResult.getName(), failureResult.getThrowable().toString());
		logger.log(Status.FAIL, MarkupHelper.createLabel(failureResult.getName(), ExtentColor.RED)); // send the passed
																										// information
		// to the report with GREEN
		// color highlighted

		// String screenshotPath = System.getProperty("user.dir") +
		// "\\Screenshots_Failure\\" + failureResult.getName() + ".png";

		String screenshotPath = Util.getScreenshot(driver, failureResult.getName());

		File f = new File(screenshotPath);

		if (f.exists()) {
			try {
				logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotPath));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public void onTestSkipped(ITestResult skipResult) {
		logger = extent.createTest(skipResult.getName()); // create new entry in the report
		logger.log(Status.SKIP, MarkupHelper.createLabel(skipResult.getName(), ExtentColor.ORANGE));
	}

	public void onFinish(ITestContext testContext) {
		extent.flush();
	}
}
