package com.eddarmitage.slugger;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.OptionalInt;

import static org.assertj.core.api.Assertions.assertThat;

class SlugBuilderTest {

    @Test
    void testSlugBuilder_withDefaultConfiguration() {
        SlugBuilder slugBuilder = new SlugBuilder("-");
        Arrays.asList("one", "two", "three").forEach(slugBuilder::addWord);

        assertThat(slugBuilder.build()).isEqualTo("one-two-three");
    }

    @Test
    void testSlugBuilder_withLengthRestriction() {
        SlugBuilder slugBuilder = new SlugBuilder(OptionalInt.of(10), false, "-");
        Arrays.asList("one", "two", "three").forEach(slugBuilder::addWord);

        assertThat(slugBuilder.build()).isEqualTo("one-two");
    }

    @Test
    void testSlugBuilder_withLengthRestrictionAndLongSeparator() {
        SlugBuilder slugBuilder = new SlugBuilder(OptionalInt.of(10), false, "-----");
        Arrays.asList("one", "two", "three").forEach(slugBuilder::addWord);

        assertThat(slugBuilder.build()).isEqualTo("one");
    }

    @Test
    void testSlugBuilder_withLongFirstWord() {
        SlugBuilder slugBuilder = new SlugBuilder(OptionalInt.of(10), false, "-");
        slugBuilder.addWord("refrigerator");

        assertThat(slugBuilder.build()).isEqualTo("refrigerator");
    }

    @Test
    void testSlugBuilder_withLongFirstWordAndHardLimitsEnforced() {
        SlugBuilder slugBuilder = new SlugBuilder(OptionalInt.of(10), true, "-");
        slugBuilder.addWord("refrigerator");

        assertThat(slugBuilder.build()).isEqualTo("refrigerat");
    }

    @Test
    void testSlugBuilder_isMergedWithOtherSlugBuilder() {
        SlugBuilder firstBuilder = new SlugBuilder("-");
        Arrays.asList("one", "two").forEach(firstBuilder::addWord);

        SlugBuilder secondBuilder = new SlugBuilder("-");
        Arrays.asList("three", "four").forEach(secondBuilder::addWord);

        String mergedResult = firstBuilder.merge(secondBuilder).build();

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(mergedResult).hasSize(18);
        softly.assertThat(mergedResult).isEqualTo("one-two-three-four");
        softly.assertAll();
    }
}
