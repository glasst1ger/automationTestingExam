import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    public void successfulRegistrationTest() throws IOException {
        homePage.navigateToHomePage();
        homePage.loginPage(driver);
        loginPage.inputToEmailAddressField();
        loginPage.clickCreateAccountButton();
        loginPage.fillOutAccountDetails();

        assertEquals(successfulLoginText, driver.findElement(successfulLoginTextSelector).getText());
    }

    @Test
    public void setSuccessfulLoginTest() {
        homePage.navigateToHomePage();
        homePage.loginPage(driver);
        loginPage.fillOutLoginDetails(loginPage.accounts());

        assertEquals(successfulLoginText, driver.findElement(successfulLoginTextSelector).getText());
    }

    @AfterEach
    public void tearDown() {
        Utils.tearDown();
    }
}
