import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.Accounts;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountsTest extends Utils {
    WebDriver driver;
    Accounts accounts = new Accounts(getDriver());


    @BeforeEach
    public void setUp() {
        this.driver = getDriver();
        getWait();
    }

    @Test
    public void addressChangeTest() {
        accounts.changeAddress();
        String address = driver.findElement(accounts.ADDRESS_BOX).getText();

        System.out.println(address);

        assertTrue(address.contains(accounts.TEST_CITY_ADDRESS));

        accounts.randomizeAddress();


    }

    @AfterEach
    public void tearDown() {
        driver.close();
    }
}
