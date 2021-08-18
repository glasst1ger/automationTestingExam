package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.*;
import java.util.*;

public class LoginPage {

    HomePage homePage;

    private final WebDriver driver;
    private final Faker faker = new Faker();
    Random randomGenerator = new Random();


    private final By EMAIL_ADDRESS_FIELD = By.cssSelector("#email_create");
    private final By CREATE_ACCOUNT_BUTTON = By.cssSelector("#SubmitCreate span");
    private final By REG_ID = By.id("id_gender1");
    private final By REG_CUSTOMER_FIRSTNAME = By.id("customer_firstname");
    private final By REG_CUSTOMER_LASTNAME = By.id("customer_lastname");
    private final By REG_PASSWORD_FIELD = By.id("passwd");
    private final By REG_SELECT_DATE_OF_BIRTH_DAY = By.id("days");
    private final By REG_SELECT_DATE_OF_BIRTH_MONTHS = By.id("months");
    private final By REG_SELECT_DATE_OF_BIRTH_YEARS = By.id("years");
    private final By REG_ADDRESS_FIRSTNAME = By.id("firstname");
    private final By REG_ADDRESS_LASTNAME = By.id("lastname");
    public final By REG_ADDRESS_STREET = By.id("address1");
    public final By REG_ADDRESS_CITY = By.id("city");
    private final By REG_SELECT_STATE = By.id("id_state");
    private final By REG_POSTCODE = By.id("postcode");
    private final By REG_MOBILE_PHONE = By.id("phone_mobile");
    private final By REG_ALIAS = By.id("alias");
    private final By REG_REGISTER_BUTTON = By.id("submitAccount");
    private final By LOG_EMAIL = By.id("email");
    private final By LOG_PASSWORD = By.id("passwd");
    private final By LOG_LOGIN_BUTTON = By.id("SubmitLogin");
    private final By LOGOUT_BUTTON = By.cssSelector(".logout");
    public final By SUCCESSFUL_LOGIN_INDICATOR = By.cssSelector(".info-account");


    public final String successfulLoginText = "Welcome to your account. Here you can manage all of your personal information and orders.";
    private final String customer_firstname = faker.name().firstName();
    private final String customer_lastname = faker.name().lastName();
    private final String customer_email_address = (customer_firstname + customer_lastname + "@gmail.com").toLowerCase();
    private final String password = String.valueOf(randomFiveNumbers());
    public final String customer_street = faker.address().streetAddress();
    public final String customer_city = faker.address().streetAddress();
    private final String customer_street_alias = faker.address().streetAddress();
    private final String postcode = String.valueOf(randomFiveNumbers());
    private final String mobilePhoneNumber = String.valueOf(faker.number().randomDigit());


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void saveAccountsToFile() throws IOException {
        FileWriter writer = new FileWriter("accounts.txt", true);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        bufferedWriter.append(customer_email_address).append(", ").append(password).append("\n");
        bufferedWriter.close();
    }

    public int randomFiveNumbers() {

        int low = 10000;
        int high = 99999;
        return randomGenerator.nextInt(high - low) + low;
    }

    public void inputToEmailAddressField() {
        driver.findElement(EMAIL_ADDRESS_FIELD).sendKeys(customer_email_address);
    }

    public void clickCreateAccountButton() {
        driver.findElement(CREATE_ACCOUNT_BUTTON).click();
    }

    public void fillOutAccountDetails() throws IOException {
        saveAccountsToFile();
        driver.findElement(REG_ID).click();
        driver.findElement(REG_CUSTOMER_FIRSTNAME).sendKeys(customer_firstname);
        driver.findElement(REG_CUSTOMER_LASTNAME).sendKeys(customer_lastname);
        driver.findElement(REG_PASSWORD_FIELD).sendKeys(password);

        Select drpDays = new Select(driver.findElement(REG_SELECT_DATE_OF_BIRTH_DAY));
        drpDays.selectByValue(String.valueOf(faker.number().numberBetween(1, 31)));
        Select drpMonths = new Select(driver.findElement(REG_SELECT_DATE_OF_BIRTH_MONTHS));
        drpMonths.selectByValue(String.valueOf(faker.number().numberBetween(1, 12)));
        Select drpYears = new Select(driver.findElement(REG_SELECT_DATE_OF_BIRTH_YEARS));
        drpYears.selectByValue(String.valueOf(faker.number().numberBetween(1900, 2021)));


        driver.findElement(REG_ADDRESS_STREET).sendKeys(customer_street);
        driver.findElement(REG_ADDRESS_CITY).sendKeys(customer_city);

        Select drpState = new Select(driver.findElement(REG_SELECT_STATE));
        drpState.selectByValue(String.valueOf(faker.number().numberBetween(1, 50)));


        driver.findElement(REG_POSTCODE).sendKeys(postcode);
        driver.findElement(REG_MOBILE_PHONE).sendKeys(mobilePhoneNumber);
        driver.findElement(REG_ALIAS).sendKeys(customer_street_alias);
        driver.findElement(REG_REGISTER_BUTTON).click();

    }

    public String[] accounts() {
        String[] accounts = new String[1];
        int fileLength = 0;
        try {
            File file = new File("accounts.txt");
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                ++fileLength;
                String line = myReader.nextLine();
                if (randomGenerator.nextInt(fileLength) == 0) {
                    accounts = line.split(",");
                }

            }
            myReader.close();

        } catch (FileNotFoundException e) {

            System.out.println("File not found.");
            e.printStackTrace();
        }
        return accounts;
    }

    public void fillOutLoginDetails(String[] accounts) {
        driver.findElement(LOG_EMAIL).sendKeys(accounts[0]);
        driver.findElement(LOG_PASSWORD).sendKeys(accounts[1]);
        driver.findElement(LOG_LOGIN_BUTTON).click();
    }

    public void login() {
        HomePage homePage = new HomePage(driver);
        homePage.navigateToHomePage();
        homePage.loginPage(driver);
        fillOutLoginDetails(accounts());
    }

    public void logout() {
        driver.findElement(LOGOUT_BUTTON).click();
    }


}
