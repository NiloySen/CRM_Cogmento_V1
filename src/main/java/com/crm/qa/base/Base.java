package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.crm.qa.util.Util;

public class Base {

	public static WebDriver driver;
	public static Properties prop;

	// What is log? : capturing info/activities at the time of program execution.
	// types of logs:
	// 1. info
	// 2. warn
	// 3. debug
	// 4. fatal

	// how to generate the logs? : use Apache log4j API (log4j jar)
	// How it works? : it reads log 4j configuration from log4j.properties file
	// where to create: create inside resources folder

	public static Logger log = Logger.getLogger("Base.class");

	public Base() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/com/crm" + "/qa/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// "C:\\Users\\Shreo-Niloy\\eclipse-workspace\\FreeCRMCogmento" -> user.dir
	// "\\src\\main\\resources\\executables\\chromedriver.exe"

	// To read browser name from TestNG.xml
	@Parameters("browser")
	@BeforeClass
	public static void initialization(String browserName) {
		log.info("**Initializing Browser**");

		// String browserName = prop.getProperty("browser"); --> To read browser name from config.properties

		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + prop.getProperty("chromepath"));
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("FF")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "\\src\\main\\resources\\executables\\geckodriver");
			driver = new FirefoxDriver();
		}

		log.info("**Browser launched**");

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Util.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Util.IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS);

		log.info("**Get the browser URL**");
		driver.get(prop.getProperty("url"));

	}
}
