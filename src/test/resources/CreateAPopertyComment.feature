Feature: Comment a property
  As a student
  I want to comment about a property
  To be able to share my experience while lived there

  Scenario: The student has commented about a available property
    Given a student in the property view
    And the property is available
    When the student clicks the accept button
    And make a post request to the url: "/students/5/properties/2/comments"
    Then the response has a code of 200

  Scenario: The student has commented about a unavailable property
    Given a student in the property view
    And the property is unavailable
    When the student clicks the accept button
    And make a post request to the url: "/students/5/properties/2/comments"
    Then the response has a code of 400