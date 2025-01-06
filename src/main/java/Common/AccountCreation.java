package Common;

import io.cucumber.java.en_old.Ac;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.json.JsonOutput;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.Bidi;
import java.time.Duration;

import static com.sun.imageio.plugins.jpeg.JPEG.version;

public class AccountCreation
{
    private String firstname;
    private String lastname;
    private String email;
    private String confirmEmail;
    private String password;
    private String confirmPassword;

    public WebDriver driver;

    // Create the Constructor. This will be used to call all the other functions. Currently not needed.
    /*public AccountCreation ()
    {

    }*/

    public void initiateWebDriver()
    {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        // Create a new object of a WebDriver. Assign it to a variable already created above.
        driver = new ChromeDriver(options);  // Use ChromeDriver(). Be sure to comment away the other drivers.
        //driver = new FirefoxDriver();  // Use FirefoxDriver(). Be sure to comment away the other drivers.
        //driver = new EdgeDriver();  // Use EdgeDriver(). Be sure to comment away the other drivers.
        //driver = new SafariDriver();  // Use SafariDriver(). Be sure to comment away the other drivers.
        //driver = new InternetExplorerDriver();  // Use SafariDriver(). Be sure to comment away the other drivers.
        //driver.manage().window().maximize();
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
        // Get the "Email Address" element and send in the email via the function argument.
        WebElement emailElement = driver.findElement(By.id("member_emailaddress"));

        // Run the function of incrementing the email with a number.
        String newEmail = emailIncrement(email);
        emailElement.sendKeys(newEmail);

        // Store the value of the input field in a variable, then return it.
        return this.email = emailElement.getAttribute("value");
    }

    public String confirmTheEmail(String confirmEmail)
    {
        // Get the "Confirm Email Address" element and send in the email via the function argument.
        WebElement confirmEmailElement = driver.findElement(By.id("member_confirmemailaddress"));

        // Run the function of incrementing the email with a number.
        String newConfirmEmail = emailIncrement(confirmEmail);
        confirmEmailElement.sendKeys(newConfirmEmail);

        // Store the value of the input field in a variable, then return it.
        return this.confirmEmail = confirmEmailElement.getAttribute("value");
    }

    public String enterPassword(String password)
    {
        // Get the "Password" element and send in the password via the function argument.
        WebElement enterPasswordElement = driver.findElement(By.id("signupunlicenced_password"));
        enterPasswordElement.sendKeys(password);

        // Store the value of the input field in a variable, then return it.
        return this.password = enterPasswordElement.getAttribute("value");
    }

    public String confirmPassword(String confirmPassword)
    {
        // Get the "Retype your password" element and send in the password via the function argument.
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
        WebElement termsConditions = driver.findElement(By.cssSelector("div.row:nth-child(12) > div:nth-child(1) >" +
                "div:nth-child(2) > div:nth-child(1) > label:nth-child(3) > span:nth-child(3)"));

        // User Actions, this will focus on the element (terms & conditions checkbox) by scrolling down to it,
        // making sure it is in the view of the end-user and the script. This will make it possible to click on it.
        Actions actions = new Actions(driver);
        actions.moveToElement(termsConditions);
        actions.perform();

        // Wait for the element to be clickable.
        elementToBeClickable(driver, By.cssSelector("div.row:nth-child(12) > div:nth-child(1) >" +
                "div:nth-child(2) > div:nth-child(1) > label:nth-child(3) > span:nth-child(3)"));

        termsConditions.click();
    }

    public void checkTheAgeOfConsentBox()
    {
        // Find the "Age of Consent" check box element.
        WebElement ageOfConsent = driver.findElement(By.cssSelector("div.row:nth-child(12) > div:nth-child(1) >" +
                "div:nth-child(2) > div:nth-child(2) > label:nth-child(3) > span:nth-child(3)"));

        // User Actions, this will focus on the element (terms & conditions checkbox) by scrolling down to it,
        // making sure it is in the view of the end-user and the script. This will make it possible to click on it.
        Actions actions = new Actions(driver);
        actions.moveToElement(ageOfConsent);
        actions.perform();

        // Wait for the element to be clickable.
        elementToBeClickable(driver, By.cssSelector("div.row:nth-child(12) > div:nth-child(1) >" +
                "div:nth-child(2) > div:nth-child(2) > label:nth-child(3) > span:nth-child(3)"));

        ageOfConsent.click();
    }

    public void codeOfEthics()
    {
        // Find the "Age of Consent" check box element.
        WebElement codeOfEthics = driver.findElement(By.cssSelector("div.md-checkbox:nth-child(7) > label:nth-child(3) > span:nth-child(3)"));

        // User Actions, this will focus on the element (terms & conditions checkbox) by scrolling down to it,
        // making sure it is in the view of the end-user and the script. This will make it possible to click on it.
        Actions actions = new Actions(driver);
        actions.moveToElement(codeOfEthics);
        actions.perform();

        // Wait for the element to be clickable.
        elementToBeClickable(driver, By.cssSelector("div.md-checkbox:nth-child(7) > label:nth-child(3) > span:nth-child(3)"));

        codeOfEthics.click();
    }

    public void submitForm()
    {
        // Find the submit button "Confirm and join".
        WebElement submitForm = driver.findElement(By.cssSelector("input[name='join']"));

        // User Actions, this will focus on the element (terms & conditions checkbox) by scrolling down to it,
        // making sure it is in the view of the end-user and the script. This will make it possible to click on it.
        Actions actions = new Actions(driver);
        actions.moveToElement(submitForm);
        actions.perform();

        submitForm.submit();
    }

    public boolean lastNameMissing()
    {
        // Set false on variable for last name missing.
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
        // Set false on variable for equal password invalid.
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
        // Set false on variable if the terms and conditions box have been checked.
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

    public String accountSuccessful()
    {
        String successful;

        // Find congratulations header text
        WebElement memberNumber = driver.findElement(By.cssSelector("body > div > div.page-content-wrapper > div > div > h5"));
        successful = memberNumber.getText();

        if (successful != null)
        {
            return successful;
        }
        else
        {
            return successful = "Error! Something went wrong or the header text has changed.\n";
        }
    }

    /*
    **************************************************************************************************************
    ********                                        WAIT FUNCTIONS                                        ********
    **************************************************************************************************************
    */
    public void presenceOfElementLocated(WebDriver driver, By by)
    {
        (new WebDriverWait(driver, Duration.ofSeconds(10))).
                until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void elementToBeClickable(WebDriver driver, By by)
    {
        (new WebDriverWait(driver, Duration.ofSeconds(10))).
                until(ExpectedConditions.elementToBeClickable(by));
    }

    public void elementSelectionStateToBe(By by, boolean selected)
    {
        (new WebDriverWait(driver, Duration.ofSeconds(10))).
                until(ExpectedConditions.elementSelectionStateToBe(by, selected));
    }

    /*
     **************************************************************************************************************
     ********                                       GENERAL FUNCTIONS                                      ********
     **************************************************************************************************************
     */

    public static String emailIncrement(String email)
    {
        // Divide the email into 2 parts, before the @ symbol and after the @ symbol.
        String[] divideEmail = email.split("@");
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
