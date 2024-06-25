package testCases;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.ResultsPage;
import screenshotmanager.ScreenShot;

public class TC002_SearchResults extends BaseClass{
	
	@Test
	public void searchResults() throws IOException {
		ResultsPage rp = new ResultsPage(driver);
		ScreenShot.fullScreenshot(driver);
		rp.setHolidayHomeasPreference();
		logger.info("Preference is set as Holiday Homes");
		ScreenShot.fullScreenshot(driver);
		rp.setSortToHighestRating();
		logger.info("Sorted by rating");
		ScreenShot.fullScreenshot(driver);
		rp.getAllHotelNamesWithPrice();
		logger.info("Printed hotel names with rent price");
		ScreenShot.fullScreenshot(driver);
		
		Assert.assertEquals(rp.getNunberOfHotels()>=3, true);
	}
	
}
