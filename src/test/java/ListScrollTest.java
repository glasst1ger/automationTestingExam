import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.ListScroll;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListScrollTest extends Utils {
    WebDriver driver;
    ListScroll listScroll = new ListScroll(getDriver());

    @BeforeEach
    public void setUp() {
        this.driver = getDriver();
        getWait();
    }

    @Test
    public void listScrollTest() {
        listScroll.scrollThroughMultiplePages();
        assertEquals(listScroll.testNumberOfProducts, listScroll.numberOfProducts);


    }

    @AfterEach
    public void tearDown() {
        driver.close();
    }

}
