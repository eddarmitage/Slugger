package com.eddarmitage.slugger;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

class CharacterReplacer {

    private static final String NOT_SLUG_CHARS_REGEX = "\\W";

    private final Locale locale;
    private final boolean preserveCase;
    private final Map<Character, String> replacements;

    CharacterReplacer(Locale locale, boolean preserveCase) {
        this.locale = locale;
        this.preserveCase = preserveCase;
        this.replacements = new HashMap<>();
    }

    private CharacterReplacer(Locale locale, boolean preserveCase, Map<Character, String> replacements) {
        this.locale = locale;
        this.preserveCase = preserveCase;
        this.replacements = replacements;
    }

    public CharacterReplacer withCasePreserved() {
        return new CharacterReplacer(locale, true);
    }

    public CharacterReplacer withReplacement(Character c, String replacement) {
        HashMap<Character, String> updatedReplacements = new HashMap<>(replacements);
        updatedReplacements.put(c, replacement);
        return new CharacterReplacer(locale, preserveCase, updatedReplacements);
    }

    public String replaceCharacters(String input) {
        for (Entry<Character, String> replacement : replacements.entrySet()) {
            input = input.replaceAll(replacement.getKey().toString(), replacement.getValue());
        }
        return changeCase(replaceNonSlugCharacters(input));
    }

    private String replaceNonSlugCharacters(String word) {
        return Normalizer.normalize(word, Form.NFKD)
                .replaceAll(NOT_SLUG_CHARS_REGEX, "");
    }

    private String changeCase(String word) {
        return preserveCase ? word : word.toLowerCase(locale);
    }
}
