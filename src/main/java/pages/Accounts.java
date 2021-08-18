package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Accounts {
    WebDriver driver;
    private final Faker faker = new Faker();


    private final By MY_ADDRESSES = By.xpath("//*[@id=\"center_column\"]/div/div[1]/ul/li[3]/a/span");
    private final By UPDATE_ADDRESS_BUTTON = By.xpath("//*[@id=\"center_column\"]/div[1]/div/div/ul/li[9]/a[1]");
    private final String UPDATED_STREET_ADDRESS = "Test Address 123";
    private final String UPDATED_CITY_ADDRESS = "Testemonia";
    private final String RANDOM_STREET_ADDRESS = faker.address().streetAddress();
    private final String RANDOM_CITY_ADDRESS = faker.address().streetAddress();
    private final By SAVE_UPDATED_ADDRESS_BUTTON = By.id("submitAddress");
    public final By ADDRESS_BOX = By.cssSelector(".box");


    public final String TEST_STREET_ADDRESS = UPDATED_STREET_ADDRESS;
    public final String TEST_CITY_ADDRESS = UPDATED_CITY_ADDRESS;


    public Accounts(WebDriver driver) {
        this.driver = driver;
    }

    public void changeAddress() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(MY_ADDRESSES));
        driver.findElement(MY_ADDRESSES).click();
        driver.findElement(UPDATE_ADDRESS_BUTTON).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.REG_ADDRESS_STREET));
        driver.findElement(loginPage.REG_ADDRESS_STREET).sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
        driver.findElement(loginPage.REG_ADDRESS_STREET).sendKeys(UPDATED_STREET_ADDRESS);
        driver.findElement(loginPage.REG_ADDRESS_CITY).sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
        driver.findElement(loginPage.REG_ADDRESS_CITY).sendKeys(UPDATED_CITY_ADDRESS);
        driver.findElement(SAVE_UPDATED_ADDRESS_BUTTON).click();


    }

    public void randomizeAddress() {
        LoginPage loginPage = new LoginPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(UPDATE_ADDRESS_BUTTON));
        driver.findElement(UPDATE_ADDRESS_BUTTON).click();

        driver.findElement(loginPage.REG_ADDRESS_STREET).sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
        driver.findElement(loginPage.REG_ADDRESS_STREET).sendKeys(RANDOM_STREET_ADDRESS);
        driver.findElement(loginPage.REG_ADDRESS_CITY).sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
        driver.findElement(loginPage.REG_ADDRESS_CITY).sendKeys(RANDOM_CITY_ADDRESS);
        driver.findElement(SAVE_UPDATED_ADDRESS_BUTTON).click();
    }
}
