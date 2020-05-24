package com.crm.qa.test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrokenLinks {

	private static WebDriver driver = null;

	public static void main(String[] args) throws MalformedURLException, IOException {
		// TODO Auto-generated method stub

		String homePage = "http://www.zlti.com";
		String url = "";
		HttpURLConnection huc = null;
		int respCode = 200;

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Shreo-Niloy\\eclipse-workspace\\FreeCRMCogmento\\Executables\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get(homePage);

		//Get all the WebElement links in page
		List<WebElement> linkTags = driver.findElements(By.tagName("a"));

		//Get the size of the WebElement in page
		System.out.println("Size of the link tag is :" + linkTags.size());
		
		//Iterate through the list of all WebElements in the page and the active ones in array list
		List<WebElement> activeLinkTags = new ArrayList<WebElement>();
		for (int i=0; i<linkTags.size();i++) {
			if(linkTags.get(i).getAttribute("href")!=null) {
				activeLinkTags.add(linkTags.get(i));
			}
		}
		System.out.println("Size of the active link tag is :"+activeLinkTags.size());
		
		for (int j=0; j<linkTags.size();j++) {
			HttpURLConnection con= (HttpURLConnection) new URL(activeLinkTags.get(j).getAttribute("href")).openConnection();
			con.connect();
			String response = con.getResponseMessage();
			con.disconnect();
			System.out.println(activeLinkTags.get(j).getAttribute("href")+" "+response);
		}

		driver.quit();

	}
}