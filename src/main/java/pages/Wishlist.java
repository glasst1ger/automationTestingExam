package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Wishlist {

    WebDriver driver;
    private final By CATEGORY = By.xpath("//*[@id=\"block_top_menu\"]/ul/li[1]/a");
    private final By SUB_CATEGORY = By.xpath("//*[@id=\"subcategories\"]/ul/li[1]/h5/a");
    private final By PRODUCT = By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div/div[2]/h5/a");
    private final By WISHLIST_BUTTON = By.id("wishlist_button");
    private final By CONFIRMATION_MESSAGE = By.xpath("//*[@id=\"product\"]/div[2]/div/div/div/div/p");
    private final By GET_TO_MY_ACCOUNT = By.cssSelector(".account");
    private final By WISHLIST_MANAGER = By.cssSelector(".lnk_wishlist span");
    private final By DELETE_FROM_WISHLIST_BUTTON = By.cssSelector(".icon-remove");
    private final By CLOSE_CONFIRMATION_MESSAGE_BOX = By.cssSelector(".fancybox-close");


    public final String TEST_CONFIRMATION_MESSAGE = "Added to your wishlist.";
    public final By WISHLIST_HISTORY = By.id("block-history");

    public Wishlist(WebDriver driver) {
        this.driver = driver;
    }

    public String addToWishlist() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login();

        driver.findElement(CATEGORY).click();
        driver.findElement(SUB_CATEGORY).click();
        driver.findElement(PRODUCT).click();
        driver.findElement(WISHLIST_BUTTON).click();
        String confirmationMessage = driver.findElement(CONFIRMATION_MESSAGE).getText();
        driver.findElement(CLOSE_CONFIRMATION_MESSAGE_BOX).click();
        return confirmationMessage;
    }

    public boolean removeFromWishlist() {
        LoginPage loginPage = new LoginPage(driver);

        driver.findElement(GET_TO_MY_ACCOUNT).click();
        driver.findElement(WISHLIST_MANAGER).click();
        driver.findElement(DELETE_FROM_WISHLIST_BUTTON).click();

        driver.switchTo().alert().accept();

        driver.findElement(GET_TO_MY_ACCOUNT).click();
        driver.findElement(WISHLIST_MANAGER).click();
        driver.navigate().refresh();
        return isTheProductOnTheWishlist();

    }

    private boolean isTheProductOnTheWishlist() {
        try {
            driver.findElement(WISHLIST_HISTORY);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

}
