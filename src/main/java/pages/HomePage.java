package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    WebDriver driver;
    By loginPage = By.cssSelector(".login");
    public static final String URL = "http://automationpractice.com/index.php";

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToHomePage() {
        driver.get(URL);
    }

    public LoginPage loginPage(WebDriver driver) {
        driver.findElement(loginPage).click();
        return new LoginPage(driver);
    }


}
