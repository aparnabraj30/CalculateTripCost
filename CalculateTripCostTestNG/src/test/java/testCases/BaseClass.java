package testCases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.*;


public class BaseClass {
	
	public static Logger logger;
	public static Properties p;
	public static String URL1;
	public static String URL2;
	public static String xlfile1 = System.getProperty("user.dir")+"\\src\\test\\resources\\ExcelFiles\\Cruise Outputs.xlsx";
	public static String xlfile2 = System.getProperty("user.dir")+"\\src\\test\\resources\\ExcelFiles\\VacHomes Outputs.xlsx";

	public static WebDriver driver;
	@BeforeTest
	@Parameters({"browser"})
	public void setup(String b) throws IOException {
		
		FileReader file=new FileReader(".//src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		logger = LogManager.getLogger(this.getClass());
		switch(b) {
		case "chrome":
			ChromeOptions co = new ChromeOptions();
			co.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			driver = new ChromeDriver(co);
			break;
		case "edge":
			EdgeOptions eo = new EdgeOptions();
			eo.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			driver = new EdgeDriver(eo);
			break;
		}

		driver.manage().window().maximize();
		logger.info("Window Maximized");
		driver.manage().deleteAllCookies();
		logger.info("Deleted all cookies");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@AfterSuite
	public void tearDown() {
		driver.quit();
	}
	
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}
	
	public static void bookingHomes() {
		
		try {
			FileReader file=new FileReader(".//src//test//resources//config.properties");
			p=new Properties();
			p.load(file);
			
			URL1 = p.getProperty("URL1");
			driver.get(URL1);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void bookingCruises() {
			
			try {
				FileReader file=new FileReader(".//src//test//resources//config.properties");
				p=new Properties();
				p.load(file);
				
				
				URL2 = p.getProperty("URL2");
				driver.switchTo().newWindow(WindowType.TAB);
				driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
				driver.navigate().to(URL2);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			
	}
	
	
}
