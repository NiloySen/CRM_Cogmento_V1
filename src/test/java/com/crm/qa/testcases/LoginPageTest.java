package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.Base;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.Util;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class LoginPageTest extends Base {

	LoginPage loginPage;
	Util util;
	public ExtentReports extent;
	public ExtentTest extentTest;

	public LoginPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		// initialization();
		loginPage = new LoginPage();
		log.info("**Initialization invoke for LoginPageTest is successful**");
	}

	@Test(priority = 1)
	public void validateLoginPageTitleTest() {
		//extentTest = extent.startTest("validateLoginPageTitleTest");
		log.info("**Start LoginPage -> validateLoginPageTitleTest**");
		String loginPageTitle = loginPage.validateLoginPageTitle();
		Assert.assertEquals(loginPageTitle, "Cogmento CRM");
		log.info("**End LoginPage -> validateLoginPageTitleTest**");
	}

	@Test(priority = 2)
	public void validateLoginPageLinkTest() {
		log.info("**Start LoginPage -> validateLoginPageLinkTest**");
		Boolean loginPageLink = loginPage.validateLoginPageLink();
		Assert.assertTrue(loginPageLink);
		log.info("**End LoginPage -> validateLoginPageLinkTest**");
	}

	@Test(priority = 3)
	public void loginTest() {
		log.info("**Start LoginPage -> loginTest**");
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		log.info("**End LoginPage -> loginTest**");
	}

	@Test(priority = 4)
	public void validateHomePageLinkTest() {
		//extentTest = extent.startTest("validateLoginPageTitleTest");
		log.info("**Start LoginPage -> validateHomePageLinkTest**");
		Boolean homePageLink = loginPage.validateHomePageLink();
		Assert.assertTrue(homePageLink);
		log.info("**End LoginPage -> validateHomePageLinkTest**");
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
