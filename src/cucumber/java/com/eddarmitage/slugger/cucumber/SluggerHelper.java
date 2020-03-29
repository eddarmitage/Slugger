package com.eddarmitage.slugger.cucumber;

import com.eddarmitage.slugger.Slugger;

public class SluggerHelper {

    private Slugger slugger;
    private String output = "";

    public SluggerHelper() {
        slugger = Slugger.create();
    }

    public Slugger get() {
        return slugger;
    }

    public void set(Slugger slugger) {
        this.slugger = slugger;
    }

    public void applyInput(String input) {
        output = slugger.sluggify(input);
    }

    public String getOutput() {
        return output;
    }
}
