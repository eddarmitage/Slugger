Feature: Words should be joined together correctly

  By default words are joined using hyphens ('-'), but alternative characters
  can be used (such as underscores) if desired.

  Scenario: By default, join words with a hyphen
    When the input string "hello world" is sluggified
    Then the output will not contain any ' ' characters
    And the output will contain a '-' character

  Scenario: Join words using an alternative character
    Given a separator of "_"
    When the input string "hello world" is sluggified
    Then the output will not contain any ' ' characters
    And the output will contain a '_' character