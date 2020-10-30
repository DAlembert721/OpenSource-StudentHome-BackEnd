Feature: Opinion about a student
  As a landlord
  I want to opinion about a student
  To be able to share my experience while he was my tenant

  Scenario: The student has commented about a available property
    Given a landlord in the student profile view
    And the student profile is available
    When the landlord clicks the accept button
    And make a post request to the direction: "/landlords/2/students/5/opinions"
    Then system response the request with the code 200

  Scenario: The student has commented about a unavailable property
    Given a student in the property view
    And he student profile is unavailable
    When the landlord clicks the accept button
    And make a post request to the direction: "landlords/2/students/5/opinions"
    Then system response the request with the code 400