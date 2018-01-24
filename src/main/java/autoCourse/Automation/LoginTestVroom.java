package autoCourse.Automation;

import org.junit.Test;

import autoCourse.Automation.PageObject.vroomHomePage;

public class LoginTestVroom extends BasicTest{
	
	@Test
	public void Test1(){
		
		// Open web-page
		PageObject.vroomHomePage.openHomePage();
		
		// Search something
		vroomHomePage.search("BMW");
		
		vroomHomePage.getNumberOfCarsFromSearch();
		driverWrapper.printScreen();
	}
	
	@Test
	public void Test2(){
		
		PageObject.vroomMostExpensive.openHomePage();
		
		PageObject.vroomMostExpensive.searchForCars();
	}
	
}
