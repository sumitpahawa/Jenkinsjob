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
  Scenario: Verify user can add more items of the same vehicle to collection
    Given No cars should be added to the Vehicles tab
    When I tap Search from the Navigation Menu at the bottom
    When I input the "96 HONDA ACCORD" into the search textbox,
    Then I should see the "96 HONDA ACCORD" car on the screen.
    When I add cars with '96 HONDA ACCORD'.
    When I navigate to Vehicles tab
    Then I should see the "96 HONDA ACCORD" in the Vehicles tab.
    When I tap Search from the Navigation Menu at the bottom
    When I input the "HONDA S2000" into the search textbox,
    Then I should see the "HONDA S2000" car on the screen.
    When I add cars with "HONDA S2000".
    When I navigate to Vehicles tab
    Then I should see the "HONDA S2000" in the Vehicles tab.

  Scenario: Data Sync after re-launching the App - Add car to Collection
    Given No cars should be added to the Vehicles tab
    When I tap Search from the Navigation Menu at the bottom
    When I input the "96 HONDA ACCORD" into the search textbox,
    Then I should see the "96 HONDA ACCORD" car on the screen.
    When I add cars with '96 HONDA ACCORD'.
    When I navigate to Vehicles tab
    Then I should see the "96 HONDA ACCORD" in the Vehicles tab.
    When I tap Search from the Navigation Menu at the bottom
    When I input the "HONDA S2000" into the search textbox,
    Then I should see the "HONDA S2000" car on the screen.
    When I add cars with "HONDA S2000".
    When I navigate to Vehicles tab
    Then I should see the "HONDA S2000" in the Vehicles tab.
    And I close the App
    And I re-launch the App
    Then I should see the "HONDA S2000" in the Vehicles tab.
    Then I should see the "96 HONDA ACCORD" in the Vehicles tab.

  Scenario: Verify vehicle card display
    Given No cars should be added to the Vehicles tab
    When I tap Search from the Navigation Menu at the bottom
    When I input the "96 HONDA ACCORD" into the search textbox,
    Then I should see the "96 HONDA ACCORD" car on the screen.
    When I add cars with '96 HONDA ACCORD'.
    When I input the "HONDA S2000" into the search textbox,
    Then I should see the "HONDA S2000" car on the screen.
    When I add cars with "HONDA S2000".
    When I navigate to Vehicles tab
    Then I should see the "HONDA S2000" car on the screen.
    Then I should see the "96 HONDA ACCORD" in the Vehicles tab.
    Then I should see the Remove icon for "HONDA S2000"
    Then I should see the Series Name "Mattel Creations" for "HONDA S2000"
    Then I should see the Year "2023" for "HONDA S2000"
    Then I should see the Remove icon for "96 HONDA ACCORD"
    Then I should see the Series Name "Car Culture" for "96 HONDA ACCORD"
    Then I should see the Year "2024" for "96 HONDA ACCORD"

  Scenario:[19] Verify user can navigate to Vehicle Detail Screen through Vehicle Card
    Given No cars should be added to the Vehicles tab
    When I tap Search from the Navigation Menu at the bottom
    When I input the "96 HONDA ACCORD" into the search textbox,
    Then I should see the "96 HONDA ACCORD" car on the screen.
    When I add cars with '96 HONDA ACCORD'.
    When I navigate to Vehicles tab
    Then I should see the "96 HONDA ACCORD" in the Vehicles tab.
    When I tap "96 HONDA ACCORD" vehicle card
    Then Then I should be redirected to the Vehicle's Details Screen with "96 HONDA ACCORD"


Scenario: [14] Verify tap on vehicle card redirected to the Vehicle's Details Screen
  Given No cars should be added to the Vehicles tab
  When I tap Search from the Navigation Menu at the bottom
  When I input the "96 HONDA ACCORD" into the search textbox,
  Then I should see the "96 HONDA ACCORD" car on the screen.
  When I add cars with '96 HONDA ACCORD'.
  When I navigate to Vehicles tab
  Then I should see the "96 HONDA ACCORD" in the Vehicles tab.
  When I tap "96 HONDA ACCORD" vehicle card
  Then Then I should be redirected to the Vehicle's Details Screen with "96 HONDA ACCORD"

  Scenario: [18] Verify user can remove vehicle from garage
    Given No cars should be added to the Vehicles tab
    When I tap Search from the Navigation Menu at the bottom
    When I input the "96 HONDA ACCORD" into the search textbox,
    Then I should see the "96 HONDA ACCORD" car on the screen.
    When I add cars with '96 HONDA ACCORD'.
    When I navigate to Vehicles tab
    Then I should see the "96 HONDA ACCORD" in the Vehicles tab.
    When I tap in the minus icon of one vehicle card
    And I should see the number of vehicles in my library decreased by 1, indicating that the selected vehicle has been successfully removed

