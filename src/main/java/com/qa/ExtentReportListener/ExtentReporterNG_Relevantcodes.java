
package com.qa.ExtentReportListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReporterNG_Relevantcodes implements IReporter {

	private ExtentReports extent;
	public ExtentHtmlReporter htmlReporter;
	public ExtentTest extentTest;

	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {

		// time stamp format
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		// report name format
		String repName = "Test-Report-" + timeStamp + ".html";

		// specify location of the report
		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/" + repName);

		extent.addSystemInfo("Host Name", "Niloy-Shreo");
		extent.addSystemInfo("User Name", "Test User");
		extent.addSystemInfo("Environment", "Test");

		/*
		 * // Tile of the report
		 * htmlReporter.config().setDocumentTitle("FREE CRM Cogmento"); // Name of the
		 * report
		 * htmlReporter.config().setReportName("FREE CRM Test Automation Report"); //
		 * Location of the chartO
		 * htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP); // Theme
		 * of the chart htmlReporter.config().setTheme(Theme.DARK);
		 */

		for (ISuite suite : suites) {
			Map<String, ISuiteResult> result = suite.getResults();

			for (ISuiteResult r : result.values()) {
				ITestContext context = r.getTestContext();

				buildTestNodes(context.getPassedTests(), LogStatus.PASS);
				buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
				buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
			}
		}

		extent.flush();
		extent.close();
	}

	private void buildTestNodes(IResultMap tests, LogStatus status) {
		ExtentTest test;

		if (tests.size() > 0) {
			for (ITestResult result : tests.getAllResults()) {
				test = extent.startTest(result.getMethod().getMethodName());

				test.setStartedTime(getTime(result.getStartMillis()));
				test.setEndedTime(getTime(result.getEndMillis()));

				for (String group : result.getMethod().getGroups())
					test.assignCategory(group);

				if (result.getThrowable() != null) {
					test.log(status, result.getThrowable());
				} else {
					test.log(status, "Test " + status.toString().toLowerCase() + "ed");
				}

				extent.endTest(test);
			}
		}
	}

	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}

}