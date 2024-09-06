Feature: Sim Card Activation
  Scenario: Succesful Sim Activation
    Given a valid iccid
    When request sim activation
    Then the activation is succesful and recorded to database
  Scenario: Failed Sim Activation
    Given an invalid iccid
    When request sim activation
    Then the activation fails and recorded to database