Feature: SVTPlay-MainPageTesting

  Scenario: SVT Play title and main menu links should have correct text
    Given SVT Play is available
    When User visits SVT Play
    Then The title should be "SVT Play"
    And  The main menu link texts should be "START", "PROGRAM", "KANALER"

  Scenario: The logo and main content should be visible
    Then The logo should be visible
    And  The main content should be visible

  Scenario: The contact URL should be correct
    Then Contact URL should be "https://kontakt.svt.see/"

  Scenario: It should go to navigate to main page Popular category
    When We navigate to Popular category
    Then Header text should be "Populärt"

  Scenario: The search box should work as expected
    Given User is on main page
    When When we search for the show Rederiet
    Then "Rederiet" should appear in our search result