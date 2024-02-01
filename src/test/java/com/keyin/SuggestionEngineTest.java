package com.keyin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


//@ExtendWith(MockitoExtension.class)
public class SuggestionEngineTest {

    @Test
    public void testEmptyWord() {
        SuggestionEngine suggestionEngine = new SuggestionEngine();
        assertEquals("", suggestionEngine.generateSuggestions(""));
    }


    @Test public void testCorrectSpelling() {
        SuggestionEngine suggestionEngine = new SuggestionEngine();
        assertEquals("", suggestionEngine.generateSuggestions("correct"));

    }

    @Test
    public void testWrongSpelling() {
        SuggestionEngine suggestionEngine = new SuggestionEngine();

       String word = "rong";
       String suggestions = suggestionEngine.generateSuggestions(word);

       assertNotNull(suggestions, "Expected not null");
    }

}
