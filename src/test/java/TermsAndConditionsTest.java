import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.TermsAndConditions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TermsAndConditionsTest {
    WebDriver driver;
    HomePage homePage = new HomePage(Utils.getDriver());
    TermsAndConditions termsAndConditions = new TermsAndConditions(Utils.getDriver());


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
        boolean isTermsAndConditionsPageEmpty = true;

        homePage.navigateToHomePage();
        String URL = termsAndConditions.findTermsAndConditionsPage();
        termsAndConditions.navigateToTermsAndConditionsPage(URL);
        termsAndConditions.saveTermsAndConditionsToFile();

        int lines = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("termsandconditions.txt"));
            while (reader.readLine() != null) lines++;
            if (lines > 5) {
                isTermsAndConditionsPageEmpty = false;
            }
            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        assertFalse(isTermsAndConditionsPageEmpty);

    }

    @AfterAll
    static void tearDown() {
        Utils.tearDown();
    }
}
