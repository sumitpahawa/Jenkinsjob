Feature: Collection Screen
    Scenario Outline:[9] Verify user is able to sign in to the app - valid credentials
      When I press the Get Started button
      When I type my credentials "<username>" and "<password>"
      And I press the Continue button
      Then I should be redirected to My Garage Screen
      Examples:
        | username                     | password    |
        | s.pahawa@gmail.com           | Shilpi@10   |
 Scenario: [6]Verify empty state in Vehicle Collection Screen in user's first login\
    Given No cars should be added to the Vehicles tab
    When I'm in the Vehicles tab
    Then I should be able to see "Your Garage is empty.".


  Scenario: [7]Verify empty state in Series Collection Screen in user's first login
    Given No cars should be added to the Vehicles tab
    When I'm in the Series tab
    Then I should be able to see "Your Garage is empty.".

  Scenario: [8]Verify empty state in Wishlist Collection Screen in user's first login
    When I'm in the Wishlist tab
    Given No vehicle are present in Wishlist
    Then I should be able to see empty wishlist message "Your Wishlist is empty.".

  Scenario: [9]Verify redirect to Profile screen from My Collection screen
    When I tap Profile from the Navigation Menu at the bottom
    Then I should go to User Profile page

  Scenario: Verify redirect to News screen from My Collection screen
    When I tap News from the Navigation Menu at the bottom
    Then I should go to News page

  Scenario: [10]Verify redirect to Search screen from My Collection screen
    When I tap Search from the Navigation Menu at the bottom
    Then I should go to Search page
  
