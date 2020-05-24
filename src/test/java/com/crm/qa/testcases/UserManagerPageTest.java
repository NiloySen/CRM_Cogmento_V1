package com.crm.qa.testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.Base;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.pages.UserManagerPage;

public class UserManagerPageTest extends Base {

	LoginPage loginPage;
	UserManagerPage userManagerPage;

	public UserManagerPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		// initialization();
		loginPage = new LoginPage();
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		log.info("**Initializantion invoke for LoginPageTest is successful**");
	}

	@Test(priority = 1)
	public void clickOnFreeAccountLinkTest() {
		log.info("**Start UserManagerPageTest -> clickOnFreeAccountLinkTest**");
		userManagerPage = new UserManagerPage();
		userManagerPage.clickOnFreeAccountLink();
		log.info("**End UserManagerPageTest -> clickOnFreeAccountLinkTest**");
	}

	/*
	 * @AfterClass public void tearDown() { driver.quit(); }
	 */
}
