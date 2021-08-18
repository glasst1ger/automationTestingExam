import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.HomePage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomePageTest extends Utils {


    WebDriver driver;

    @BeforeEach
    public void setUp() {
        this.driver = getDriver();
        getWait();
    }


    @Test
    @DisplayName("Website is up and running test")
    @Story("A user reported that sometimes the website is not available")
    public void correctHomePageUrlTest() {
        HomePage homePage = new HomePage(driver);
        driver.get(homePage.getURL());
        String homeUrl = homePage.getURL();
        assertEquals(homeUrl, driver.getCurrentUrl());
    }

    @AfterEach
    public void tearDown() {
        driver.close();
    }

}
