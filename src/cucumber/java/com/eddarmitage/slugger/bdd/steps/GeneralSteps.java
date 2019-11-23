package com.eddarmitage.slugger.bdd.steps;

import com.eddarmitage.slugger.bdd.SluggerHelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class GeneralSteps {

    private final SluggerHelper sluggerHelper;

    public GeneralSteps(SluggerHelper sluggerHelper) {
        this.sluggerHelper = sluggerHelper;
    }

    @Given("case will be preserved")
    public void case_will_be_preserved() {
        sluggerHelper.set(sluggerHelper.get().withCasePreserved());
    }

    @Given("a replacement from {character} to {string}")
    public void a_replacement(Character character, String replacement) {
        sluggerHelper.set(sluggerHelper.get().withReplacement(character, replacement));
    }

    @When("the input string {string} is sluggified")
    public void sluggifyString(String input) {
        sluggerHelper.applyInput(input);
    }

    @Then("the output will not contain any {character} characters")
    public void checkOutputHasNoSpaces(Character character) {
        assertThat(sluggerHelper.getOutput()).doesNotContain(character.toString());
    }

    @Then("the output will contain {quantity} {character} character(s)")
    public void checkOutputContainsCharacter(Integer quantity, Character c) {
        int actual = sluggerHelper.getOutput().split(c.toString(), -1).length - 1;
        assertThat(actual)
                .as("Check \"%s\" contains exactly %d occurrences of '%c'", sluggerHelper.getOutput(), quantity, c)
                .isEqualTo(quantity);
    }
}
