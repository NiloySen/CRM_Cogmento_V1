package com.crm.qa.testcases;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.Base;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.Util;

public class HomePageTest extends Base {

	LoginPage loginPage;
	HomePage homePage;

	public HomePageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		//initialization();
		homePage = new HomePage();
		loginPage = new LoginPage();
		driver.manage().timeouts().implicitlyWait(Util.IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS);
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));

	}

	@Test(priority = 1)
	public void validateDealsLinkDisplayTest() {
		boolean flag = homePage.validateDealsLink();
		Assert.assertTrue(flag);
	}

	@AfterMethod
	public void tearDown() { 
		driver.quit(); 
		}

}
