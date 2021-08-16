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
    @DisplayName("")
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
    @DisplayName("")
    public void logoutTest() {
        loginPage.logout();
        String loginText = driver.findElement(homePage.loginPageSelector).getText();

        assertEquals("Sign in", loginText); //sign-in szöveget átvinni loginpage-be
    }

    @Test
    @Order(3)
    @DisplayName("")
    public void loginTest() {
        loginPage.login();

        assertEquals(loginPage.successfulLoginText, driver.findElement(loginPage.SUCCESSFUL_LOGIN_INDICATOR).getText());
    }

    @AfterAll
    public static void tearDown() {
        Utils.tearDown();
    }
}
