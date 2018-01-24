package autoCourse.Automation;

import org.junit.Test;
import org.openqa.selenium.WebElement;

public class MySupermarket_project extends BasicTest {
	
	private static final String SUT_URL = "http://www.mysupermarket.co.il/";

	@Test
	public void login() throws Exception{
		
		driverWrapper.openUrl(SUT_URL);
		
		
//		System.out.println("Finding Username field");
//		
//		WebElement element = driverWrapper.getElementByType("username", "id");
//		
//		
		driverWrapper.getElementByType("//a[@href='http://www.mysupermarket.co.il/עכשיו_בזול/מדף'][1]", "xpath").click();
		
		driverWrapper.getElementByType("//a[@class='ui-dialog-titlebar-close ui-corner-all']", "xpath").click();
		
		driverWrapper.getElementByType("txtfind", "id").sendKeys("חלב");
		
		driverWrapper.getElementByType("btnFind", "id").click();
		
		System.out.println();
//		
		
		
//		element.sendKeys("user1");
//		
//		element2.sendKeys("1234");
//

//		
//		System.out.println();
	}
	

	
}
