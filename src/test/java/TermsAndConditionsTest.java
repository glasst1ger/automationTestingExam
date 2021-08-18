import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.TermsAndConditions;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TermsAndConditionsTest extends Utils {
    WebDriver driver;
    HomePage homePage = new HomePage(getDriver());
    TermsAndConditions termsAndConditions;

    {
        try {
            termsAndConditions = new TermsAndConditions(getDriver());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @BeforeEach
    public void setup() {
        this.driver = getDriver();
        getWait();
    }

    @Test
    @Order(1)
    @DisplayName("")
    public void getTermsAndConditions() {
        homePage.navigateToHomePage();
        String URL = termsAndConditions.findTermsAndConditionsPage();
        termsAndConditions.navigateToTermsAndConditionsPage(URL);

        assertEquals(URL, termsAndConditions.testURL);
    }

    @Test
    @Order(2)
    @DisplayName("")
    public void isTermsAndConditionsPageEmpty() throws IOException {

        homePage.navigateToHomePage();
        String URL = termsAndConditions.findTermsAndConditionsPage();
        termsAndConditions.navigateToTermsAndConditionsPage(URL);
        termsAndConditions.saveTermsAndConditionsToFile();

        assertTrue(termsAndConditions.compareTermsAndConditions());

    }

    @AfterEach
    public void tearDown() {
        driver.close();
    }
}
