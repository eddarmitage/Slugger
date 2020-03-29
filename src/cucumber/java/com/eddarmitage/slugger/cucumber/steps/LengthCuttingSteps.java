package com.eddarmitage.slugger.cucumber.steps;

import com.eddarmitage.slugger.cucumber.SluggerHelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static org.assertj.core.api.Assertions.assertThat;

public class LengthCuttingSteps {

    private final SluggerHelper sluggerHelper;

    public LengthCuttingSteps(SluggerHelper sluggerHelper) {
        this.sluggerHelper = sluggerHelper;
    }

    @Given("a target length of {int} character(s)")
    public void setTargetLength(Integer target) {
        sluggerHelper.set(sluggerHelper.get().withTargetLength(target));
    }

    @Given("hard limits are enforced")
    public void enforceHardLimits() {
        sluggerHelper.set(sluggerHelper.get().withHardLimitsEnforced());
    }

    @Then("the output will be {int} character(s) long")
    public void checkOutputHasLength(int length) {
        assertThat(sluggerHelper.getOutput())
                .as("Check \"%s\" is %d characters long", sluggerHelper.getOutput(), length)
                .hasSize(length);
    }

    @Then("the output will be no longer than {int} character(s) long")
    public void checkOutputIsShorterThanLength(Integer length) {
        assertThat(sluggerHelper.getOutput())
                .as("Check length of \"%s\" is less than or equal to  %d", sluggerHelper.getOutput(), length)
                .hasSizeLessThanOrEqualTo(length);
    }

    @Then("the output will be longer than {int} character(s) long")
    public void checkOutputIsLongerThanLength(Integer length) {
        assertThat(sluggerHelper.getOutput())
                .as("Check length of \"%s\" is greater than %d", sluggerHelper.getOutput(), length)
                .hasSizeGreaterThan(length);
    }
}
