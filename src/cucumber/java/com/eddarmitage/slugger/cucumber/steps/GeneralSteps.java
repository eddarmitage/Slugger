package com.eddarmitage.slugger.cucumber.steps;

import com.eddarmitage.slugger.cucumber.SluggerHelper;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class GeneralSteps {

    private final SluggerHelper sluggerHelper;

    public GeneralSteps(SluggerHelper sluggerHelper) {
        this.sluggerHelper = sluggerHelper;
    }

    /**
     * Allows for steps to refer to chars within quotes.
     * <p>
     * For example, a step defined as {@code @Then("the output will not contain any {character} characters")} will
     * allow for steps to be written as {@code Then the output will not contain any 'u' characters} with the single
     * character {@code u} passed to the step method.
     *
     * @see GeneralSteps#checkOutputDoesNotContainCharacter(Character)
     *
     * @param match  the sub-string of the text that matches this expression
     * @return the quoted character
     */
    @ParameterType(name = "character", value = "\'.\'")
    public Character defineCharacter(String match) {
        return match.charAt(1);
    }

    /**
     * Allows for steps to refer to quantities.
     * <p>
     * Examples for step {@code @When("{quantity} (chicken|elephant)(s) crossed the road")}:
     * <ul>
     *     <li>{@code When a chicken crossed the road} gives 1</li>
     *     <li>{@code When an elephant crossed the road} gives 1</li>
     *     <li>{@code When 1 chicken crossed the road} gives 1</li>
     *     <li>{@code When 0 chickens crossed the road} gives 0</li>
     *     <li>{@code When 7 chickens crossed the road} gives 7</li>
     *     <li>{@code When 10 chickens crossed the road} gives 10</li>
     * </ul>
     *
     * @see GeneralSteps#checkOutputContainsCharacter(int, Character)
     *
     * @param match  the sub-string of the text that matches this expression
     * @return the numeric quantity
     */
    @ParameterType(name = "quantity", value = "(an?)|[0-9]+")
    public int defineQuantity(String match) {
        return match.startsWith("a") ? 1 : Integer.parseInt(match);
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

    @Then("the output will be {string}")
    public void checkOutput(String output) {
        assertThat(sluggerHelper.getOutput()).isEqualTo(output);
    }

    @Then("the output will not contain any {character} characters")
    public void checkOutputDoesNotContainCharacter(Character character) {
        assertThat(sluggerHelper.getOutput()).doesNotContain(character.toString());
    }

    @Then("the output will contain {quantity} {character} character(s)")
    public void checkOutputContainsCharacter(int quantity, Character c) {
        int actual = sluggerHelper.getOutput().split(c.toString(), -1).length - 1;
        assertThat(actual)
                .as("Check \"%s\" contains exactly %d occurrences of '%c'", sluggerHelper.getOutput(), quantity, c)
                .isEqualTo(quantity);
    }
}
