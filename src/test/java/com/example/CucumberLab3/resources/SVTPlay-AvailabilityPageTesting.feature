Feature: SVTPlay-AvailabilityPageTesting

  Scenario: Availability link should be visible and text should be correct
    Given SVT Play main page is available
    When User scrolls all the way down
    Then The availability link should be visible
    And The link text should be "Tillgänglighet i SVT Play"

  Scenario: Availability page main header text should be correct
    Given Availability page is available
    When User visits availability page
    Then The main header text should be "Så arbetar SVT med tillgänglighet"