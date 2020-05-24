package com.crm.qa.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.qa.base.Base;

public class UserManagerPage extends Base {

	// Page Factory - OR:
	@FindBy(xpath = "//*[@id=\"top-header-menu\"]/div[3]/span[2]/a")
	WebElement freeAccountLink;

	@FindBy(xpath = "//*[@id=\"ui\"]/div/div[2]/div[2]/div/div/div[1]/div/a[4]")
	WebElement userManager;

	@FindBy(xpath = "//*[@id=\"ui\"]/div/div[2]/div[2]/div/div/div[2]/div/div/div[2]/a/div/i")
	WebElement addNewUser;

	@FindBy(xpath = "//*[@class=\"ui linkedin small button\"]")
	WebElement saveNewUser;

	@FindBy(xpath = "//*[@class='background']")
	WebElement redNotificationBottom;

	// Initializing the Page Objects:
	public UserManagerPage() {
		PageFactory.initElements(driver, this);
	}

	public UserManagerPage clickOnFreeAccountLink() {

		/*
		 * WebDriverWait wait = new WebDriverWait(driver, 15);
		 * wait.until(ExpectedConditions.elementToBeClickable(freeAccountLink));
		 * freeAccountLink.click();
		 * wait.until(ExpectedConditions.elementToBeClickable(userManager));
		 * userManager.click();
		 * wait.until(ExpectedConditions.elementToBeClickable(addNewUser));
		 * addNewUser.click(); saveNewUser.click();
		 */

		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.elementToBeClickable(redNotificationBottom));

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//new Actions(driver).moveToElement(new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@id='Automation Tools']")))).build().perform();
		
		Point p= redNotificationBottom.getLocation();
		Actions actions = new Actions(driver);
		//actions.moveToElement(redNotificationBottom).moveByOffset(p.x,p.y).click().perform();
		actions.moveToElement(new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(redNotificationBottom))).build().perform();
		
		redNotificationBottom.click();
		Alert alert = driver.switchTo().alert();
		alert.accept();

		return new UserManagerPage();

	}

}
