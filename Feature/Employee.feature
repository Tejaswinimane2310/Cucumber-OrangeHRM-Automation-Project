#Author : Tejaswini Mane
#Date : 2-June-2023

Feature: Add Employee on OrangeHRM Website

@test
Scenario: Successful adding the Employee
    Given user launches the browser for orangeHRM
    And opens the orange HRM url "https://tejaswinimane-trials79.orangehrmlive.com/auth/login"
    When user login with the username as "Admin" and password as "L0tLjv3@TJ"
    And clicks on the add employee button
    And user enters firstname as "<firstname>" , lastname as "<lastname>" and gender as "<gender>"
    Then the user should see the added employee of OrangeHRM
    
     Examples: 
    | firstname    | lastname     | gender  |
    | Tejaswini    | Mane         | Female  |