Feature: Remove characters in slugs, replacing them where appropriate
  # Enter feature description here

  Scenario: Special characters should be removed from inputs
    When the input string "test input $%.@€/]{" is sluggified
    Then the output will contain a '-' character

  Scenario: Case can be preserved in the output
    Given case will be preserved
    When the input string "Hello World" is sluggified
    Then the output will contain a 'H' character
    And the output will contain a 'W' character

  Scenario: Specific characters can be replaced with substitute strings
    Given a replacement from 'u' to "oo"
    When the input string "gu" is sluggified
    Then the output will be "goo"

  Scenario: Accent is removed 
    When the input string "exposé" is sluggified
    Then the output will be "expose"