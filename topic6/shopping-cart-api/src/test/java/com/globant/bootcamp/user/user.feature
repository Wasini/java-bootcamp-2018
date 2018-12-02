Feature: Quote generator

  Background:
    * url BaseUrl + '/api/users'

  Scenario: create and retrieve a user

# create a new user
    Given path 'new'
    And request { name: 'Billie', password:'Boy' }
    When method post
    Then status 201
    And match response == { id: '#number', name: 'Billie' , password:'Boy'}

    * def id = response.id

# created user in on the users list
    Given method get
    Then status 200
    And match response[*].id contains id

# delete user
    Given  path id
    When method delete
    Then status 200


# user is removed  of the user list
    Given method get
    Then status 200
    And match response[*].id !contains id