Feature: Collection Screen
    Scenario Outline:[9] Verify user is able to sign in to the app - valid credentials
      When I press the Get Started button
      When I type my credentials "<username>" and "<password>"
      And I press the Continue button
      Then I should be redirected to My Garage Screen
      Examples:
        | username                     | password    |
        | s.pahawa@gmail.com           | Shilpi@10   |
