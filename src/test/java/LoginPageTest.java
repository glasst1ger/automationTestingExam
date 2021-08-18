import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.LoginPage;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class LoginPageTest extends Utils {

    WebDriver driver;


    @BeforeEach
    public void setUp() {
        this.driver = getDriver();
        getWait();
    }

    @Test
    @Order(1)
    @DisplayName("Automated registration test with randomized data")
    public void registrationTest() throws IOException {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        homePage.navigateToHomePage();
        homePage.loginPage(driver);
        loginPage.inputToEmailAddressField();
        loginPage.clickCreateAccountButton();
        loginPage.fillOutAccountDetails();

        assertEquals(loginPage.successfulLoginText, driver.findElement(loginPage.SUCCESSFUL_LOGIN_INDICATOR).getText());
    }


    @Test
    @Order(2)
    @DisplayName("Login test with a random account and password after a successful registration")
    public void loginTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login();

        assertEquals(loginPage.successfulLoginText, driver.findElement(loginPage.SUCCESSFUL_LOGIN_INDICATOR).getText());
    }

    @Test
    @Order(3)
    @DisplayName("Logout test after a successful registration test")
    public void logoutTest() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        loginPage.login();
        loginPage.logout();
        String loginText = driver.findElement(homePage.getLoginPageSelector()).getText();

        assertEquals("Sign in", loginText);
    }

    @AfterEach
    public void tearDown() {
        driver.close();
    }
}
