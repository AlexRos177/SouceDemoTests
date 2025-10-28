package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;

public class CartTest extends BaseTest {

    @Test
    public void addToCartTest() {
        LoginPage login = new LoginPage(driver);
        login.login("standard_user", "secret_sauce");

        InventoryPage inventory = new InventoryPage(driver);
        inventory.addFirstItemToCart();
        inventory.goToCart();

        CartPage cart = new CartPage(driver);
        Assert.assertTrue(cart.isItemInCart(), "Item is not in cart");
    }
}



