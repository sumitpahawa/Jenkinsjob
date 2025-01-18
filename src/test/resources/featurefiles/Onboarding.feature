Feature: Onboarding
   Scenario: Verify Guest user able to view onboarding screen
     Given  I have installed and launch the speedway app
     Then  I should see the onboarding screen

   Scenario:[8] Verify Get Started button redirectto sign up screen
     When I press the Get Started button
     Then I should see the Sign In Screen

   Scenario Outline:[11] Verify user is not able to sign in to the app - invalid credentials
     When I press the Get Started button
     When I type my credentials "<username>" and "<password>"
     And I press the Continue button
     Then I should see an error message indicating my credentials are invalid
     Examples:
       | username                        | password      |
       | s.pahawass@gmail.com            | Shilepi@102   |
       | autokrasasssmotmattel@gmail.com | Krasamo1234   |

   Scenario Outline:[9] Verify user is able to sign in to the app - valid credentials
     When I press the Get Started button
     When I type my credentials "<username>" and "<password>"
     And I press the Continue button
     Then I should be redirected to My Garage Screen
     Examples:
       | username                     | password    |
       | s.pahawa@gmail.com           | Shilpi@10   |





