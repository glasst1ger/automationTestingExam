import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.ListScroll;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListScrollTest extends Utils {

    WebDriver driver;


    @BeforeEach
    public void setUp() {
        this.driver = getDriver();
        getWait();
    }

    @Test
    @DisplayName("Scrolling through multiple pages test")
    @Description("The objective of this test is to scroll through multiple pages and count how many products are there in the given categories.")
    @Severity(SeverityLevel.NORMAL)
    public void listScrollTest() {
        ListScroll listScroll = new ListScroll(driver);
        listScroll.scrollThroughMultiplePages();
        assertEquals(listScroll.testNumberOfProducts, listScroll.numberOfProducts);
    }

    @AfterEach
    public void tearDown() {
        driver.close();
    }

}
