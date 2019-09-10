package com.eddarmitage.slugger;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

class CharacterReplacerTest {

    @Test
    void testCharactersConvertedToLowercase_whenPreserveCaseDisabled() {
        CharacterReplacer characterReplacer = new CharacterReplacer(Locale.ENGLISH, false);
        assertThat(characterReplacer.replaceCharacters("PsychicSpiesFromChinaTryToStealYourMindsElation"))
                .isEqualTo("psychicspiesfromchinatrytostealyourmindselation");
    }

    @Test
    void testCaseIsPreserved_whenPreserveCaseEnabled() {
        CharacterReplacer characterReplacer = new CharacterReplacer(Locale.ENGLISH, true);
        assertThat(characterReplacer.replaceCharacters("SometimesIFeelLikeIDontHaveAPartner"))
                .isEqualTo("SometimesIFeelLikeIDontHaveAPartner");
    }

    @Test
    void testEnforcedCaseMutator_setsConstructorFlagCorrectly() {
        CharacterReplacer toLowerCaseReplacer = new CharacterReplacer(Locale.ENGLISH, false);
        CharacterReplacer preservedCaseReplacer = new CharacterReplacer(Locale.ENGLISH, true);

        String input = "CantStopAddictedToThisShindig";

        assertThat(toLowerCaseReplacer.withCasePreserved().replaceCharacters(input))
                .isEqualTo(preservedCaseReplacer.replaceCharacters(input));
    }

    @Test
    void testCharactersAreReplaced_whenSpecificReplacementIsGiven() {
        CharacterReplacer characterReplacer = new CharacterReplacer(Locale.ENGLISH, false)
                .withReplacement('ä', "a")
                .withReplacement('Ä', "A");

        assertThat(characterReplacer.replaceCharacters("StändingInLineToSeeTheShowTonightÄndTheresÄLightOn"))
                .isEqualTo("standinginlinetoseetheshowtonightandtheresalighton");
    }
}