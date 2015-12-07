package TestClasses;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import DataLayer.WalmartLabsDataProvider;
import PageObject.WalmartLabsPageObject;

public class WalmartLabsTest {
	@Test(dataProvider = "WalMart e-commerce Transaction", dataProviderClass = WalmartLabsDataProvider.class)
	public void testWalmartAutomation(String email, String pwd, String search) throws InterruptedException {
		
		Logger log = Logger.getLogger(WalmartLabsTest.class);
		WalmartLabsPageObject lp = new WalmartLabsPageObject();

		lp.setBrowser();

		lp.openUrl();
		// click sign in
		lp.clickSignInButton();

		// enter username
		lp.enterEmailId(email);

		// enter pwd
		lp.enterPassword(pwd);

		// click on sign in
		lp.login();
		
		//Searching for an item
		lp.enterSearch(search);
		
		//getting the product id of the item while selecting
		String expectedProductId = lp.productSelection();
		
		//Checking if the selected item is not out of stock then continue else return
		if(!lp.clickAddToCart())
		{
			lp.closeBrowser();
			return;
		}
		lp.viewCart();
		
		//getting the product id of the item from the cart
		String actualProductId = lp.getActualProductId();
		log.info(search +" Actual Product ID: " + actualProductId);
		log.info(search +" Expected Product ID: " + expectedProductId);
		
		//Validating the item present in the cart using product ID
		Assert.assertEquals(expectedProductId, actualProductId);
		Assert.assertEquals(lp.getQuantity(), "(1 item)");
		
		log.info("Validated the item added in the cart and is the only item in the cart");
		
		//removing the item from the cart
		lp.removeCartItem();
		log.info("Item removed from the cart");
		lp.closeBrowser();
	}
}



