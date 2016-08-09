Feature: User Service Integration Test

Scenario: create a user
    When client passes user object to service
    Then the user object should be created with id set and returned to the service