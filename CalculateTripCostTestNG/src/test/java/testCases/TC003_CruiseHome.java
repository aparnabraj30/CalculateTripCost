package testCases;

import java.io.IOException;
import org.testng.annotations.Test;
import pageObjects.CruiseHomePage;
import pageObjects.CruiseResultsPage;
import pageObjects.SpecificCruiseDetails;
import screenshotmanager.ScreenShot;

public class TC003_CruiseHome extends BaseClass{
	
	
	
	@Test(priority =2 )
	public void testForCruise() throws IOException {
		BaseClass.bookingCruises();
		CruiseHomePage cp = new CruiseHomePage(driver);
		ScreenShot.fullScreenshot(driver);
		cp.setCruiseDetails();
		logger.info("Cruise details is set");
		ScreenShot.fullScreenshot(driver);
//		CruiseResultsPage cr = new CruiseResultsPage(driver);
//		ScreenShot.fullScreenshot(driver);
//		cr.getCruiseDetails();
//		logger.info("Got Different types of Cruise available");
//		ScreenShot.fullScreenshot(driver);
//		cr.getFirstCruiseFullDetails();
//		ScreenShot.fullScreenshot(driver);
//		
//		SpecificCruiseDetails scd = new SpecificCruiseDetails(driver);
//		scd.getCompleteDet();
//		logger.info("Got complete details of the cruise");
//		ScreenShot.fullScreenshot(driver);
		
	}

}
