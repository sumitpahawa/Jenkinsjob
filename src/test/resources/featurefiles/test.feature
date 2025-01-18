Feature: test
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

  Scenario: [44] Verify user is able to filter vehicles - Series View
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
    When I tab on filter icon
    When I select Release Date (Newest) filter.
    When I tab on show result button
    Then I should see the Year "2024" AUDI LMS on first position
    Then I should see the Year "2023" DODGE MACHO on second position
    Then I should see the Year "2013" FORD GT on third position

  Scenario: [45] Verify user is able to filter vehicles - Wishlisht View
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
    When I tab on filter icon
    When I select Release Date (Oldest) filter
    When I tab on show result button
    Then I should see the Year "2013" FORD GT on first position
    Then I should see the Year "2023" DODGE MACHO on Second position
    Then I should see the Year "2024" AUDI LMS on third position

  Scenario: [46] Verify user is able to filter vehicles - Wishlisht View
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
    When I tab on filter icon
    When I select Recently Collected (Oldest) filter.
    When I tab on show result button
    Then I should see the Year "2024" AUDI LMS on first position
    Then I should see the Year "2023" DODGE MACHO on second position
    Then I should see the Year "2013" FORD GT on third position

  Scenario: [47] Verify user is able to filter vehicles - Wishlisht View
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
    When I tab on filter icon
    When I select Recently Collected (Newest) filter.
    When I tab on show result button
    Then I should see the Year "2013" FORD GT on first position
    Then I should see the Year "2023" DODGE MACHO on Second position
    Then I should see the Year "2024" AUDI LMS on third position

  Scenario: [48] Verify user is able to filter vehicles - Wishlisht View
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
    When I tab on filter icon
    When I select "2023" year filter.
    When I tab on show result button
    Then I should see the Year "2023" DODGE MACHO.
    Then I should not see the Year "2024" AUDI LMS.
    Then I should not see the Year "2013" FORD GT.


  Scenario: [49] Verify user is able to filter vehicles - Wishlisht View
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
    When I tab on filter icon
    When I select "Brendon Vetuskey" product designer filter.
    When I tab on show result button
    Then I should see the "80 DODGE MACHO".
    Then I should not see the "AUDI R8 LMS".
    Then I should not see the "FORD GT-40".


  Scenario: [50] Verify user is able to filter vehicles - Wishlisht View
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
    When I tab on filter icon
    When I select "Brendon Vetuskey" graphic designer filter.
    When I tab on show result button
    Then I should see the "80 DODGE MACHO".
    Then I should not see the "AUDI R8 LMS".
    Then I should not see the "FORD GT-40".


  Scenario: [51] Verify user is able to filter vehicles - Wishlisht View
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
    When I tab on filter icon
    When I select "White, Blue" body color filter.
    When I tab on show result button
    Then I should see the "80 DODGE MACHO".
    Then I should not see the "AUDI R8 LMS".
    Then I should not see the "FORD GT-40".

  Scenario: [52] Verify user is able to filter vehicles - Wishlisht View
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
    When I tab on filter icon
    When I select "2023" year filter.
    When I select "White, Blue" body color filter.
    When I select "Brendon Vetuskey" graphic designer filter.
    When I tab on show result button
    Then I should see the "80 DODGE MACHO".
    Then I should not see the "AUDI R8 LMS".
    Then I should not see the "FORD GT-40".

  Scenario: [53] Verify user is able to filter vehicles - Wishlisht View
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
    When I tab on filter icon
    When I select "2023" year filter.
    When I select "White, Blue" body color filter.
    When I select "Mark Jones" graphic designer filter.
    When I tab on show result button
    Then I should not see the "80 DODGE MACHO".
    Then I should not see the "AUDI R8 LMS".
    Then I should not see the "FORD GT-40".






  Scenario: [34] Verify user is able to filter vehicles  - Series view
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
    When I tab on filter icon
    When I select Release Date (Newest) filter.
    When I tab on show result button
    Then I should see the Year "2024" AUDI LMS on first position
    Then I should see the Year "2023" DODGE MACHO on second position
    Then I should see the Year "2013" FORD GT on third position

  Scenario: [35] Verify user is able to filter vehicles  - Series view
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
    When I'm in the Series tab
    When I tab on filter icon
    When I select Release Date (Oldest) filter
    When I tab on show result button
    Then I should see the Year "2013" FORD GT on first position
    Then I should see the Year "2023" DODGE MACHO on Second position
    Then I should see the Year "2024" AUDI LMS on third position

  Scenario: [36] Verify user is able to filter vehicles  - Series view
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
    When I tab on filter icon
    When I select Recently Collected (Oldest) filter.
    When I tab on show result button
    Then I should see the Year "2024" AUDI LMS on first position
    Then I should see the Year "2023" DODGE MACHO on second position
    Then I should see the Year "2013" FORD GT on third position

  Scenario: [37] Verify user is able to filter vehicles  - Series view
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
    When I tab on filter icon
    When I select Recently Collected (Newest) filter.
    When I tab on show result button
    Then I should see the Year "2013" FORD GT on first position
    Then I should see the Year "2023" DODGE MACHO on Second position
    Then I should see the Year "2024" AUDI LMS on third position

  Scenario: [38] Verify user is able to filter vehicles  - Series view
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
    When I tab on filter icon
    When I select "2023" year filter.
    When I tab on show result button
    Then I should see the Year "2023" DODGE MACHO.
    Then I should not see the Year "2024" AUDI LMS.
    Then I should not see the Year "2013" FORD GT.


  Scenario: [39] Verify user is able to filter vehicles  - Series view
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
    When I tab on filter icon
    When I select "Brendon Vetuskey" product designer filter.
    When I tab on show result button
    Then I should see the "80 DODGE MACHO".
    Then I should not see the "AUDI R8 LMS".
    Then I should not see the "FORD GT-40".


  Scenario: [40] Verify user is able to filter vehicles  - Series view
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
    When I tab on filter icon
    When I select "Brendon Vetuskey" graphic designer filter.
    When I tab on show result button
    Then I should see the "80 DODGE MACHO".
    Then I should not see the "AUDI R8 LMS".
    Then I should not see the "FORD GT-40".


  Scenario: [41] Verify user is able to filter vehicles  - Series view
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
    When I tab on filter icon
    When I select "White, Blue" body color filter.
    When I tab on show result button
    Then I should see the "80 DODGE MACHO".
    Then I should not see the "AUDI R8 LMS".
    Then I should not see the "FORD GT-40".

  Scenario: [42] Verify user is able to filter vehicles  - Series view

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
    When I tab on filter icon
    When I select "2023" year filter.
    When I select "White, Blue" body color filter.
    When I select "Brendon Vetuskey" graphic designer filter.
    When I tab on show result button
    Then I should see the "80 DODGE MACHO".
    Then I should not see the "AUDI R8 LMS".
    Then I should not see the "FORD GT-40".

  Scenario: [43] Verify user is able to filter vehicles  - Series view

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
    When I tab on filter icon
    When I select "2023" year filter.
    When I select "White, Blue" body color filter.
    When I select "Mark Jones" graphic designer filter.
    When I tab on show result button
    Then I should not see the "80 DODGE MACHO".
    Then I should not see the "AUDI R8 LMS".
    Then I should not see the "FORD GT-40".
