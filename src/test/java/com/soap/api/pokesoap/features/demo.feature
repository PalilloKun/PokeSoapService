Feature: Lets use DT

  Scenario: Get Pokemon Ability
    Given the Pokemon name is "bulbasaur"
    When I request the ability of the Pokemon
    Then the response should contain the ability "overgrow"

  Scenario: Get Pokemon Base Experience
    Given the Pokemon name is "bulbasaur"
    When I request the base experience of the Pokemon
    Then the response should contain the base experience "64"

  Scenario: Get Pokemon Held Items
    Given the Pokemon name is "bulbasaur"
    When I request the held items of the Pokemon
    Then the response should contain the held item "some item"

  Scenario: Get Pokemon ID
    Given the Pokemon name is "bulbasaur"
    When I request the ID of the Pokemon
    Then the response should contain the ID "1"

  Scenario: Get Pokemon Name
    Given the Pokemon name is "bulbasaur"
    When I request the name of the Pokemon
    Then the response should contain the name "bulbasaur"

  Scenario: Get Pokemon Location Area Encounters
    Given the Pokemon name is "bulbasaur"
    When I request the location area encounters of the Pokemon
    Then the response should contain the location area encounters "Pallet Town"