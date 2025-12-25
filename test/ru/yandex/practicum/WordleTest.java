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
    public void attemptsOver() {
        try {
            wg.wordProcessing("привет");

            wg.wordProcessing("привет");

            wg.wordProcessing("привет");

            wg.wordProcessing("привет");

            wg.wordProcessing("привет");
        } catch (WordNotFoundInDictionary e) {}

        Assertions.assertTrue(wg.wordProcessing("привет")); //Данный тест работает не с первого раза, так как слово случано. Загаданным слововм должно быть "приват"
    }

    @Test
    public void testTips() {
        Assertions.assertFalse(wg.wordProcessing("")); //Данный тест работает не с первого раза, так как слово случано. Тест должен показать то что попытки выводятся и при этом могут как завершать игру выдавая true (слово угаданно), так и не угадать выбранное слово, то есть вывести false
    }

}
