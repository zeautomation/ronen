package autoCourse.Automation;

import org.junit.Test;
import org.openqa.selenium.WebElement;

public class LoginTests extends BasicTest {
	
	private static final String SUT_URL = "http://autocourse.tmtjk6qchm.us-east-1.elasticbeanstalk.com/loginPage.jsp";

	@Test
	public void login() throws Exception{
		
		driverWrapper.openUrl(SUT_URL);
		
		System.out.println("Finding Username field");
		
		WebElement element = driverWrapper.getElementByType("username", "id");
		
		WebElement element2 = driverWrapper.getElementByType("password", "id");
		
		WebElement element3 = driverWrapper.getElementByType("//button[text()='submit']", "xpath");
		
		element.sendKeys("user1");
		
		element2.sendKeys("1234");

		element3.click();
		
		System.out.println();
	}
	
}
