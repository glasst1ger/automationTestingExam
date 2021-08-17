import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.Accounts;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountsTest {
    WebDriver driver;
    Accounts accounts = new Accounts(Utils.getDriver());


    @BeforeEach
    public void setUp() {
        this.driver = Utils.getDriver();
        Utils.getWait();
    }

    @Test
    public void addressChangeTest() {
        accounts.changeAddress();
        String address = driver.findElement(accounts.ADDRESS_BOX).getText();

        System.out.println(address);

        assertTrue(address.contains(accounts.TEST_CITY_ADDRESS));

        accounts.randomizeAddress();


    }

    @AfterAll
    static void tearDown() {
        Utils.tearDown();
    }
}
