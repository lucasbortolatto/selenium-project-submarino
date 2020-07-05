package testsubmarinocheckout;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage {

	private WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickOnElementById(String idElement) {
		driver.findElement(By.id(idElement)).click();
	}
	
	public void clickOnElementByXpath(String xPathElement) {
		driver.findElement(By.xpath(xPathElement)).click();
	}

	public void writeTextOnField(String idElement, String text) {
		driver.findElement(By.id(idElement)).sendKeys(text);
	}

	public void searchByProduct(String product) {
		clickOnElementById("h_search-input");
		writeTextOnField("h_search-input", product);
		clickOnElementById("h_search-btn");
	}
	
	public String getHeaderSearchResult(){
		return driver.findElement(By.xpath("//div[@data-tracker=\"searchquery-main\"]//h1")).getText();
	}
	
	public String getImageAltByXpath(String givenXpath) {
		return driver.findElement(By.xpath(givenXpath)).getAttribute("alt");
	}
	
	public WebElement getElementByXpath(String givenXpath) {
		return driver.findElement(By.xpath(givenXpath));
	}
	
	public String getElementTextByXpath(String givenXpath) {
		return driver.findElement(By.xpath(givenXpath)).getText();
	}
	
	public String getProductOnCartTitle() {
		return driver.findElement(By.className("basket-productTitle")).getText();
	}



}
