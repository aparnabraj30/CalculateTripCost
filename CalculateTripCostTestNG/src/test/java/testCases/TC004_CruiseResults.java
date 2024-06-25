package testCases;

import java.io.IOException;
import org.testng.annotations.Test;
import pageObjects.CruiseResultsPage;
import screenshotmanager.ScreenShot;

public class TC004_CruiseResults extends BaseClass{

	@Test
	public void getCruiseResults() throws IOException {
		CruiseResultsPage cr = new CruiseResultsPage(driver);
		ScreenShot.fullScreenshot(driver);
		cr.getCruiseDetails();
		logger.info("Got Different types of Cruise available");
		ScreenShot.fullScreenshot(driver);
		cr.getFirstCruiseFullDetails();
		ScreenShot.fullScreenshot(driver);
	}
	
}
