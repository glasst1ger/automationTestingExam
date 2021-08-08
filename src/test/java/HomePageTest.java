import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.HomePage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomePageTest {

    Utils utils;
    HomePage homePage;
    WebDriver driver;

    @BeforeEach
    public void setUp() {
        this.driver = Utils.getDriver();
        Utils.getWait();
    }


    @Test
    public void correctHomePageUrlTest() {
        driver.get(HomePage.URL);
        String homeUrl = HomePage.URL;
        assertEquals(homeUrl, driver.getCurrentUrl());
    }

    @AfterEach
    public void tearDown() {
        Utils.tearDown();
    }

}
