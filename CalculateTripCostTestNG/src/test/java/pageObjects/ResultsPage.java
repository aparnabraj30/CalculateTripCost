package pageObjects;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.ExcelUtils;
import testCases.BaseClass;

public class ResultsPage extends BasePage{
	
	public ResultsPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//button[@data-testid='sorters-dropdown-trigger']")
	WebElement sort_dropdown;
	
	By sort_button = By.xpath("//button[@data-testid='sorters-dropdown-trigger']");
	
	By sortListElem = By.xpath("//div[@data-testid='sorters-dropdown']");
	
	@FindBy(xpath="//ul[@class='b122235413']//li")
	List<WebElement> sort_list_elem;
	
	@FindBy(xpath="//div[@class='b43bdecede']//button")
	WebElement close_popup;
	
	By offerDiv = By.className("b43bdecede");
	
	
	@FindBy(xpath="(//div[@data-filters-group='ht_id'])[1]//button")
	WebElement showall_button;
	
	@FindBy(xpath="(//div[contains(text(),'Holiday homes')  or contains(text(),'Vacation Homes')])[1]")
	WebElement vacation_home;
	
	By vh = By.xpath("(//div[contains(text(),'Holiday homes')  or contains(text(),'Vacation Homes')])[1]");
	By sb = By.xpath("(//div[@data-filters-group='ht_id'])[1]//button");
	
	By property_cards = By.xpath("//div[@data-testid='property-card']");
	By pn = By.xpath("//div[@data-testid='property-card']//div[@data-testid='title']");
	By rp = By.xpath("(//div[@data-testid='property-card']//span[@data-testid='price-and-discounted-price'])[1]");
	
	@FindBy(xpath="//div[@data-testid='property-card']//div[@data-testid='title']")
	List<WebElement> property_names;
	
	@FindBy(xpath="(//div[@data-testid='property-card']//span[@data-testid='price-and-discounted-price'])")
	List<WebElement> rental_price;
	
	@FindBy(xpath="(//div[contains(text(),'Upper floors accessible by elevator')])[1]")
	WebElement elevator;
	
	public void setSortToHighestRating() {
			waitForElement(property_cards);
			clickSortDropDown();
			waitForElement(sortListElem);
			for(WebElement a: sort_list_elem) {
				System.out.println(a.getText());
				if(a.getText().equals("Property rating (high to low)")) {
					a.click();
					break;
				}	
		}
		}
	
	public void getAllHotelNamesWithPrice() throws IOException {
		waitForElement(property_cards);
		for(int i =0;i<property_names.size() && i<rental_price.size();i++) {
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",property_names.get(i));
			String n = (property_names.get(i)).getText();
			String p = (rental_price.get(i)).getText();
			
			ExcelUtils.setCellData(BaseClass.xlfile2, "Sheet1", i+1, 0, n);
			ExcelUtils.setCellData(BaseClass.xlfile2, "Sheet1", i+1, 1, p);
			System.out.println(i+1 +" -> "+ "Property name : "+n + "Price : "+p);
			
		}
	}
	
	public void setHolidayHomeasPreference() {
		try {
			handleOfferPopup();
		}
		catch(Exception e) {
			System.out.println("There is no popup here too.. Haha !!");
		}
		finally {
			try {
				waitForElement(vh);
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",vacation_home);
				vacation_home.click();
			}
			catch(Exception e) {
				waitForElement(sb);
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",showall_button);
				showall_button.click();
				waitForElement(vh);
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",vacation_home);
				vacation_home.click();		
			}
		}
		
	}
	
	public boolean isResultPageHere() {
		waitForElement(property_cards);
		return property_names.get(0).isDisplayed();
	}
	
	public int getNunberOfHotels() {
		return property_names.size();
	}
	
	
	
	
	private void clickSortDropDown() {
		sort_dropdown.click();
	}
	
	private void waitForElement(By elem) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(7));
		wait.until(ExpectedConditions.presenceOfElementLocated(elem));
	}
	
	private void handleOfferPopup() {
		waitForElement(offerDiv);
		close_popup.click();
	}

}
