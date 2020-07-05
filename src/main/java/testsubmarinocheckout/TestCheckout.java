package testsubmarinocheckout;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCheckout {
	
	private WebDriver driver;
	private CheckoutPage checkoutPage;
	
	@Before
	public void  init() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://www.submarino.com");
		checkoutPage = new CheckoutPage(driver);
	}
	
	@After
	public void teardown() {
		if (driver != null) {
			driver.close();
		}
	}
	

	@Test
	public void chooseOneProductAndSendToCart() throws InterruptedException {

		String desiredIphoneText = "Iphone 11 64 GB Branco";
		String productName = "iPhone 11 64GB Branco iOS 4G Câmera 12MP - Apple";
		String desiredIphoneImgPath = "//img[@alt=\"iPhone 11 64GB Branco iOS 4G Câmera 12MP - Apple\"]";

		Thread.sleep(1000);
		checkoutPage.searchByProduct(desiredIphoneText);
		Thread.sleep(1000);
		String headerSearchResult = checkoutPage.getHeaderSearchResult();
		assert(headerSearchResult).equals(desiredIphoneText);
		Thread.sleep(1000);
		String iphoneImgAlt = checkoutPage.getImageAltByXpath(desiredIphoneImgPath);
		assert(iphoneImgAlt).equals(productName);
		checkoutPage.clickOnElementByXpath(desiredIphoneImgPath);
		Thread.sleep(1000);
		checkoutPage.clickOnElementById("btn-buy");
		Thread.sleep(1000);
		checkoutPage.clickOnElementById("btn-continue");
		Thread.sleep(1000);
		String productOnCartTitle = checkoutPage.getProductOnCartTitle();
		assert(productOnCartTitle).equals(productName);
		Select quantitytoBuy = new Select(checkoutPage.getElementByXpath("//select[@class=\"form-control select__quantity\"]"));
		WebElement quantitySelected = quantitytoBuy.getFirstSelectedOption();
		Thread.sleep(1000);
		assert(quantitySelected.getAttribute("value").equals("1"));
	}
	
	@Test 
	public void searchForInexistentProduct() throws InterruptedException{
		String desiredIphoneText = "Xphone";
		String productNotFoundMessage = "Não encontramos nenhum resultado na sua busca.";
		Thread.sleep(1000);
		checkoutPage.searchByProduct(desiredIphoneText);
		Thread.sleep(2000);
		String productNotFound = checkoutPage.getElementTextByXpath("//div[@data-tracker=\"productgrid-main\"]//span/span");
		assert(productNotFound).equals(productNotFoundMessage);
		Thread.sleep(2000);
		WebElement imgProductNotFound = checkoutPage.getElementByXpath("//div[@data-tracker=\"productgrid-main\"]//img[@src=\"https://images-submarino.b2w.io/spacey/2018/08/09/oops-sub.png\"]");
		assert(imgProductNotFound).isDisplayed();	
	}

}
