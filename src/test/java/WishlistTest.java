import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.Wishlist;

import static org.junit.jupiter.api.Assertions.*;

public class WishlistTest extends Utils {

    WebDriver driver;

    @BeforeEach
    public void setUp() {
        this.driver = getDriver();
        getWait();
    }

    @Test
    @DisplayName("Adding a product to wishlist test")
    public void addToWishlistTest() {
        Wishlist wishlist = new Wishlist(driver);
        String actualWishlistText = wishlist.addToWishlist();

        assertEquals(actualWishlistText, wishlist.TEST_CONFIRMATION_MESSAGE);
    }

    @Test
    @DisplayName("Adding, then removing a product from a wishlist test")
    public void removeFromWishlistTest() {
        LoginPage loginPage = new LoginPage(driver);
        Wishlist wishlist = new Wishlist(driver);
        wishlist.addToWishlist();
        assertFalse(wishlist.removeFromWishlist());
        loginPage.logout();
    }

    @AfterEach
    public void tearDown() {
        driver.close();
    }

}
