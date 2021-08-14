import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.HomePage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomePageTest {

    HomePage homePage;
    WebDriver driver;

    @BeforeEach
    public void setUp() {
        this.driver = Utils.getDriver();
        Utils.getWait();
    }


    @Test
    @DisplayName("")
    public void correctHomePageUrlTest() {
        driver.get(homePage.URL);
        String homeUrl = homePage.URL;
        assertEquals(homeUrl, driver.getCurrentUrl());
    }

    @AfterEach
    public void tearDown() {
        Utils.tearDown();
    }

}
