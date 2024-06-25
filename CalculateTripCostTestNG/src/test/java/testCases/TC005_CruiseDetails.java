package testCases;

import java.io.IOException;
import org.testng.annotations.Test;
import pageObjects.SpecificCruiseDetails;
import screenshotmanager.ScreenShot;

public class TC005_CruiseDetails extends BaseClass{

	@Test
	public void setCruiseDetails() throws IOException {
		SpecificCruiseDetails scd = new SpecificCruiseDetails(driver);
		scd.getCompleteDet();
		logger.info("Got complete details of the cruise");
		ScreenShot.fullScreenshot(driver);
	}
}
