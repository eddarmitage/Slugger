package com.eddarmitage.slugger.cucumber.steps;

import com.eddarmitage.slugger.cucumber.SluggerHelper;
import com.eddarmitage.slugger.splitting.WordSplitters;
import io.cucumber.java.en.Given;

public class SplittingSteps {

    private final SluggerHelper sluggerHelper;

    public SplittingSteps(SluggerHelper sluggerHelper) {
        this.sluggerHelper = sluggerHelper;
    }

    @Given("camel case splitting is enabled")
    public void enableCamelCaseSplitting() {
        sluggerHelper.set(sluggerHelper.get().withAdditionalWordSplitter(WordSplitters.camelCaseWordSplitter()));
    }

    @Given("underscore splitting is enabled")
    public void enableUnderscoreSplitting() {
        sluggerHelper.set(sluggerHelper.get().withAdditionalWordSplitter(WordSplitters.underscoreWordSplitter()));
    }
}
