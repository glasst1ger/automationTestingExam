package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.*;
import java.util.List;

public class TermsAndConditions {
    private final WebDriver driver;
    public final String testURL = "http://automationpractice.com/index.php?id_cms=3&controller=cms";

    private final By TERMS_AND_CONDITIONS_TEXT = By.cssSelector("#columns .row");

    FileWriter writer = new FileWriter("termsandconditions.txt");
    BufferedWriter bufferedWriter = new BufferedWriter(writer);

    BufferedReader readerExpected = new BufferedReader(new FileReader("TermsAndConditionsTestFile.txt"));
    BufferedReader readerActual = new BufferedReader(new FileReader("termsandconditions.txt"));

    public TermsAndConditions(WebDriver driver) throws IOException {
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


        for (WebElement text : texts) {
            bufferedWriter.append(text.getText());

        }

        closeWriter();
    }

    public boolean compareTermsAndConditions() throws IOException {

        String line1 = readerExpected.readLine();
        String line2 = readerActual.readLine();

        boolean areEqual = false;

        while (line1 != null || line2 != null) {
            if (line1 == null || line2 == null) {
                areEqual = false;

                break;
            } else if (!line1.equalsIgnoreCase(line2)) {
                areEqual = false;

                break;
            } else {
                areEqual = true;
            }

            line1 = readerExpected.readLine();

            line2 = readerActual.readLine();
        }

        closeReaders();

        return areEqual;
    }

    public void closeWriter() throws IOException {
        bufferedWriter.close();
    }

    public void closeReaders() throws IOException {
        readerExpected.close();
        readerActual.close();
    }
}
