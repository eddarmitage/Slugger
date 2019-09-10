package com.eddarmitage.slugger.splitting;

import com.eddarmitage.slugger.WordSplitter;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

class RegexWordSplitterTest {

    @Test
    void testRegexStringSplitter_splitsOnSimpleRegex() {
        String regex = "a";
        WordSplitter stringSplitter = WordSplitters.withRegex(regex);
        WordSplitter patternSplitter = WordSplitters.withRegex(Pattern.compile(regex));

        String input = "Mama, take this badge off of me";
        assertThat(stringSplitter.splitWords(input).collect(toList()))
                .hasSize(5)
                .isEqualTo(patternSplitter.splitWords(input).collect(toList()));
    }
}
