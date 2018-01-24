package autoCourse.Automation;

import org.junit.After;
import org.junit.Before;

public class BasicTest {

	WebDriverWrapper driverWrapper;

	@Before
	public void before() {

		driverWrapper = new WebDriverWrapper();

		driverWrapper.init();
		
		GenericPageObject.setWebDriver(driverWrapper);
	}

	@After
	public void after() {
		
		driverWrapper.printConsoleLogs();
		
		driverWrapper.quit();
	}
}
