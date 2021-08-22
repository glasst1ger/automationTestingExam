import io.qameta.allure.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.Accounts;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountsTest extends Utils {
    WebDriver driver;


    @BeforeEach
    public void setUp() {
        this.driver = getDriver();
        getWait();
    }

    @Test
    @DisplayName("Address change test")
    @Description("The objective of this test is to see whether the address can be changed. " +
            "The address is changed to the given test address and then compared. " +
            "After that, the address is randomized in order to reset the test environment.")
    public void addressChangeTest() {
        Accounts accounts = new Accounts(driver);
        accounts.changeAddress();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(accounts.ADDRESS_BOX));
        String address = driver.findElement(accounts.ADDRESS_BOX).getText();


        assertTrue(address.contains(accounts.TEST_CITY_ADDRESS));
        assertTrue(address.contains(accounts.TEST_STREET_ADDRESS));

        accounts.randomizeAddress();


    }

    @AfterEach
    public void tearDown() {
        driver.close();
    }
}
