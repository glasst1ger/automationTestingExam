package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TermsAndConditions {
    private final WebDriver driver;
    public final String testURL = "http://automationpractice.com/index.php?id_cms=3&controller=cms";

    private final By TERMS_AND_CONDITIONS_TEXT = By.cssSelector("#columns .row");

    public TermsAndConditions(WebDriver driver) {
        this.driver = driver;
    }

    public String findTermsAndConditionsPage() {
        String termsAndConditionsPageURL = null;
        WebElement linkGroup = driver.findElement(By.id("block_various_links_footer"));     //provide a variable for all the links in the footer section

        List<WebElement> links = linkGroup.findElements(By.tagName("a"));                   //get all the links from the above footer section
        for (WebElement link : links) {                                                     //iterate through the links
            if (link.getText().contains("Terms and conditions")) {                          //find relevant link
                termsAndConditionsPageURL = link.getAttribute("href");                // get the url by href attribute
            }
        }

        return termsAndConditionsPageURL;
    }


    public void navigateToTermsAndConditionsPage(String URL) {
        driver.navigate().to(URL);
    }

    public void saveTermsAndConditionsToFile() throws IOException {
        List<WebElement> texts = driver.findElements(TERMS_AND_CONDITIONS_TEXT);


        FileWriter writer = new FileWriter("termsandconditions.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        for (WebElement text : texts) {
            bufferedWriter.append(text.getText());

        }

        bufferedWriter.close();
    }
}
