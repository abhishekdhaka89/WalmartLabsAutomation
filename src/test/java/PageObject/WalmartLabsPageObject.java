package PageObject;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class WalmartLabsPageObject {
	static WebDriver driver;
	static int TIME_TO_WAIT = 120;
	implicitWaitHelper lpo = new implicitWaitHelper();
	private String item;
	
	Logger log = Logger.getLogger(WalmartLabsPageObject.class);
	
	public WebDriver getDriver() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;
	}
	public void setBrowser() {
	// open the browser
		System.setProperty("webdriver.chrome.driver", "chrome_driver/chromedriver.exe");
		driver = new ChromeDriver();
	}
	public void openUrl() throws InterruptedException {
		driver.get("http://www.walmart.com/");
		Thread.sleep(3000);
	}

	public void clickSignInButton() throws InterruptedException {
		driver.findElement(By.linkText("Sign In")).click();
		lpo.implicitWait(driver);
	}

	public void enterEmailId(String email) throws InterruptedException {
		driver.findElement(By.cssSelector("#login-username")).sendKeys(email);
		lpo.implicitWait(driver);
	}

	public void enterPassword(String pwd) throws InterruptedException {
		driver.findElement(By.id("login-password")).sendKeys(pwd);
		lpo.implicitWait(driver);
	}
	
	public void login() throws InterruptedException {
		driver.findElement(By.cssSelector(".btn-block-max-s")).click();
		lpo.implicitWait(driver);
		Thread.sleep(5000);
	}

	
	public void enterSearch(String search) throws InterruptedException {
		this.item = search;
		log.info("Logged in SuccessFully and looking for "+item);
		driver.findElement(By.id("search")).sendKeys(search);
		driver.findElement(By.id("search")).click();
		driver.findElement(By.id("search")).sendKeys(Keys.ENTER);
		lpo.implicitWait(driver);
		Thread.sleep(5000);
	}

	
	
	
	public String productSelection() throws InterruptedException
	{
		String productID = "";
		WebElement we = driver.findElement(By.className("js-product-title"));
		try
		{
			String temp = we.getAttribute("href").toString();
			productID = temp.substring(temp.lastIndexOf("/") + 1);
			we.click();
			Thread.sleep(5000);
			return productID;
		}
		catch(NullPointerException npe)
		{
			try
			{
				we = driver.findElement(By.cssSelector("#sponsored-container-middle-2 > div > div.js-carousel-n-up.carousel-n-up-responsive.carousel-hotspot.carousel.js-carousel-sponsored-products > ol > div > div > li.selected.carousel-slide.sponsored-product.wpa-product.js-wpa-product.slick-slide.slick-current.slick-active > div"));
				
			}
			catch(NoSuchElementException nsee)
			{
				we = driver.findElement(By.cssSelector("#sponsored-container-middle-2 > div > div > div.js-carousel-n-up.carousel-n-up-responsive.carousel-hotspot.carousel.js-carousel-sponsored-products > ol > div > div > li.carousel-slide.sponsored-product.wpa-product.js-wpa-product.selected.slick-slide.slick-current.slick-active > div"));
			}
			productID = we.getAttribute("data-product-id").toString();
			we.click();
			Thread.sleep(5000);
			return productID;
			
		}
	}

	public boolean clickAddToCart() throws InterruptedException {
		try
		{
			if(item.equals("iPhone"))
			{
				driver.findElement(By.className("variant-swatch")).click();
				Thread.sleep(5000);
			}
			driver.findElement(By.id("WMItemAddToCartBtn")).click();
			lpo.implicitWait(driver);
			Thread.sleep(5000);
			return true;
		}
		catch(NoSuchElementException nsee)
		{
			driver.findElement(By.className("price-oos"));
			log.info("Oops !! Selected Item is out of Stock");
			return false;
		}
	}
	
	public void viewCart() throws InterruptedException {
		driver.findElement(By.id("PACViewCartBtn")).click();
		lpo.implicitWait(driver);
		Thread.sleep(5000);
	}
	
	public String getActualProductId()
	{
		String temp = "";
		temp = driver.findElement(By.cssSelector("#CartItemInfo")).getAttribute("data-us-item-id").toString();
		return temp;
	}
	

	public String getQuantity() {
		String result = driver.findElement(By.cssSelector("#spa-layout > div > div > div.responsive-container.pos-relative.clearfix > div > div.persistent-order-summary.cart-pos > div.pos-totals.s-padding-sides.js-pos-totals > div.pos-totals-row.js-subtotal > p:nth-child(1) > small")).getText();
		return result;
	}


	public void removeCartItem() throws InterruptedException {
		driver.findElement(By.id("CartRemItemBtn")).click();
		Thread.sleep(5000);
	
	}
	public void closeBrowser() {
		driver.close();
	}
	
}

class implicitWaitHelper
{
	public void implicitWait(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
}
