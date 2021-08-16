import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.LoginPage;
import pages.Wishlist;

import static org.junit.jupiter.api.Assertions.*;

public class WishlistTest {

    WebDriver driver;
    HomePage homePage = new HomePage(Utils.getDriver());
    LoginPage loginPage = new LoginPage(Utils.getDriver());
    Wishlist wishlist = new Wishlist(Utils.getDriver());


    @BeforeEach
    public void setUp() {
        this.driver = Utils.getDriver();
        Utils.getWait();
    }

    @Test
    public void addToWishlistTest() {
        String actualWishlistText = wishlist.addToWishlist();

        assertEquals(actualWishlistText, wishlist.TEST_CONFIRMATION_MESSAGE);
    }

    @Test
    public void removeFromWishlistTest() {
        wishlist.addToWishlist();
        assertFalse(wishlist.removeFromWishlist());
        loginPage.logout();
    }

    @AfterAll
    static void tearDown() {
        Utils.tearDown();
    }

}
