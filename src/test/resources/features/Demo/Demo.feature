Feature: Demo for POC

  Background:
    Given I navigate to Guru page
    And User should be navigate to Guru page

  Scenario: TC1 Verify user navigate to Guru88
    Then Homepage should be display as design

  Scenario: TC2 Verify error message when user leave blank/ Invalid Email
    When I leave Email field is blank
    Then Alert message "Email ID must not be blank" should be display
    When I input "ádasdasdasda" email
    When I input "123456789" email
    Then Alert message "Email ID is not valid" should be display

  Scenario: TC3 Verify failed test case
    When I input "ádasdasdasda" email
    Then Alert message "Email ID must not be blank" should be display

  Scenario: TC4 Verify Selenium dropdown items
    When I open Selenium dropdown
    Then I should see these Selenium dropdown items:
      | Flash Movie Demo                 |
      | Radio & Checkbox Demo            |
      | Table Demo                       |
      | Accessing Link                   |
      | Ajax Demo                        |
      | Inside & Outside Block Level Tag |
      | Delete Customer Form             |
      | Yahoo                            |
      | Tooltip                          |
      | File Upload                      |
      | Login                            |
      | Social Icon                      |
      | Selenium Auto IT                 |
      | Selenium IDE Test                |
      | Guru99 Demo Page                 |
      | Scrollbar Demo                   |
      | File Upload using Sikuli Demo    |
      | Cookie Handling Demo             |
      | Drag and Drop Action             |
      | Selenium DatePicker Demo         |

  Scenario Outline: TC5 Verify top navigation menu redirects correctly
    When I click on "<menuName>" menu
    Then I should be navigated to "<expectedPageURL>" page

    Examples:
      | menuName                | expectedPageURL |
      | Insurance Project       | insurance       |
      | Agile Project           | Agile_Project   |
      | Bank Project            | V1              |
      | Security Project        | Security/SEC_V1 |
      | Telecom Project         | telecom         |
      | Payment Gateway Project | payment-gateway |
      | New Tours               | newtours        |

#    Viết thêm function để switch tab
  Scenario: Check new tab
    When i open new tab