Scenario: [21] Verify series card display
    When I tap Search from the Navigation Menu at the bottom
    When I click in the All Series tab
    Then I see a list of Series Cards
    Then I should see the Series card displays with image


  Scenario: Verify user can add vehicle to collection via Wishlist screen - vehicle not in collection.
    Given No cars should be added to the Vehicles tab
    Given Vehicle are already in Wishlist
    When I'm in the Wishlist tab
    And I see a list of Vehicles Cards
    When I tap in the plus icon of one vehicle car
    Then I should see the "-" options instead of + button.

  Scenario:Verify user can add vehicle to collection via Wishlist Screen - vehicle already in collection
    Given No cars should be added to the Vehicles tab
    Given Vehicle are already in Wishlist
    When I'm in the Wishlist tab
    And I see a list of Vehicles Cards
    When I tap in the plus icon of one vehicle car
    When I navigate to Vehicles tab
    Then I should see the 1 car is displayed correctly in My Collection

    Scenario:[23] Verify user can navigate to Series Detail Screen through Series Card
      Given No cars should be added to the Vehicles tab
      When I tap Search from the Navigation Menu at the bottom
      When I input the "96 HONDA ACCORD" into the search textbox,
      Then I should see the "96 HONDA ACCORD" car on the screen.
      When I add cars with '96 HONDA ACCORD'.
      When I'm in the Series tab
      When I tap on "TEAM TRANSPORT" series card
      Then I should see in my screen the "TEAM TRANSPORT" Series Detail corresponding to the selected series card

  Scenario:[27]Verify user can add vehicle to garage via Wishlist screen - vehicle not in garage
    Given No cars should be added to the Vehicles tab
    Given Vehicle are already in Wishlist
    When I'm in the Wishlist tab
    And I see a list of Vehicles Cards
    When I tap in the plus icon of one vehicle car
    Then I should see the "-" options instead of + button.
    When I navigate to Vehicles tab
    Then I should see the 1 car is displayed correctly in My Collection

Scenario: [29] Verify user can remove vehicle from garage via Wishlist Screen - one item in garage
  Given No cars should be added to the Vehicles tab
  Given Vehicle are already in Wishlist
  When I'm in the Wishlist tab
  And I see a list of Vehicles Cards
  When I tap in the plus icon of one vehicle car
  Then I should see the "-" options instead of + button.
  When I tap in the minus icon of one vehicle car
  Then I should see the "+" options instead of + button.

  Scenario: [33] Verify Sorting options are displayed in Vehicles View
    Given No cars should be added to the Vehicles tab
    When I tap Search from the Navigation Menu at the bottom
    When I input the "AUDI R8 LMS" into the search textbox,
    Then I should see the "AUDI R8 LMS" car on the screen.
    When I add cars with 'AUDI R8 LMS'.
    When I input the "80 DODGE MACHO" into the search textbox,
    Then I should see the "80 DODGE MACHO" car on the screen.
    When I add cars with "80 DODGE MACHO".
    When I input the "FORD GT-40" into the search textbox,
    Then I should see the "FORD GT-40" car on the screen.
    When I add cars with "FORD GT-40".
    When I navigate to Vehicles tab
    Then I see the filter icon
    And I see the "Release Date" sorting option
    And I see the "Year" sorting option
    And I see the "Category" sorting option
    And I see the "Scale" sorting option
    And I see the "Product Designer" sorting option
    And I see the "Graphic Designer" sorting option
    And I see the "Body Color" sorting option
    And I see the "Wheel" sorting option
    And I see the "ManUFcturing Plant" sorting option


  Scenario: [33] Verify Sorting options are displayed in Wishlist View
    Given No cars should be added to the Vehicles tab
    When I tap Search from the Navigation Menu at the bottom
    When I input the "AUDI R8 LMS" into the search textbox,
    Then I should see the "AUDI R8 LMS" car on the screen.
    When I tap 'AUDI R8 LMS' vehicle card
    When I tap on Wishlist icon.
    When I tap on back icon on detail page.
    When I tap on back icon.
    When I input the "80 DODGE MACHO" into the search textbox,
    Then I should see the "80 DODGE MACHO" car on the screen.
    When I tap '80 DODGE MACHO' vehicle card
    When I tap on Wishlist icon.
    When I tap on back icon on detail page.
    When I tap on back icon.
    When I input the "FORD GT-40" into the search textbox,
    Then I should see the "FORD GT-40" car on the screen.
    When I tap 'FORD GT-40' vehicle card
    When I tap on Wishlist icon.
    When I tap on back icon on detail page.
    When I tap on back icon.
    When I'm in the Wishlist tab
    Then I see the filter icon
    And I see the "Recently" sorting option
    And I see the "Year" sorting option
    And I see the "Category" sorting option
    And I see the "Scale" sorting option
    And I see the "Product Designer" sorting option
    And I see the "Graphic Designer" sorting option
    And I see the "Body Color" sorting option
    And I see the "Wheel" sorting option
    And I see the "ManUFcturing Plant" sorting option
