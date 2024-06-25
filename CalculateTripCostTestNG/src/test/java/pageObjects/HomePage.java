package pageObjects;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage{
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@name='ss']")
	WebElement search_box;
	
	@FindBy(xpath="//div[@class='b43bdecede']//button")
	WebElement close_popup;
	
	By offerDiv = By.className("b43bdecede");
	
	
	@FindBy(xpath="//button[@data-testid='date-display-field-start']")
	WebElement date_picker_button;
	
	By calendar_box = By.xpath("//div[@data-testid = 'searchbox-datepicker']");
	
	@FindBy(xpath="//div[@data-testid = 'searchbox-datepicker-calendar']//table//td/span")
	List<WebElement> dates;
	
	@FindBy(xpath="//button[@data-testid = 'occupancy-config']")
	WebElement occupancy;
	
	By occupancy_popup = By.xpath("//div[@data-testid='occupancy-popup']");
	
	@FindBy(xpath="//div[@data-testid='occupancy-popup']/descendant::button[2]")
	WebElement add_adult_by_one;
	
	@FindBy(xpath="(//span[@class='c366165ccc'])[1]")
	WebElement adult_count;
	
	@FindBy(xpath="//button[normalize-space() = 'Done']")
	WebElement done_button;
	
	
	@FindBy(xpath="//button[normalize-space() = 'Search']")
	WebElement search_button;
			
	
	

	public void giveSearchInput() {
		try {
		handleOfferPopup();
		}
		catch(Exception e) {
			System.out.println("There is no popup..");
		}
		finally {		
			search_box.click();
			search_box.clear();
			search_box.sendKeys("Nairobi");
		}
		
	}
	
	public void giveDateInput() {
		date_picker_button.click();
		waitForElement(calendar_box);
		String tomorrow_date = getTomorrowDate();
		String after_fivedays = getDateAfterFiveDays();
		for(WebElement a:dates) {
			if(a.getAttribute("data-date").equals(tomorrow_date)) {
				a.click();
			}
			
			if((a.getAttribute("data-date").equals(after_fivedays))) {
				a.click();
				break;
			}
		}
		
	}
	

	public void giveOccupancyDetails() {
		occupancy.click();
		waitForElement(occupancy_popup);
		while(!adult_count.getText().equals("4")) {
			add_adult_by_one.click();
		}
		done_button.click();
		
	}
	
	public void clickSearchButton() {
		search_button.click();
	}
	
	private void waitForElement(By elem) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(7));
		wait.until(ExpectedConditions.presenceOfElementLocated(elem));
	}
	
	private String getTomorrowDate() {
		LocalDate tomorrow = LocalDate.now().plusDays(1);
		DateTimeFormatter pattern =	DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return tomorrow.format(pattern);
	}
	
	private String getDateAfterFiveDays() {
		LocalDate d = LocalDate.now().plusDays(6);
		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return d.format(pattern);
	}
	
	private void handleOfferPopup() {
		waitForElement(offerDiv);
		close_popup.click();
	}
}
