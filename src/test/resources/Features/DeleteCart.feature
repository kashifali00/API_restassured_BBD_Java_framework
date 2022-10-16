
@DeleteItem
Feature: View Cart Functionality
  Scenario: Verify that user is able to view cart
    Given User has already item in the cart
    When  User hit endpoint deletecart
    Then  Response should be 405