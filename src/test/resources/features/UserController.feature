Feature: User Resource Test

Scenario: create a user
    Given the web context is set
    When client request POST /user with json data:
    """
    {"userName":"appi.bh@gmail.com","fName":"Appi","lName":"Bhimavarapu","mName":null,"password":"Appi123#","dob":null,"phoneNumber":"1234567890","dob":"09-19-1984"}
    """
    Then the response code should be 201