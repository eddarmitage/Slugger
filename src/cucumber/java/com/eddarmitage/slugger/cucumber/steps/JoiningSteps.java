package com.eddarmitage.slugger.cucumber.steps;

import com.eddarmitage.slugger.cucumber.SluggerHelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static org.assertj.core.api.Assertions.assertThat;

public class JoiningSteps {

    private final SluggerHelper sluggerHelper;
    private String separator = "-";

    public JoiningSteps(SluggerHelper sluggerHelper) {
        this.sluggerHelper = sluggerHelper;
    }

    @Given("a separator of {string}")
    public void specifySeperator(String separator) {
        this.separator = separator;
        sluggerHelper.set(sluggerHelper.get().withSeparator(separator));
    }

    @Then("the output will contain {int} words")
    public void countOutputWords(int quantity) {
        int actual = sluggerHelper.getOutput().split(separator).length;
        assertThat(actual).isEqualTo(quantity);
    }
}
