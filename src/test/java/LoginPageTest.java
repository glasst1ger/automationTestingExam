import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.LoginPage;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class LoginPageTest {

    WebDriver driver;
    HomePage homePage = new HomePage(Utils.getDriver());
    LoginPage loginPage = new LoginPage(Utils.getDriver());

    @BeforeEach
    public void setUp() {
        this.driver = Utils.getDriver();
        Utils.getWait();
    }

    @Test
    @Order(1)
    @DisplayName("Automated registration test with randomized data")
    public void registrationTest() throws IOException {
        homePage.navigateToHomePage();
        homePage.loginPage(driver);
        loginPage.inputToEmailAddressField();
        loginPage.clickCreateAccountButton();
        loginPage.fillOutAccountDetails();

        assertEquals(loginPage.successfulLoginText, driver.findElement(loginPage.SUCCESSFUL_LOGIN_INDICATOR).getText());
    }

    @Test
    @Order(2)
    @DisplayName("Logout test after a successful registration test")
    public void logoutTest() {
        loginPage.logout();
        String loginText = driver.findElement(homePage.getLoginPageSelector()).getText();

        assertEquals("Sign in", loginText);
    }

    @Test
    @Order(3)
    @DisplayName("Login test with a random account and password after a successful registration and logout test")
    public void loginTest() {
        loginPage.login();

        assertEquals(loginPage.successfulLoginText, driver.findElement(loginPage.SUCCESSFUL_LOGIN_INDICATOR).getText());
    }

    @AfterAll
    public static void tearDown() {
        Utils.tearDown();
    }
}
