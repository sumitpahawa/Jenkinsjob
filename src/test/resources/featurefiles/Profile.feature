Feature: Profile


Scenario: Verify redirection to settings screen
  When I tap Profile from the Navigation Menu at the bottom
  When I tap Settings Icon
  Then I should see the Settings Screen


Scenario: Verify Profile Screen information display
  Given No cars should be added to the Vehicles tab
  When I tap Profile from the Navigation Menu at the bottom
  Then I should go to User Profile page
  And  I see the Settings Icon
  And  I see the Profile Image
  And  I see the Username
  And  I see the No. of Vehicles Collected
  And  I see the No. of Series Collected
  And  I see the Vehicles Collected
  And  I see the Series Collected