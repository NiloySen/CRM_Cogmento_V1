package com.crm.qa.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.crm.qa.base.Base;
import com.crm.qa.util.Util;

public class ContactPage extends Base {

	// Page Factory - OR:
	@FindBy(xpath = "//*[@id=\"dashboard-toolbar\"]/div[2]/div/a/button")
	WebElement newContact;

	@FindBy(name = "first_name")
	WebElement contactFirstName;

	@FindBy(name = "last_name")
	WebElement contactLastName;

	@FindBy(xpath = "//*[@placeholder='Email address']")
	WebElement contactEmail;

	@FindBy(xpath = "//input[@name='day']")
	WebElement contactBirthDate;

	@FindBy(xpath = "//*[@name='month']")
	WebElement contactBirthMonth;

	@FindBy(xpath = "//input[@name='year']")
	WebElement contactBirthYear;

	@FindBy(xpath = "//button[@class='ui linkedin button']")
	WebElement saveContactDetails;

	@FindBy(xpath = "//*[@id=\"dashboard-toolbar\"]/div[1]/i")
	WebElement contactCreated;
	//*[@id=\"dashboard-toolbar\"]/div[1]
	

	// Initializing the Page Objects:
	public ContactPage() {
		PageFactory.initElements(driver, this);
	}

	public ContactPage createNewContact(String fn, String ln, String email, String date, String month, String year) {

		newContact.click();
		contactFirstName.sendKeys(fn);
		contactLastName.sendKeys(ln);
		Util.explicitWaits_clickOnelement(driver, contactEmail, Util.EXPLICIT_WAIT_TIMEOUT);
		// contactEmail.clear();
		contactEmail.sendKeys(email);

		contactBirthDate.sendKeys(date);

		/*
		 * contactBirthMonth.click(); Select select = new Select(contactBirthMonth);
		 * select.selectByVisibleText(month);
		 */

		// contactBirthMonth.sendKeys(month);
		contactBirthMonth.sendKeys(year);
		saveContactDetails.click();
		String fName = contactFirstName.getAttribute("value");
		String lName = contactLastName.getAttribute("value");
		String name = fName+" "+lName;
		System.out.println(name);
		Util.explicitWaits_visibilityOfelement(driver, contactCreated, Util.EXPLICIT_WAIT_TIMEOUT);
		String contactName = contactCreated.getAttribute("innerHTML");
		System.out.println(contactName);
		Assert.assertEquals(contactName, name);

		return new ContactPage();
	}

	public boolean verifyContactCreated() {

		return contactCreated.isDisplayed();
	}

}
