package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    WebDriver driver;

    private final By loginPageSelector = By.cssSelector(".login");
    private final String URL = "http://automationpractice.com/index.php";

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public By getLoginPageSelector() {
        return loginPageSelector;
    }

    public void navigateToHomePage() {
        driver.get(URL);
    }

    public String getURL() {
        return URL;
    }

    public LoginPage loginPage(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        //wait.until(ExpectedConditions.visibilityOfElementLocated(loginPageSelector));
        driver.findElement(loginPageSelector).click();
        return new LoginPage(driver);
    }


}
