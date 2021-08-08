import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.LoginPage;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginPageTest {

    WebDriver driver;
    HomePage homePage = new HomePage(Utils.getDriver());
    LoginPage loginPage = new LoginPage(Utils.getDriver());

    private final By successfulLoginTextSelector = By.cssSelector(".info-account");
    private final String successfulLoginText = "Welcome to your account. Here you can manage all of your personal information and orders.";

    @BeforeEach
    public void setUp() {
        this.driver = Utils.getDriver();
        Utils.getWait();
    }

    @Test
    @Order(1)
    public void registrationTest() throws IOException {
        homePage.navigateToHomePage();
        homePage.loginPage(driver);
        loginPage.inputToEmailAddressField();
        loginPage.clickCreateAccountButton();
        loginPage.fillOutAccountDetails();

        assertEquals(successfulLoginText, driver.findElement(successfulLoginTextSelector).getText());
    }

    @Test
    @Order(2)
    public void logoutTest() {
        loginPage.logout();
        String loginText = driver.findElement(homePage.loginPageSelector).getText();
        
        assertEquals("Sign in", loginText);
    }

    @Test
    @Order(3)
    public void loginTest() {
        homePage.navigateToHomePage();
        homePage.loginPage(driver);
        loginPage.fillOutLoginDetails(loginPage.accounts());

        assertEquals(successfulLoginText, driver.findElement(successfulLoginTextSelector).getText());
    }

    @AfterAll
    public static void tearDown() {
        Utils.tearDown();
    }
}
