package com.LambdaTest.com.LambdaTestAssignment;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Hello world!
 *
 */
public class SeleniumPlayGround {
	public String username = "Kalla.Radha";
	public String accesskey = "1kYEahVb5mqpCDi8PO2jaBIaLwWLRjqCZ8NZ0H48EXjl01IzlG";
	public RemoteWebDriver driver = null;
	public String gridURL = "@hub.lambdatest.com/wd/hub";
	boolean status = false;

	@BeforeMethod
	@org.testng.annotations.Parameters(value = { "browser", "version", "platform" })
	public void setUp(String browser, String version, String platform) throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("browserName", browser);
		capabilities.setCapability("version", version);
		capabilities.setCapability("platform", platform); // If this cap isn't specified, it will just get the any
															// available one
		capabilities.setCapability("build", browser+platform);
		capabilities.setCapability("name", browser+platform);
		capabilities.setCapability("network", true); // To enable network logs
		capabilities.setCapability("visual", true); // To enable step by step screenshot
		capabilities.setCapability("video", true); // To enable video recording
		capabilities.setCapability("console", true); // To capture console logs
		try {
			driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + gridURL), capabilities);
			driver.get("https://www.lambdatest.com/selenium-playground");
		} catch (MalformedURLException e) {
			System.out.println("Invalid grid URL");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test(priority = 0)
	public void simpleFormDemo() throws Exception {

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		// Change it to production page
		// driver.get("https://lambda.github.io/sample-todo-app/");
		// driver.switchTo().alert().accept();
		driver.findElement(By.linkText("Simple Form Demo")).click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		String currentUrl = driver.getCurrentUrl();
		Assert.assertTrue(currentUrl.contains("simple-form-demo"));
		String textMessage = "Welcome to lambda Test";
		driver.findElement(By.xpath("//input[@id='user-message']")).sendKeys(textMessage);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.id("showInput")).click();
		String yourMessage = driver.findElement(By.xpath("//div[@id='user-message']//following::p[@id='message']"))
				.getText();
		Assert.assertEquals(textMessage, yourMessage,
				"Incoorect test message is being displayed in the right hand panel");
	}

	@Test(priority = 1)
	public void DragandDrop() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.linkText("Drag & Drop Sliders")).click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		WebElement dragElementFrom = driver
				.findElement(By.xpath("(//h4[text()=' Default value 15']//following::input[@type='range'])[1]"));

		// To Move jQuery slider by 100 pixel offset using dragAndDropBy method of
		// Actions class.
		new Actions(driver).dragAndDropBy(dragElementFrom, 119, 0).build().perform();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		// After 5 seconds, This will Move jQuery slider by 100 pixel offset using the
		// combination of clickAndHold, moveByOffset and release methods of Actions
		// class.
		new Actions(driver).clickAndHold(dragElementFrom).moveByOffset(119, 0).release().perform();

		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

		String actualSliderValue = driver.findElement(By.xpath("//output[@id='rangeSuccess']")).getText();
		System.out.println(actualSliderValue);
		Assert.assertEquals(actualSliderValue.trim(), "95", "The slider range value is not 95");
//		if(95==Integer.parseInt(actualSliderValue))
//		{
//			Assert.assertTrue(true);
//		}
	}

	@DataProvider(name = "data-provider")
	public Object[][] formDetails() {
		return new Object[][] { { "Radha", "test123@gmail.com", "testweclome@121", "TestCompany", "DemoWebsite.com",
				"Bangalore", "flat no 3,abc road", "global village", "karnataka", "341200" } };
	}

	@Test(priority = 2, dataProvider = "data-provider")
	public void InputFormSubmit(String name, String email, String password, String companyName, String website,
			String City, String address1, String address2, String state, String pincode) {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.linkText("Input Form Submit")).click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//button[text()='Submit']")).click();
//		  WebDriverWait wait = new WebDriverWait(driver,20);
//		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.id<locator>))
		driver.findElement(By.name("name")).sendKeys(name);
		driver.findElement(By.name("email")).sendKeys(email);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.id("company")).sendKeys(companyName);
		driver.findElement(By.id("websitename")).sendKeys(website);
		Select drpCountry = new Select(driver.findElement(By.name("country")));
		drpCountry.selectByVisibleText("United States");
		driver.findElement(By.id("inputCity")).sendKeys(City);
		driver.findElement(By.id("inputAddress1")).sendKeys(address1);
		driver.findElement(By.id("inputAddress2")).sendKeys(address2);
		driver.findElement(By.id("inputState")).sendKeys(state);
		driver.findElement(By.id("inputZip")).sendKeys(pincode);
//		  WebElement submitButton = driver.findElement(By.xpath("//button[text()='Submit']"));
//		  ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
		driver.findElement(By.xpath("//button[text()='Submit']")).click();
		boolean statusMsg = driver.findElement(By.xpath("(//form[@id='seleniumform']//following::p)[1]")).isDisplayed();
		Assert.assertEquals(statusMsg, true);

	}

	@AfterMethod
	public void tearDown() throws Exception {
		if (driver != null) {
			((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
			driver.quit();
		}
	}
}
