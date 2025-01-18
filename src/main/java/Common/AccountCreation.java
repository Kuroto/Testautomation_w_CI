package Common;

import io.cucumber.java.en_old.Ac;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.json.JsonOutput;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.Bidi;
import java.time.Duration;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class AccountCreation
{
    private String firstname;
    private String lastname;
    private String email;
    private String confirmEmail;
    private String password;
    private String confirmPassword;

    public WebDriver driver;
    private WebDriverWait wait;

    // Create a new object from SimpleDateFormat with a custom format, so that we can add it later to the email.
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss");
    String formatDateTime = simpleDateFormat.format(new Date());

    public void initiateWebDriver_Chrome()
    {
        // Create some options for Chrome to start with.
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-popup-blocking");

        // Create a new object of a WebDriver. Assign it to a variable already created above.
        driver = new ChromeDriver(options);  // Use ChromeDriver(). Be sure to comment away the other drivers.
    }

    public void initiateWebDriver_Firefox()
    {
        // Create a new object of a WebDriver. Assign it to a variable already created above.
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    public void initiateWebDriver_Edge()
    {
        // Create a new object of a WebDriver. Assign it to a variable already created above.
        driver = new EdgeDriver();
        driver.manage().window().maximize();
    }

    public void closeWebDriver()
    {
        // Close the website.
        driver.quit();
    }

    public void openWebsite()
    {
        // Start by opening the website.
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
        presenceOfElementLocated(driver, By.id("titleText1"));  // Verify the website opened by locating the title.
    }

    public void dateOfBirthElementPresent()
    {
        // Open the date of birth module. Not actually typing in the date, but using the module from the website.
        WebElement dateOfBirth = driver.findElement(By.id("dp"));
        dateOfBirth.click();
        presenceOfElementLocated(driver, By.className("datepicker-dropdown"));  // Verify that the module actually opened.
    }

    public void dateOfBirthDropdown()
    {
        // Click on the month and year at the top, to switch to the Months view.
        WebElement changeDateMonths = driver.findElement(By.cssSelector(".datepicker-days > table:nth-child(1) > thead:nth-child(1) > tr:nth-child(2) > th:nth-child(2)"));
        changeDateMonths.click();

        // Click on the year at the top, to switch to Years view.
        WebElement changeDateYears = driver.findElement(By.cssSelector(".datepicker-months > table:nth-child(1) > thead:nth-child(1) > tr:nth-child(2) > th:nth-child(2)"));
        changeDateYears.click();

        // Click on the previous button twice, to go back some years so the year 2000 is visible.
        WebElement previousYears = driver.findElement(By.cssSelector(".datepicker-years > table:nth-child(1) > thead:nth-child(1) > tr:nth-child(2) > th:nth-child(1)"));
        previousYears.click();
        previousYears.click();

        // Click on the year 2000.
        WebElement selectYear = driver.findElement(By.cssSelector("span.year:nth-child(2)"));
        selectYear.click();

        // Click on January
        WebElement selectMonth = driver.findElement(By.cssSelector("span.month:nth-child(1)"));
        selectMonth.click();

        // Click on the 1st of January
        WebElement selectDay = driver.findElement(By.cssSelector(".datepicker-days > table:nth-child(1) > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(7)"));
        selectDay.click();
    }

    public String enterTheFirstName(String firstname)
    {
        // Get the "First Name" element.
        WebElement firstnameElement = driver.findElement(By.id("member_firstname"));
        firstnameElement.sendKeys(firstname);

        // Store the value of the input field in a variable, then return it.
        return this.firstname = firstnameElement.getAttribute("value");
    }

    public String enterTheLastName(String lastname)
    {
        // Get the "Last Name" element.
        WebElement lastnameElement = driver.findElement(By.id("member_lastname"));
        lastnameElement.sendKeys(lastname);

        // Store the value of the input field in a variable, then return it.
        return this.lastname = lastnameElement.getAttribute("value");
    }

    public String enterTheEmail(String email)
    {
        // Get the "Email Address" element.
        WebElement emailElement = driver.findElement(By.id("member_emailaddress"));

        // Run the function to add current date and time to the email.
        String newEmail = addEmailDate(email);
        emailElement.sendKeys(newEmail);

        /* THIS WAS AN EARLIER EMAIL INCREMENT VERSION, WITH NUMBERS. IT WORKS BUT STILL MANUAL WORK NECESSARY EACH RUN.
        // Run the function of incrementing the email with a number.
        String newEmail = emailIncrement(email);
        emailElement.sendKeys(newEmail);
         */

        // Store the value of the input field in a variable, then return it.
        return this.email = emailElement.getAttribute("value");
    }

    public String confirmTheEmail(String confirmEmail)
    {
        // Get the "Confirm Email Address" element and send in the email via the function argument.
        WebElement confirmEmailElement = driver.findElement(By.id("member_confirmemailaddress"));

        // Run the function to add current date and time to the email.
        String newConfirmEmail = addEmailDate(confirmEmail);
        confirmEmailElement.sendKeys(newConfirmEmail);

        /* THIS WAS AN EARLIER EMAIL INCREMENT VERSION, WITH NUMBERS. IT WORKS BUT STILL MANUAL WORK NECESSARY EACH RUN.
        // Run the function of incrementing the email with a number.
        String newConfirmEmail = emailIncrement(confirmEmail);
        confirmEmailElement.sendKeys(newConfirmEmail);
         */

        // Store the value of the input field in a variable, then return it.
        return this.confirmEmail = confirmEmailElement.getAttribute("value");
    }

    public String enterPassword(String password)
    {
        // Get the "Password" element.
        WebElement enterPasswordElement = driver.findElement(By.id("signupunlicenced_password"));
        enterPasswordElement.sendKeys(password);

        // Store the value of the input field in a variable, then return it.
        return this.password = enterPasswordElement.getAttribute("value");
    }

    public String confirmPassword(String confirmPassword)
    {
        // Get the "Retype your password" element.
        WebElement confirmPasswordElement = driver.findElement(By.id("signupunlicenced_confirmpassword"));
        confirmPasswordElement.sendKeys(confirmPassword);

        // This will click anywhere on the form so that the password will "take".
        // It will display an error message if there is a mismatch of passwords.
        Actions action = new Actions(driver);
        action.click().perform();

        // Store the value of the input field in a variable, then return it.
        return this.confirmPassword = confirmPasswordElement.getAttribute("value");
    }

    public void checkTheTermsAndConditionsBox()
    {
        // Find the "Terms and Conditions" check box element.
        WebElement termsConditions = driver.findElement(By.cssSelector("label[for='sign_up_25'] > .box"));

        // Scroll down the page so that the checkboxes will be visible.
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");

        // Wait for the element to be clickable.
        elementToBeClickable(driver, By.cssSelector("label[for='sign_up_25'] > .box"));
        termsConditions.click();

        /* Initialize the WebDriverWait. Then wait for an element to be visible. I did it this way so that I can reuse
           it and use other wait.until() conditions if necessary. */
        invokeWait();
        WebElement termsChecked = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("div.row:nth-child(12) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > " +
                        "label:nth-child(3) > span:nth-child(1)")));
    }

    public void checkTheAgeOfConsentBox()
    {
        // Find the "Age of Consent" check box element.
        WebElement ageOfConsent = driver.findElement(By.cssSelector("label[for='sign_up_26'] > .box"));

        // Scroll down the page so that the checkboxes will be visible.
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");

        // Wait for the element to be clickable.
        elementToBeClickable(driver, By.cssSelector("label[for='sign_up_26'] > .box"));
        ageOfConsent.click();

        /* Initialize the WebDriverWait. Then wait for an element to be visible. I did it this way so that I can reuse
           it and use other wait.until() conditions if necessary. */
        invokeWait();
        WebElement ageChecked = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("div.row:nth-child(12) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > " +
                        "label:nth-child(3) > span:nth-child(1)")));
    }

    public void codeOfEthics()
    {
        // Find the "Code of Ethics" check box element.
        WebElement codeOfEthics = driver.findElement(By.cssSelector("label[for='fanmembersignup_agreetocodeofethicsandconduct'] > .box"));

        // Scroll down the page so that the checkboxes will be visible.
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");

        // Wait for the element to be clickable.
        elementToBeClickable(driver, By.cssSelector("label[for='fanmembersignup_agreetocodeofethicsandconduct'] > .box"));
        codeOfEthics.click();

        /* Initialize the WebDriverWait. Then wait for an element to be visible. I did it this way so that I can reuse
           it and use other wait.until() conditions if necessary. */
        invokeWait();
        WebElement ethicsChecked = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector
                ("div.md-checkbox:nth-child(7) > label:nth-child(3) > span:nth-child(1)")));
    }

    public void submitForm()
    {
        // Find the submit button "Confirm and join".
        WebElement submitForm = driver.findElement(By.cssSelector("input[name='join']"));

        // Scroll down the page so that the checkboxes will be visible.
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");

        submitForm.submit();
    }

    public String accountSuccessful()
    {
        String successful;

        // Wait for the membership number text on the account created page to be visible.
        invokeWait();
        WebElement membershipNumberTest = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                ("/html/body/div/div[2]/div/div/h5")));

        // Find membership number header text
        WebElement memberAcceptedText = driver.findElement(By.xpath("/html/body/div/div[2]/div/div/h5"));
        successful = memberAcceptedText.getText();

        return successful;
    }

    public boolean lastNameMissing()
    {
        // Set false on variable for last name missing. This will be used as a check for the assert.
        boolean isLastNameMissing = false;

        // Find the error field from last name after we've tried to submit the form.
        WebElement lastNameErrorField = driver.findElement(By.xpath("//*[@id=\"signup_form\"]/div[5]/div[2]/div/span/span"));
        String lastNameErrorString = lastNameErrorField.getText();

        // Check if the error message of last name is displayed.
        if (lastNameErrorString.equals("Last Name is required"))
        {
            return isLastNameMissing = true;
        }
        else
        {
            return isLastNameMissing;
        }
    }

    public boolean passwordMismatch()
    {
        // Set false on variable for equal password to be invalid. This will be used as a check for the assert.
        boolean doesPasswordMatch = false;

        // Find the error field from password verification after we've tried to submit the form.
        WebElement passwordErrorField = driver.findElement(By.xpath("//*[@id=\"signup_form\"]/div[8]/div/div[2]/div[2]/div/span/span"));
        String passwordErrorString = passwordErrorField.getText();

        // Check if the error message of password verification field is displayed.
        if (passwordErrorString.equals("Password did not match"))
        {
            return doesPasswordMatch = true;
        }
        else
        {
            return doesPasswordMatch;
        }
    }

    public boolean termsNotChecked()
    {
        // Set false on variable if the terms and conditions box have been checked. This will be used as a check for the assert.
        boolean termsChecked = false;

        // Find the error field from terms verification after we've tried to submit the form.
        WebElement termsErrorField = driver.findElement(By.xpath("//*[@id=\"signup_form\"]/div[11]/div/div[2]/div[1]/span/span"));
        String termsErrorString = termsErrorField.getText();

        // Check if the error message of the terms verification field is displayed.
        if (termsErrorString.equals("You must confirm that you have read and accepted our Terms and Conditions"))
        {
            return termsChecked = true;
        }
        else
        {
            return termsChecked;
        }
    }

    /*
    **************************************************************************************************************
    ********                                        WAIT FUNCTIONS                                        ********
    **************************************************************************************************************
    */

    public void invokeWait()
    {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void presenceOfElementLocated(WebDriver driver, By by)
    {
        (new WebDriverWait(driver, Duration.ofSeconds(5))).
                until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void elementToBeClickable(WebDriver driver, By by)
    {
        (new WebDriverWait(driver, Duration.ofSeconds(5))).
                until(ExpectedConditions.elementToBeClickable(by));
    }

    /*
    **************************************************************************************************************
    ********                                  INCREMENT EMAIL FUNCTIONS                                   ********
    **************************************************************************************************************
    */

    public String addEmailDate(String email)
    {
        String[] divideEmail = email.split("@");  // Divide the email into 2 parts, before the @ symbol and after the @ symbol.
        String emailPrefix = divideEmail[0];  // This is text before @ symbol. "0" is before the @ symbol, "1" is after.
        String emailSuffix = "@" + divideEmail[1];  // This is text after the @ symbol. "0" is before the @ symbol, "1" is after.

        // Add the current date and time to email prefix.
        emailPrefix = emailPrefix + "_" + formatDateTime;

        // Return the new prefix and add the suffix of the email.
        return emailPrefix + emailSuffix;
    }

    // NOT USED BUT CAN BE USED FOR FUNZIES
    public static String emailIncrement(String email)
    {
        String[] divideEmail = email.split("@");  // Divide the email into 2 parts, before the @ symbol and after the @ symbol.
        String emailPrefix = divideEmail[0];  // This is text before @ symbol. "0" is before the @ symbol, "1" is after.
        String emailSuffix = "@" + divideEmail[1];  // This is text after the @ symbol. "0" is before the @ symbol, "1" is after.

        // Create a variable, this will be used to extract any numbers at the end of the email prefix if any exists.
        int emailPrefixNumber = 0;
        int i = emailPrefix.length() - 1;  // Get the last character of the email prefix.

        // Checks if the character in email prefix is a number.
        while (i >= 0 && Character.isDigit(emailPrefix.charAt(i)))
        {
            // If it is a number, go back one character and repeat until no more numbers found in the email prefix.
            i--;
        }

        // This if statement is only executed if there is a number at the end of email prefix.
        if (i < emailPrefix.length() - 1)
        {
            // This extracts any existing number from email prefix, assigns it to a variable, then converts back to an integer with "Integer.parseInt".
            emailPrefixNumber = Integer.parseInt(emailPrefix.substring(i + 1));
            emailPrefix = emailPrefix.substring(0, i + 1);  // This stores only the text from the email prefix.
        }

        // Increment the number that was extracted.
        emailPrefixNumber++;

        // Return the text part of the email prefix and append the incremented number.
        return emailPrefix + emailPrefixNumber + emailSuffix;
    }
}
