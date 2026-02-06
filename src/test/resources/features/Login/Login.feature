Feature: Demo for POC

  Scenario: TC1 Verify user navigate to Google
    Given user navigate to Google page
    Then google page should be display
    And i input "abc" text to search box
    And i input "123" text to search box
    And i input "dfsdgdgdfg" text to search box
    And i input "Hi" text to search box
    And i input "Hello" text to search box
    And i input "2" text to search box
    And i input "3" text to search box
    And i input "4" text to search box
#    And i click on submit button