package com.crm.qa.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import com.crm.qa.util.Util;

public class Test {
	public static void main(String[] args) {

		WebDriver driver;

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Shreo-Niloy\\eclipse-workspace\\FreeCRMCogmento\\Executables\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Util.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Util.IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS);
		driver.get("https://learn.letskodeit.com/p/practice");

		// Radio button Select
		WebElement radiobtn = driver.findElement(By.id("bmwradio"));
		radiobtn.click();
		Boolean radio = radiobtn.isSelected();
		System.out.println("Radio button selected Value is " + radio);
		Assert.assertTrue(radio);

		// Alert Handling
		WebElement alertBtn = driver.findElement(By.id("alertbtn"));
		alertBtn.click();
		String alertText = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		System.out.println("Alert text is " + alertText);
		Assert.assertEquals(alertText, "Hello , share this practice page and share your knowledge");

		// Alert Text validation
		WebElement nameTextBox = driver.findElement(By.id("name"));
		WebElement confirmBtn = driver.findElement(By.id("confirmbtn"));
		nameTextBox.sendKeys("Shreoshi");
		String textBoxValue = nameTextBox.getAttribute("value");
		// String textBoxValue = nameTextBox.getText();
		String expectedtextBoxValue = "Hello " + textBoxValue + ", Are you sure you want to confirm?";
		confirmBtn.click();
		String confirmBtnAlert = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		System.out.println(expectedtextBoxValue);
		Assert.assertEquals(confirmBtnAlert, expectedtextBoxValue);

	}

}
