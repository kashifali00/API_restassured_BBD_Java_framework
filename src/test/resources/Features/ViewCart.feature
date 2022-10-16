
@ViewItem1
Feature: View Cart Functionality
  Scenario: Verify that user is able to view cart
    Given User is on demoblaze
    When  User hit endpoint viewcart
    Then  Response status should be 201