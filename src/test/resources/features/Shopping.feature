Feature: SauceDemo Shopping Cart
  As a customer
  I want to login and add products to cart
  So that I can purchase items

  Background:
    Given I navigate to the SauceDemo website

  Scenario: Successfully login and add t-shirt to cart
    When I login with username "standard_user" and password "secret_sauce"
    Then I should see the Products page
    When I add "Sauce Labs Bolt T-Shirt" to the cart
    Then the cart should show 1 item

  Scenario: Verify multiple products are displayed after login
    When I login with username "standard_user" and password "secret_sauce"
    Then I should see the Products page
    And I should see 6 products displayed

  Scenario: Login with invalid credentials
    When I login with username "invalid_user" and password "wrong_password"
    Then I should see an error message

  Scenario: Successfully checkout with multiple items
    When I login with username "standard_user" and password "secret_sauce"
    And I add "Sauce Labs Backpack" to the cart
    And I add "Sauce Labs Bike Light" to the cart
    Then the cart should show 2 item
    When I proceed to checkout
    And I enter shipping information "John", "Doe", "12345"
    And I finish the checkout
    Then I should see the order confirmation message "Thank you for your order!"
