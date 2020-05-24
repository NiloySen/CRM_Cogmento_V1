package com.crm.qa.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.Base;
import com.crm.qa.pages.ContactPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.Util;

public class ContactPageTest extends Base {

	ContactPage contactPage;
	LoginPage loginPage;
	HomePage homePage;

	String contactsDataSheet = "Contacts";

	public ContactPageTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws InterruptedException {
		// initialization();
		loginPage = new LoginPage();
		contactPage = new ContactPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		contactPage = homePage.clickOnContactsLink();

		log.info("**Initializantion invoke for ContactsPageTest is successful**");

		// driver.manage().timeouts().implicitlyWait(Util.IMPLICIT_WAIT,
		// TimeUnit.SECONDS);

		// homePage.switchToLeftFrame();
	}

	@DataProvider
	public Object[][] getContactsTestData() {
		Object data[][] = Util.getTestData(contactsDataSheet);
		return data;
	}

	@Test(priority = 1, dataProvider = "getContactsTestData")
	public void validateCreateNewContactTest(String fn, String ln, String email, String date, String month, String year)
			throws IOException {

		log.info("**Start ContactPage -> validateCreateNewContactTest**");
		homePage.clickOnContactsLink();
		contactPage.createNewContact(fn, ln, email, date, month, year);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Boolean flag = contactPage.verifyContactCreated();
		Assert.assertTrue(flag);
		Util.takeScreenshot("New Contact Created");
		log.info("**End ContactPage -> validateCreateNewContactTest**");

	}

	@AfterGroups
	public void tearDown() {
		// initialization();
		driver.quit();
	}

}
