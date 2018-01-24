package autoCourse.Automation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;


import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverWrapper {

	RemoteWebDriver remoteWebDriver;
	
	public LogEntries getConsoleLogs(){
		
		return remoteWebDriver.manage().logs().get(LogType.BROWSER);
	}
	
	public void printConsoleLogs(){
		
		// list of log entries
		List<LogEntry> consoleLogEntries = getConsoleLogs().getAll();
		
		// Array = length, List = size
		for (int i = 0; i<consoleLogEntries.size(); i++){
			
			LogEntry entry = consoleLogEntries.get(i);
			
			System.out.println(entry.getMessage() + ": " + entry.getTimestamp());
		}
	}
	
	// clearing the local storage from the browser
	public void clearLocalStorage(){
		runJavascript(String.format("window localStorage.clear();"));
	}
	
	public void deleteCookies(){
		
		remoteWebDriver.manage().deleteAllCookies();
	}
	
	public void addCookies(String cookieName, String value){
		
		Cookie cookie = new Cookie(cookieName, value);
		
		remoteWebDriver.manage().addCookie(cookie);
	}
	
	public void init() {

		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		
		LoggingPreferences loggingPreferences = new LoggingPreferences();
		
		loggingPreferences.enable(LogType.BROWSER, Level.ALL);
		
		capabilities.setCapability(CapabilityType.LOGGING_PREFS, loggingPreferences);
		
		remoteWebDriver = new RemoteWebDriver(capabilities);

	}

	public void quit() {
		remoteWebDriver.quit();
	}
	
	public void openUrl(String url){
		remoteWebDriver.get(url);
	}
	
	public void dragAndDrop(String xpathFrom, String xpathTo){
		
		Actions actions = new Actions(remoteWebDriver);
		
		//actions.click
	}
	
	public void runJavascript (String script){
		
		int height = remoteWebDriver.manage().window().getSize().getHeight();
		
		remoteWebDriver.executeScript(script);
	}
	
	// return elements by x-path
	public List<WebElement> findListOfElements(String xpath){
		
		// 20 = wait, 1000 = sample-rate
		WebDriverWait driverWait = new WebDriverWait(remoteWebDriver, 20, 1000);
		List<WebElement> elementsList = null;
		
		try {
			elementsList = driverWait.until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(By.xpath(xpath)));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return elementsList;
	}
	
	// method goal = find web-element
	public WebElement getElementByType(String value, String type) {
		
		// define variable
		WebElement element = null;
		
		try {
			// wait for element to load (explicit wait)
			WebDriverWait driverWait = new WebDriverWait(remoteWebDriver, 10, 1000);
			
			// Comparison of Strings ALWAYS USE ".equals" (instead of: ==)
			if (type.equals("xpath")) {
				element = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(value)));
			} else if (type.equals("id")) {
				element = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(value)));
			}
		}
		catch (Exception e) {
			
			printScreen();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return element;
	}
		
	public void printScreen() {

		WebDriver augmentedDriver = new Augmenter().augment(remoteWebDriver);
		File screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);

		copyFile(screenshot, System.getProperty("user.dir") + "\\files\\scr\\" + screenshot.getName());
	}

	public static void copyFile(File source, String destinationPath) {
		try {
			InputStream in = new FileInputStream(source);
			try {
				OutputStream out = new FileOutputStream(new File(destinationPath));
				try {
					// Transfer bytes from in to out
					byte[] buf = new byte[1024];
					int len;
					while ((len = in.read(buf)) > 0) {
						out.write(buf, 0, len);
					}
				} finally {
					out.close();
				}
			} finally {
				in.close();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
