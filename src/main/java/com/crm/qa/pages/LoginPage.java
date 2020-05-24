package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.qa.base.Base;
import com.crm.qa.util.Util;

public class LoginPage extends Base {

	// Page Factory - OR:
	@FindBy(name = "email")
	WebElement userName;

	@FindBy(name = "password")
	WebElement userPassword;

	@FindBy(xpath = "//div[contains(@class, 'ui fluid large blue submit button')]")
	WebElement loginBtn;

	@FindBy(xpath = "//*[@id=\"ui\"]/div/div/div[1]/a")
	WebElement loginPageLink;

	@FindBy(xpath = "//*[@id=\"main-nav\"]/a[1]/span")
	WebElement homePageLink;

	// Initializing the Page Objects:
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	// Actions on the Page Objects:
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}

	public boolean validateLoginPageLink() {
		return loginPageLink.isDisplayed();
	}

	public HomePage login(String un, String pwd) {

		Util.explicitWaits_visibilityOfelement(driver, userName, Util.EXPLICIT_WAIT_TIMEOUT);
		userName.sendKeys(un);
		Util.explicitWaits_visibilityOfelement(driver, userPassword, Util.EXPLICIT_WAIT_TIMEOUT);
		userPassword.sendKeys(pwd);
		Util.explicitWaits_clickOnelement(driver, loginBtn, Util.EXPLICIT_WAIT_TIMEOUT);

		/*
		 * loginBtn.click();
		 */

		return new HomePage();
	}

	public boolean validateHomePageLink() {
		Util.explicitWaits_visibilityOfelement(driver, homePageLink, Util.EXPLICIT_WAIT_TIMEOUT);
		return homePageLink.isDisplayed();
	}

}
