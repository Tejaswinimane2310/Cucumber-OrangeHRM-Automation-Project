#Author : Tejaswini Mane
#Date : 2-June-2023

Feature: Login OrangeHRM Website

@smoke
Scenario: Successful login on homepage
    Given user launches the browser
    And opens the url "https://tejaswinimane-trials79.orangehrmlive.com/auth/login"
    When user enters the username as "<username>" and password as "<password>"
    And clicks on the login button
    Then the user should see the homepage of OrangeHRM

    Examples: 
    | username    | password     |
    | Admin       | L0tLjv3@TJ   |
    | pappujadhav | Pappu@123    |
    | Admin       | hjasgdhasgf  |
    | Admin1      | L0tLjv3@TJ   |
    | pappu       | Teju@123     |
   