package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class LoginPage {

    HomePage homePage;

    private WebDriver driver;
    private Faker faker = new Faker();
    private By EMAIL_ADDRESS_FIELD = By.cssSelector("#email_create");
    private By CREATE_ACCOUNT_BUTTON = By.cssSelector("#SubmitCreate span");
    private By REG_ID = By.id("id_gender1");
    private By REG_CUSTOMER_FIRSTNAME = By.id("customer_firstname");
    private By REG_CUSTOMER_LASTNAME = By.id("customer_lastname");
    private By REG_PASSWORD_FIELD = By.id("passwd");
    private By REG_SELECT_DATE_OF_BIRTH_DAY = By.id("days");
    private By REG_SELECT_DATE_OF_BIRTH_MONTHS = By.id("months");
    private By REG_SELECT_DATE_OF_BIRTH_YEARS = By.id("years");
    private By REG_ADDRESS_FIRSTNAME = By.id("firstname");
    private By REG_ADDRESS_LASTNAME = By.id("lastname");
    private By REG_ADDRESS_STREET = By.id("address1");
    private By REG_ADDRESS_CITY = By.id("city");
    private By REG_SELECT_STATE = By.id("id_state");
    private By REG_POSTCODE = By.id("postcode");
    private By REG_MOBILE_PHONE = By.id("phone_mobile");
    private By REG_ALIAS = By.id("alias");
    private By REG_REGISTER_BUTTON = By.id("submitAccount");
    private By LOG_EMAIL = By.id("email");
    private By LOG_PASSWORD = By.id("passwd");
    private By LOG_LOGIN_BUTTON = By.id("SubmitLogin");
    private By LOGOUT_BUTTON = By.cssSelector(".logout");


    private String customer_firstname = faker.name().firstName();
    private String customer_lastname = faker.name().lastName();
    private String customer_email_address = customer_firstname + customer_lastname + "@gmail.com";
    private String password = String.valueOf(randomFiveNumbers());
    private String customer_street = faker.address().streetAddress();
    private String customer_city = faker.address().city();
    private String customer_street_alias = faker.address().streetAddress();
    private String postcode = String.valueOf(randomFiveNumbers());
    private String mobilePhoneNumber = String.valueOf(faker.number().randomDigit());


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void saveAccountsToFile() throws IOException {
        FileWriter writer = new FileWriter("accounts.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        bufferedWriter.write(customer_email_address + ", " + password);
        bufferedWriter.close();
    }

    public int randomFiveNumbers() {
        Random randomGenerator = new Random();
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
        try {
            File file = new File("accounts.txt");
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                accounts = line.split(",");
            }

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

    public void logout() {
        driver.findElement(LOGOUT_BUTTON).click();
    }

}
