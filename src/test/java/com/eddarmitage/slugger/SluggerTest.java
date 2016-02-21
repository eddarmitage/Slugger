package com.eddarmitage.slugger;

import com.eddarmitage.slugger.splitting.WordSplitters;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class SluggerTest {


    @Test
    public void testNullInput_causesNullPointerException() {
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> Slugger.create().sluggify(null))
                .withMessage("Input to Slugger.sluggify() is null")
                .withNoCause();
    }


    @Test
    public void testEnforcingHardLimitsWithNoTarget_causesException() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> Slugger.create().withHardLimitsEnforced())
                .withMessage("Can't enforce hard limits without target length")
                .withNoCause();
    }

    @Test
    public void testSeperatorWithEscapableCharacter_causesIllegalArgumentException() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> Slugger.create().withSeparator("@"))
                .withMessage("Separator \"@\" contains unsafe characters")
                .withNoCause();
    }

    @Test
    public void testReadmeExamplesWork_simpleExample() {
        Slugger slugger = Slugger.create();

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(slugger.sluggify("My first blog post")).isEqualTo("my-first-blog-post");
        softly.assertThat(slugger.sluggify("Another post!")).isEqualTo("another-post");
        softly.assertAll();
    }

    @Test
    public void testReadmeExamplesWork_replaceWordSplitterExample() {
        Slugger slugger = Slugger.create().withWordSplitter(WordSplitters.camelCaseWordSplitter());
        assertThat(slugger.sluggify("MyFirstBlogPost")).isEqualTo("my-first-blog-post");
    }

    @Test
    public void testReadmeExamplesWork_additionalWordSplitterExample() {
        Slugger slugger = Slugger.create().withAdditionalWordSplitter(WordSplitters.camelCaseWordSplitter());
        assertThat(slugger.sluggify("The importance of toString()")).isEqualTo("the-importance-of-to-string");
    }
}
