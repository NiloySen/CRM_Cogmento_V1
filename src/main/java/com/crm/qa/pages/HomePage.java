package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.qa.base.Base;

public class HomePage extends Base {

	// Page Factory - OR:
	/*
	 * @FindBy(xpath = "//*[@id=\"main-nav\"]/a[3]/span") WebElement contactLink;
	 */

	@FindBy(xpath = "//*[@id=\"main-nav\"]/a[3]/span")
	WebElement contactLink;

	@FindBy(xpath = "//*[@id=\"main-nav\"]/a[5]/span")
	WebElement dealsLink;

	@FindBy(xpath = "//*[@id=\"main-nav\"]")
	WebElement leftContainer;

	// Initializing the Page Objects:
	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	public void switchToLeftFrame() {
		driver.switchTo().frame(leftContainer);
	}

	public ContactPage clickOnContactsLink() {

		/*
		 * JavascriptExecutor js = (JavascriptExecutor)driver;
		 * js.executeScript("arguments[0].click();", contactLink);
		 */
		/*
		 * WebDriverWait wait = new WebDriverWait(driver, 15);
		 * wait.until(ExpectedConditions.elementToBeClickable(contactLink));
		 */
		contactLink.click();
		return new ContactPage();
	}

	public DealPage clickOnDealsLink() {

		/*
		 * JavascriptExecutor js = (JavascriptExecutor)driver;
		 * js.executeScript("arguments[0].click();", contactLink);
		 */
		dealsLink.click();
		return new DealPage();
	}

	public boolean validateDealsLink() {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.elementToBeClickable(dealsLink));
		return dealsLink.isDisplayed();
	}

}
