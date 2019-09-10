package com.eddarmitage.slugger.splitting;

import com.eddarmitage.slugger.WordSplitter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CamelCaseWordSplitterTest {

    private WordSplitter splitter;

    @BeforeEach
    void setup() {
        splitter = WordSplitters.camelCaseWordSplitter();
    }

    @Test
    void testCamelCaseSplitter_splitsOnCaseChange() {
        assertThat(splitter.splitWords("TheseMistCoveredMountains")).hasSize(4);
    }

    @Test
    void testCamelCaseSplitter_doesntSplitOnWhitespace() {
        assertThat(splitter.splitWords("a love-struck romeo sings a streetsuss serenade")).hasSize(1);
    }

    @Test
    void testCamelCaseSplitter_splitsOnConsecutiveUppercaseLetters() {
        assertThat(splitter.splitWords("IAmJeremiahDixonIAmAGeordieBoy")).hasSize(9);
    }

    @Test
    void testCamelCaseSplitter_doesntSplitAllCapsInput() {
        assertThat(splitter.splitWords("THEY HAD A LAST SUPPER THE DAY OF THE BEACHING")).hasSize(1);
    }

    @Test
    void testCamelCaseSplitter_doesntSplitAccronyms() {
            assertThat(splitter.splitWords("YouPlayTheGuitarOnTheMTV")).hasSize(7);
    }

    @Test
    void testCamelCaseSplitter_splitsCorrectlyAfterAnAccronym() {
        assertThat(splitter.splitWords("YouPlayTheGuitarOnTheMTVThatAintWorkin")).hasSize(10);
    }
}
