package ru.yandex.practicum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;


class WordleTest {

    private WordleGame wg;

    @BeforeEach
    public void beforeEach(){
        PrintWriter logFile = new PrintWriter(System.out);

        WordleDictionaryLoader wordleDictionaryLoader = new WordleDictionaryLoader(logFile);

        WordleDictionary wordleDictionary = new WordleDictionary(wordleDictionaryLoader.getWordsListOfFile("words_ru.txt"));

        wg = new WordleGame(wordleDictionary, 6, logFile);
    }

    @Test
    public void correctTipToWord() {
        Assertions.assertEquals(WordleDictionary.testLettersInWord("привет", "пеинат"), "+^+--+");
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

}
