Feature: Register a property
  As a landlord
  I want to register my property
  To be able to promote it

  Scenario: The landlord has registered his property successfully
    Given a landlord in the register property's view
    And the information entered is correct
    When the landlord clicks the register button
    And make a post request to "/landlords/7/properties"
    Then the system promotes the ad of the property
    And the result received has a status code of 200

  Scenario: The landlord has not registered his property
    Given a landlord in the register property's view
    And the information entered is incorrect
    When the landlord clicks the register button
    And make a post request to "/landlords/1/properties"
    Then the system asks to correct the wrong data
    And the result received has a status code of 500