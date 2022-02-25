Feature: Check CourseInfo for a particular course
  Scenario: User will query API for particular course id
    Given User have access to api at "https://app.pragra.io"
    When User call the path "/api/course-info/{id}" with course id "6116c308df858e6d5ed1507b"
    Then System should return response with status 200
    And course name should be "FullStack - Java"
    And number of instructor should be 3
