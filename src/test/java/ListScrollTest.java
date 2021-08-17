import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.ListScroll;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListScrollTest {
    WebDriver driver;
    ListScroll listScroll = new ListScroll(Utils.getDriver());

    @BeforeEach
    public void setUp() {
        this.driver = Utils.getDriver();
        Utils.getWait();
    }

    @Test
    public void listScrollTest() {
        listScroll.scrollThroughMultiplePages();
        assertEquals(listScroll.testNumberOfProducts, listScroll.numberOfProducts);


    }

    @AfterAll
    static void tearDown() {
        Utils.tearDown();
    }

}
