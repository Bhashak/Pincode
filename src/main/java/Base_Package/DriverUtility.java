package Base_Package;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;



public class DriverUtility {
	


	
	
	

	WebDriverWait wait;

	//private WebDriver driver;
	WebDriver driver;
	public  DriverUtility(WebDriver driver) {
		this.driver=driver;
		
	}
	 /* This method is used to move the curser on the element
	  * @Param element */

	 /* This method is used to maximize the window */
	public void maximizeWindow() {
		driver.manage().window().maximize();
	}
	/* This method is used to do window handle from one tab to another tab
	  *@Param title */
	public void windowHandle(String title) {
		Set<String>allwindowhandleID=driver.getWindowHandles();
		for(String wind:allwindowhandleID) {
		String currentTitle=driver.switchTo().window(wind).getTitle();
			if (currentTitle.contains(title)) {
				break;
			}
	}
		}
	/* This method will wait for particular element to be clickable
	  * @Param element
	  * @Param time */
	public void explicitWaitForElementClickable(WebElement element) {
//	    wait = new WebDriverWait(driver, Duration.ofSeconds(time));
//		wait.until(ExpectedConditions.elementToBeClickable(element));
		try {
			Thread.sleep(4000);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/* This method is used to scroll the page upto particular webelement
	  * @Param element */

	/*
	  * This method will wait till the particular webelement get visible
	  * @Param element
	  * @Param time */
	public void expicitWaitForVisibilityOfElement(WebElement element) {
		try {
			Thread.sleep(4000);
		} catch (Exception e) {
			// TODO: handle exception
		}
//	    wait = new WebDriverWait(driver, Duration.ofSeconds(time));
//		wait.until(ExpectedConditions.visibilityOf(element));
	}
	/* This method is used to click the webelement
	  *@Param element */
	public void ClickOnElement(WebElement element) {
		element.click();
	}
	/* This method is used to send data to element
	 * @Param element
	 * @Param data */
	public void sendText(WebElement element,String data) {
		element.sendKeys(data);	
	}
	/* This method is used to get the url
	 * @Param url */
	public void url(String url) {
		driver.get(url);
	}
	public void quitBrowser() {
		driver.quit();
	}
	public void expicitWaitForVisibilityOfTitle(String titles) {
//	    wait = new WebDriverWait(driver, Duration.ofSeconds(time));
//		wait.until(ExpectedConditions.titleIs(title));
		try {
			Thread.sleep(4000);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void waitUntillElementVisible(WebElement element, int time)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void downKey() 
	{
		try {
		Robot r=new Robot();
		r.keyPress(KeyEvent.VK_DOWN);
		r.keyPress(KeyEvent.VK_ENTER);
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void scrolltoElement(WebElement ele)
	{
		JavascriptExecutor js =(JavascriptExecutor)driver;
		 js.executeScript("arguments[0].scrollIntoView(true)", ele);
				
	}
	
	public void scrollToElement(WebElement webElement)
	{
		JavascriptExecutor js =(JavascriptExecutor)driver;
//		js.executeScript("window.scrollBy(0,500)");
		
		js.executeScript("arguments[0].scrollIntoView();,ele");
	}
	
	public void scrollby()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)", "");
	}
	
	public void validateAttribute(WebElement eleAttribute,String strAttributeName,String strtextValidate)
	{
		String strAttributevalue=eleAttribute.getAttribute(strAttributeName);
		Assert.assertTrue(strAttributevalue.contains(strtextValidate),strtextValidate+"text validate in"+strAttributeName+"Attribute");
		
		
	}
	
	/* This method will checks the intendent and realized value are equal
	 * @Param intendent
	 * @Param realized */
	public void verifyTwoValues(String intendent, String realized) {
		assertionEquals(realized, intendent);
	}
	/* This method will checks the element is visible 
	 * @Param element */
	public void validatingElementVisibility(WebElement element) {
		boolean value=isDisplay(element);
		assertionTrue(value);
	} 
	
	/*
	 * This method will checks the element not visible 
	 * @Param element */
	public void validatingElementInvisibility(WebElement element) {
		boolean value=!isDisplay(element);
		assertionTrue(value);
	}
	public  void Randomnumber()
	{
		Random random = new Random();

		// Random integer
		int randomInt = random.nextInt(); // Any random integer
		int randomIntWithinRange = random.nextInt(100);
	}
	
	/* This method is for assertionTrue
	 * @Param value */
	public void assertionTrue(boolean value) {
		Assert.assertTrue(value);
	}
	
	/* This method is for retreiving the text from the webelement
	 * @Param element */
	public String getText(WebElement element) {
		return element.getText();
	}
	
	/* This method is for element is displayed or not
	 * @Param element */
	public boolean isDisplay(WebElement element) {
	   return element.isDisplayed();
	}
	
	public boolean isDisplay(String strValue) {
		   return strValue.equals(strValue);
		}
	public void jsclickElement(WebElement element) {
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].click();", element);
	}

	public void mouseActionClick(WebElement ele)
	{
		Actions action = new Actions(driver);

		action.moveToElement(ele).click().perform();
	}
	
	/* This method is for checking assertionsEquals 
	 * @Param intendentValue
	 * @Param realizedValue */
	public void assertionEquals(String intendentValue, String realizedValue) {
	Assert.assertEquals(realizedValue, intendentValue);
	}
	
	public String getSystemdate() {
		String timeStamp = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss").format(new Date());
		return timeStamp;
	}
	
	public int randomNumber()
	{
  
		Random rand=new Random();
		int randomInRange = rand.nextInt(100);
		return randomInRange;
	}

	public String takeScreenshot(WebDriver driver, String filePath) throws IOException {
		// Convert WebDriver object to TakesScreenshot
		TakesScreenshot screenshotTaker = (TakesScreenshot) driver;

		// Capture screenshot as an image file
		File screenshot = screenshotTaker.getScreenshotAs(OutputType.FILE);

		// Copy the screenshot to the desired location
		Files.copy(screenshot.toPath(), Paths.get(filePath));
		return filePath;
	}
	
	}
	

