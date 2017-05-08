package com.toolsQA.testNG.pack;

import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ReporterLogs {
  
	WebDriver driver = null;
	String URL = "http://www.store.demoqa.com";
	String PROXY = "hjproxy.persistent.co.in:8080";
 
@BeforeTest
  public void launch() {
	 
	
	Proxy proxy = new Proxy();
	proxy.setHttpProxy(PROXY).setFtpProxy(PROXY).setSslProxy(PROXY)
	.setSocksProxy(PROXY);
	DesiredCapabilities cap = new DesiredCapabilities();
	cap.setCapability(CapabilityType.PROXY, proxy);
	driver = new FirefoxDriver(cap);
	driver.get(URL);
	
	Reporter.log("Application Lauched successfully");
	
  }


@Test
public void actualTest()
{
	driver.findElement(By.xpath(".//*[@id='account']/a")).click();
	 
     driver.findElement(By.id("log")).sendKeys("testuser_1");

      driver.findElement(By.id("pwd")).sendKeys("Test@123");
      driver.findElement(By.id("login")).click();
   // System.out.println(driver.findElement(By.xpath("/*[@id='ajax_loginform']/p[1]/child::strong[contains(text(),'ERROR')]")).getText());
     String actual = driver.findElement(By.xpath("//*[@id='ajax_loginform']/p[1]")).getText();
    String actual2 ="ERROR";
     System.out.println(actual);
    Assert.assertNotEquals(actual, actual2);
      Reporter.log("Login Sucessfull");

}

 @AfterTest
 public void tearDown()
 { 
	 driver.close();
	 driver.quit();
	 
 }

}