package com.toolsQA.testNG.pack;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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


@Test(enabled=false)
public void actualTest()
{
		driver.findElement(By.xpath(".//*[@id='account']/a")).click();
		 
	     driver.findElement(By.id("log")).sendKeys("testuser_1");
	
	      driver.findElement(By.id("pwd")).sendKeys("Test@123");
	      driver.findElement(By.id("login")).click();
	   // System.out.println(driver.findElement(By.xpath("/*[@id='ajax_loginform']/p[1]/child::strong[contains(text(),'ERROR')]")).getText());
	      By message = By.xpath("/*[@id='ajax_loginform']/p[1]/following::");
	      //String actual = driver.findElement(By.xpath("/*[@id='ajax_loginform']/p[1]")).getText();
	     WebDriverWait wait = new WebDriverWait(driver,20);
	  String Actualmessage = wait.until(ExpectedConditions.visibilityOfElementLocated(message)).getText();
	     System.out.println("The actual message " + Actualmessage);
	     String actual2 ="ERROR";
	    // System.out.println(actual);
	   // Assert.assertNotEquals(actual, actual2);
	      Reporter.log("Login Sucessfull");
	
	}

@Test(enabled=false)
public void actualTest2()
{
	List<WebElement> element = driver.findElements(By.xpath("//*[@id='menu-item-15']/following::a"));
	System.out.println(element.size());
	for(int i=0;i<element.size();i++)
	{
	switch(element.get(i).getText())
	{
		
	    case "Home":
	    System.out.println("Home");
		break;
		
	    case "Product Category":
	    	System.out.println("Product Category");
	    break;
	    
	    case "All Product":
	    	System.out.println("All Product");
	    	break;
		default :
		break;
		}
	}
}

@Test(enabled=false)
public void actualTest3()
{
	List<WebElement> element =  driver.findElements(By.xpath("//*[@id='menu-item-34']//ancestor::li"));
  System.out.println(element.size());
}

@Test
public void actualTest4() throws InterruptedException
{
	 Actions action = new Actions(driver);
	 By productc = By.xpath("//*[@id='menu-item-33']/a");
	WebElement element = driver.findElement(productc);
	//WebElement element2 = driver.findElement("//*[@id='menu-item-34']/a");
	action.moveToElement(element).moveToElement(driver.findElement(By.xpath("//*[@id='menu-item-34']/a"))).click().perform();
	Thread.sleep(5);
}
 @AfterTest
 public void tearDown()
 { 
	 driver.close();
	 driver.quit();
	 
 }

}