Feature: SVTPlay-ProgramPageTesting

  Scenario: The Program page should contain 17 categories
    Given  SVT Play is available
    When Navigating to the Program page
    Then 17 categories should be listed

  Scenario: The show all categories button should be visible
    Then The show all categories button should be visible