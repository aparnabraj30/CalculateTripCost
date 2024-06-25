package testCases;

import java.io.IOException;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import screenshotmanager.ScreenShot;

public class TC001_HolidayHome extends BaseClass{
	
	
	
	@Test(priority = 1)
	public void testForHolidayHomes() throws IOException {
		logger.info("Launching the app");
		BaseClass.bookingHomes();
		HomePage hp = new HomePage(driver);
		hp.giveSearchInput();
		logger.info("Search input is given");
		ScreenShot.fullScreenshot(driver);
		hp.giveDateInput();
		logger.info("Date input is given");
		ScreenShot.fullScreenshot(driver);
		hp.giveOccupancyDetails();
		logger.info("Occupancy input is given");
		ScreenShot.fullScreenshot(driver);
		hp.clickSearchButton();
		logger.info("Search button is clicked");
	}
	

	
	
	
}
