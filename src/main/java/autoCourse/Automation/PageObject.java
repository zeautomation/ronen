package autoCourse.Automation;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class PageObject {
	
	public static class vroomHomePage extends GenericPageObject implements ILoadable{
		
		public static void openHomePage(){
			driverWrapper.openUrl("https://www.vroom.com/");
		}
		
//		public int getNumberOfCarsFromSearch(){
//			
//			driverWrapper.getElementByType(value, type)
//		}

		public static void search (String test){
			WebElement search = driverWrapper.getElementByType("hero-search-input", "id");
			
			// check if item found before activation
			if(search != null){
				search.sendKeys(test);
				// another way: "search.sendKeys(Keys.ENTER);"
				
				driverWrapper.getElementByType("//button[@class='search-button']", "xpath").click();
			}
			else{
				System.out.println("Element not found!");
			}
		}
		
		public static void getNumberOfCarsFromSearch() {
			 driverWrapper.findListOfElements("//div[@class='car-image-container']");
			
		}
		
		public static void clickSearch(){
			
			WebElement submit = driverWrapper.getElementByType("//span[text() = 'Search']", "xpath");
			
			if(submit != null){
				submit.click();
			}
			else{
				System.out.println("Element not found!");
			}
			
		}
		
		public void isPageLoaded() {
			driverWrapper.getElementByType("//span[@class='search-btn-text']", "xpath");
		}
	}
	
	public static class vroomMostExpensive extends GenericPageObject{
		
		public static void openHomePage(){
			driverWrapper.openUrl("https://www.vroom.com/");
		}
		
		public static void searchForCars(){
			WebElement submit = driverWrapper.getElementByType("//a[text()='Search Cars Now']", "xpath");
			if(submit != null){
				submit.click();
			}
		}
	}
	
}
