Feature: Account creation

  Background: Open the website first, only "Firefox", "Chrome" or "Edge" are supported.
    Given that my web browser is "Firefox"
    And website opens successfully

  Scenario Outline: Creating several accounts using scenario outline
    Given the date of birth element present
    And I input the date of birth via the dropdown
    And enter the first name "<First Name>"
    And enter the last name "<Last Name>"
    And enter the email "<Email>"
    And confirm the email "<Confirm Email>"
    And enter password "<Password>"
    And confirm password "<Repeat Password>"
    And check the Terms and Conditions box
    And check the Age of Consent box
    And check the Code of Ethics box
    When I submit the form
    Then I will be redirected to a confirmation page

    Examples:
      | First Name | Last Name | Email                        | Confirm Email                | Password     | Repeat Password |
      | Phil       | Stevenson | phil.stevenson_test@test.com | phil.stevenson_test@test.com | password123! | password123!    |
      | Margret    | Thatcher  | mt_test@test.com             | mt_test@test.com             | ste!5$@fF    | ste!5$@fF       |
      | Cucumber   | Selenium  | cu_se_test@test.com          | cu_se_test@test.com          | APassword1!  | APassword1!     |

  Scenario: Last name is missing when trying to create an account
    Given the date of birth element present
    And I input the date of birth via the dropdown
    And enter the first name "TestFirstName"
    And enter the email "email@email.com"
    And confirm the email "email@email.com"
    And enter password "password!"
    And confirm password "password!"
    And check the Terms and Conditions box
    And check the Age of Consent box
    And check the Code of Ethics box
    When I submit the form
    Then I will receive an error message that the last name is missing

  Scenario: Password do not match
    Given the date of birth element present
    And I input the date of birth via the dropdown
    And enter the first name "TestFirstName"
    And enter the last name "TestLastName"
    And enter the email "email@email.com"
    And confirm the email "email@email.com"
    And enter password "password!"
    And confirm password "password"
    And check the Terms and Conditions box
    And check the Age of Consent box
    And check the Code of Ethics box
    When I submit the form
    Then I will receive an error message that the passwords do not match

  Scenario: Terms and Conditions box is not checked
    Given the date of birth element present
    And I input the date of birth via the dropdown
    And enter the first name "TestFirstName"
    And enter the last name "TestLastName"
    And enter the email "email17@email.com"
    And confirm the email "email17@email.com"
    And enter password "password!"
    And confirm password "password!"
    And check the Age of Consent box
    And check the Code of Ethics box
    When I submit the form
    Then I will receive an error message that I haven't confirmed the terms
