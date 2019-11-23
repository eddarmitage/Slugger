Feature: Ensure slugs are the correct length

  If no target length is supplied, the length of a slug is not restricted in
  any way. If it is provided, then the slug will not be longer than the target
  length so long as it contains at least one full word (as defined by the
  word-splitter). If hard-limits are enforced, the slug will be truncated at
  the end of the last full word OR at the target limit if the slug contains
  only one word.

  Scenario: By default do not cut the length of input
    When the input string "This is a very long title for a post" is sluggified
    Then the output will be 36 characters long

  Scenario: Cut whole words from a title
    Given a target length of 25 characters
    When the input string "This is a very long title for a post" is sluggified
    Then the output will be no longer than 25 characters long

  Scenario: Don't truncate single words
    Given a target length of 25 characters
    When the input string "Supercalifragilisticexpialidocious" is sluggified
    Then the output will be longer than 25 characters long

  Scenario: Truncate single words if a hard limit is enforced
    Given a target length of 25 characters
    And hard limits are enforced
    When the input string "Supercalifragilisticexpialidocious" is sluggified
    Then the output will be 25 characters long