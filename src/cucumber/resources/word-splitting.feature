Feature: Slugs should be split into words correctly

  Slugs consist of a sequence of joined words, meaning it's important that the
  individual words in the input String are correctly identified. Config allows
  for words to be split in a number of ways - by default it will be by
  whitespace only, but words can be identified by camel-casing, for example, if
  required

  Scenario: Words should be split by whitespace
    When the input string "This is a title" is sluggified
    Then the output will not contain any ' ' characters
    And the output will contain 4 words

  Scenario: Words are not split by camel-case by default
    When the input string "The importance of toString()" is sluggified
    Then the output will not contain any ' ' characters
    And the output will contain 4 words

  Scenario: Words can be split by camel-case
    Given camel case splitting is enabled
    When the input string "The importance of toString()" is sluggified
    Then the output will not contain any ' ' characters
    And the output will contain 5 words

  Scenario: Words can be split by underscores
    Given underscore splitting is enabled
    When the input string "Why I love snake_case conventions" is sluggified
    Then the output will not contain any ' ' characters
    And the output will contain 6 words

  Scenario: Word-splitters can be combined
    Given camel case splitting is enabled
    And underscore splitting is enabled
    When the input string "Why I prefer camelCase to snake_case" is sluggified
    Then the output will not contain any ' ' characters
    And the output will contain 8 words