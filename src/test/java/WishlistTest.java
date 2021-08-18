import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.Wishlist;

import static org.junit.jupiter.api.Assertions.*;

public class WishlistTest extends Utils {

    WebDriver driver;
    LoginPage loginPage = new LoginPage(getDriver());
    Wishlist wishlist = new Wishlist(getDriver());


    @BeforeEach
    public void setUp() {
        this.driver = getDriver();
        getWait();
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

    @AfterEach
    public void tearDown() {
        driver.close();
    }

}
