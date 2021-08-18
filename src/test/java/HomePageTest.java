import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.HomePage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomePageTest {


    WebDriver driver;

    @BeforeEach
    public void setUp() {
        this.driver = Utils.getDriver();
        Utils.getWait();
    }


    @Test
    @DisplayName("Website up and running test")
    public void correctHomePageUrlTest() {
        HomePage homePage = new HomePage(driver);
        driver.get(homePage.getURL());
        String homeUrl = homePage.getURL();
        assertEquals(homeUrl, driver.getCurrentUrl());
    }

    @AfterEach
    public void tearDown() {
        Utils.tearDown();
    }

}
