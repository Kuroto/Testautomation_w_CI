package StepDefinitions;

import Common.AccountCreation;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.interactions.Actions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Basketall_Stepdefs
{
    // Create the connection to the constructor.
    // This will be used in all the coming cases to minimize the code here as much as possible.
    // All the code will be on the constructor class side.
    AccountCreation accountCreation = new AccountCreation();

    @Before
    public void setUp()
    {
        // Initate the WebDriver.
        accountCreation.initiateWebDriver();
    }

    @Given("website opens successfully")
    public void testOpenWebsite()
    {
        accountCreation.openWebsite();
        System.out.println("Website opened successfully!");
    }

    @Given("the date of birth element present")
    public void testDateOfBirthElementPresent()
    {
        accountCreation.dateOfBirthElementPresent();
        System.out.println("Verifying that the \"Date of birth\" element is present.");
    }

    @And("I input the date of birth via the dropdown")
    public void testDateOfBirthDropdown()
    {
        accountCreation.dateOfBirthDropdown();
        System.out.println("Entering date of birth via dropdown menu.");
    }

    @And("enter the first name {string}")
    public void testEnterTheFirstName(String firstname)
    {
        String firstnameResult = accountCreation.enterTheFirstName(firstname);
        System.out.println("Entering first name: " + firstnameResult);
    }

    @And("enter the last name {string}")
    public void testEnterTheLastName(String lastname)
    {
        String lastnameResult = accountCreation.enterTheLastName(lastname);
        System.out.println("Entering last name: " + lastnameResult);
    }

    @And("enter the email {string}")
    public void testEnterTheEmail(String email)
    {
        String emailResult = accountCreation.enterTheEmail(email);
        System.out.println("Entering email: " + emailResult);
    }

    @And("confirm the email {string}")
    public void testConfirmTheEmail(String confirmEmail)
    {
        String confirmEmailResult = accountCreation.confirmTheEmail(confirmEmail);
        System.out.println("Email confirmation: " + confirmEmailResult);
    }

    @And("enter password {string}")
    public void testEnterPassword(String password)
    {
        String passwordResult = accountCreation.enterPassword(password);
        System.out.println("Entering password: " + passwordResult);
    }

    @And("confirm password {string}")
    public void testConfirmPassword(String confirmPassword)
    {
        String confirmPasswordResult = accountCreation.confirmPassword(confirmPassword);
        System.out.println("Password confirmation: " + confirmPasswordResult);
    }

    @And("check the Terms and Conditions box")
    public void testCheckTheTermsAndConditionsBox()
    {
        accountCreation.checkTheTermsAndConditionsBox();
        System.out.println("Terms and Conditions box checked.");
    }

    @And("check the Age of Consent box")
    public void testCheckTheAgeOfConsentBox()
    {
        accountCreation.checkTheAgeOfConsentBox();
        System.out.println("Age of Consent box checked.");
    }

    @And("check the Code of Ethics box")
    public void testCheckTheCodeOfEthicsBox()
    {
        accountCreation.codeOfEthics();
        System.out.println("Code of Ethics box checked.");
    }

    @When("I submit the form")
    public void testSubmitForm()
    {
        accountCreation.submitForm();
        System.out.println("Pressed the submit button on the form.");
    }

    @Then("I will be redirected to a confirmation page")
    public void testRedirectToConfirmation()
    {
        // The expected value should always be true. Meaning the account was successfully created and user redirected.
        String expected = "Your Basketball England Membership Number is:";

        // Get the result from the account creation process.
        //boolean actual = false;
        String actual = accountCreation.accountSuccessful();

        // Compare the expected result with the actual result.
        assertEquals(expected, actual);
        System.out.println("Congratulations! You've successfully created an account! \n");
    }

    @Then("I will receive an error message that the last name is missing")
    public void testLastNameIsMissing()
    {
        boolean expected = true;
        boolean actual = accountCreation.lastNameMissing();

        assertEquals(expected, actual);
        System.out.println("Error! Last name is missing!");
    }

    @Then("I will receive an error message that the emails do not match")
    public void testEmailsDoNotMatch()
    {
        boolean expected = true;
        boolean actual = accountCreation.passwordMismatch();

        assertEquals(expected, actual);
        System.out.println("Error! Passwords do not match!");
    }

    @Then("I will receive an error message that I haven't confirmed the terms")
    public void testTermsAndConditionsNotChecked()
    {
        boolean expected = true;
        boolean actual = accountCreation.termsNotChecked();

        assertEquals(expected, actual);
        System.out.println("Error! Terms and Conditions have not been checked!");
    }

    @After
    public void testCloseBrowser()
    {
        //accountCreation.closeWebDriver();
        //System.out.println("Closed the website.");
    }
}
