@AddItem1
Feature: View Cart Functionality
  Scenario: Verify that user is able to view cart
    Given User is authorized
    When  User hit endpoint additem
    Then  Response should be OK and item should be added successfully