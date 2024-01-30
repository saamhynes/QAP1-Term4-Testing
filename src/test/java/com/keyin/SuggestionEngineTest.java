package com.keyin;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.URL;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(MockitoExtension.class)
public class SuggestionEngineTest {
    private SuggestionEngine suggestionEngine = new SuggestionEngine();

    @Mock
    private SuggestionsDatabase mockSuggestionDB;
    private boolean testInstanceSame = false;

    @Test
    public void testGenerateSuggestions() throws Exception {
        URL resourceUrl = getClass().getClassLoader().getResource("words.txt");
        if (resourceUrl != null) {
            suggestionEngine.loadDictionaryData((Paths.get(resourceUrl.toURI())));
        } else {
            System.out.println("Error: Could not find words.txt");
        }

        Assertions.assertTrue(suggestionEngine.generateSuggestions(("hellw")).contains("hello"));
    }

    @Test
    public void testGenerateSuggestionsFail() throws Exception {
        URL resourceUrl = getClass().getClassLoader().getResource("words.txt");
        if (resourceUrl != null) {
            suggestionEngine.loadDictionaryData((Paths.get(resourceUrl.toURI())));
        } else {
            System.out.println("Error: Could not find words.txt");
        }


        testInstanceSame = true;
        Assertions.assertTrue(testInstanceSame);
        Assertions.assertFalse(suggestionEngine.generateSuggestions(("hello")).contains("hello"));
    }

    @Test
    public void testSuggestionsAsMock() {
        Map<String,Integer> wordMapForTest = new HashMap<>();

        wordMapForTest.put("test", 1);

        Mockito.when(mockSuggestionDB.getWordMap()).thenReturn(wordMapForTest);

        suggestionEngine.setWordSuggestionDB(mockSuggestionDB);

        Assertions.assertFalse(suggestionEngine.generateSuggestions("test").contains("test"));

        Assertions.assertTrue(suggestionEngine.generateSuggestions("tes").contains("test"));
    }


    @Test
    public void testEmptyWord() {

        assertEquals("", suggestionEngine.generateSuggestions(""));
    }


    @Test public void testCorrectSpelling() {
        assertEquals("", suggestionEngine.generateSuggestions("correct "));

    }

    @Test
    public void testShortWord() {
        String suggestions = suggestionEngine.generateSuggestions("occurred");
        assertFalse(suggestions.isEmpty());
        assertTrue(suggestions.contains("occurred"));
    }

//    @Test
//    public void testFalseWord() {
//        String suggestions = suggestionEngine.generateSuggestions("fnrewuifgbrueyfgyrue");
//        assertFalse(suggestions.isEmpty());
//        assertTrue(suggestions.contains("fnrewuifgbrueyfgyrue"));
//    }

}
