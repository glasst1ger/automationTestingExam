package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ListScroll {
    WebDriver driver;

    private final By DRESSES_CATEGORY = By.xpath("//*[@id=\"block_top_menu\"]/ul/li[2]");
    private final By SUBCATEGORIES = By.id("categories_block_left");
    private final By PRODUCT_COUNTER = By.cssSelector(".heading-counter");
    public final int testNumberOfProducts = 5;

    public int numberOfProducts = 0;


    public ListScroll(WebDriver driver) {
        this.driver = driver;
    }

    public void scrollThroughMultiplePages() {
        HomePage homePage = new HomePage(driver);
        homePage.navigateToHomePage();

        List<String> productsByCategory = new ArrayList<>();
        List<String> categoryLinks = new ArrayList<>();

        driver.findElement(DRESSES_CATEGORY).click();
        WebElement linkGroup = driver.findElement(SUBCATEGORIES);


        List<WebElement> links = linkGroup.findElements(By.tagName("a"));
        for (WebElement linkWebElement : links) {
            String categoryLink = linkWebElement.getAttribute("href");
            categoryLinks.add(categoryLink);
            productsByCategory.add(linkWebElement.getText());


        }

        for (String link : categoryLinks) {
            driver.navigate().to(link);

            String productNumber = driver.findElement(PRODUCT_COUNTER).getText();

            numberOfProducts += Integer.parseInt(productNumber.replaceAll("[\\D]", ""));

        }

    }

}
