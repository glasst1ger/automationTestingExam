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
public class TermsAndConditionsTest {
    WebDriver driver;
    HomePage homePage = new HomePage(Utils.getDriver());
    TermsAndConditions termsAndConditions;

    {
        try {
            termsAndConditions = new TermsAndConditions(Utils.getDriver());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @BeforeEach
    public void setup() {
        this.driver = Utils.getDriver();
        Utils.getWait();
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

    @AfterAll
    static void tearDown() {
        Utils.tearDown();
    }
}
