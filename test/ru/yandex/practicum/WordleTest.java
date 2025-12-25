package ru.yandex.practicum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.Exceptions.WordNotFoundInDictionary;

import java.io.PrintWriter;


class WordleTest {

    private WordleGame wg;
    private WordleDictionary wordleDictionary;

    @BeforeEach
    public void beforeEach(){
        PrintWriter logFile = new PrintWriter(System.out);

        WordleDictionaryLoader wordleDictionaryLoader = new WordleDictionaryLoader(logFile);

        wordleDictionary = new WordleDictionary(wordleDictionaryLoader.getWordsListOfFile("custom_words.txt"));

        wg = new WordleGame(wordleDictionary, 6, logFile);
    }

    @Test
    public void correctTipToWord() {
        Assertions.assertEquals(WordleDictionary.testLettersInWord("привет", "пеинат"), "+^+--+");
    }

    @Test
    public void loadWordsFile() {
        Assertions.assertEquals(wordleDictionary.getWords().getFirst(), "привет");
    }

    @Test
    public void enterWordNotInDictionary() {
        boolean error = false;

        try {
            wg.wordProcessing("абвгдеё");
        } catch (WordNotFoundInDictionary e) {
            error = true;
        }

        Assertions.assertTrue(error);
    }

    @Test
    public void testForWinOverTheGame() {
        Assertions.assertTrue(wg.wordProcessing("привет"));
    }

    @Test
    public void testTipCanOverTheGame() {
        Assertions.assertTrue(wg.wordProcessing(""));
    }

}
