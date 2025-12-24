package ru.yandex.practicum;

import java.io.PrintWriter;
import java.util.LinkedHashMap;

public class WordleGame {

    private String answer;

    private int steps;

    private WordleDictionary dictionary;

    private LinkedHashMap<String, String> usedWords = new LinkedHashMap<>();

    private PrintWriter logFile;

    public WordleGame(WordleDictionary dictionary, int steps, PrintWriter logFile) {
        this.dictionary = dictionary;
        this.steps = steps;
        this.answer = WordleDictionary.generateRandomWord(dictionary.getWords());
        this.logFile = logFile;

        logFile.println(String.format("Игра началась, загаданное слово: %s", answer));
    }


    public boolean wordProcessing(String userWord) throws WordNotFoundInDictionary {
        if (steps > 0) {
            if (userWord.isBlank()) {
                String tip = dictionary.getTip(usedWords, answer);

                System.out.println(String.format("Подсказка: %s", tip));

                String testedWord = WordleDictionary.testLettersInWord(answer, tip);

                System.out.println(testedWord);

                if (tip.equals(answer)) {
                    System.out.println("Победа! Вы отгадали слово!");

                    logFile.println(String.format("Победа слово введено верно %s", answer));

                    return true;
                } else {
                    usedWords.put(tip, testedWord);

                    steps--;

                    logFile.println(String.format("Подсказка слова: %s (попытка %s)", tip, steps));

                    System.out.println(String.format("Осталось попыток %s", steps));
                }

                if (steps == 0) {
                    System.out.println(String.format("Проигрыш! Попытки кончились, загаданноое слово %s", answer));

                    logFile.println(String.format("Поражение! Загаданное слово %s", answer));

                    return true;
                }
            } else if (userWord.equals(answer)) {
                System.out.println("Победа! Вы отгадали слово!");

                logFile.println(String.format("Победа слово введено верно %s", answer));

                return true;
            } else if (dictionary.getWords().contains(userWord)) {
                if (userWord.length() == getAnswerLength()) {
                    String testedWord = WordleDictionary.testLettersInWord(answer, userWord);

                    System.out.println(testedWord);

                    steps--;

                    logFile.println(String.format("Попытка ввода слова: %s (попытка %s)", userWord, steps));

                    usedWords.put(userWord, testedWord);

                    System.out.println(String.format("Осталось попыток %s", steps));

                    if (steps == 0) {
                        System.out.println(String.format("Проигрыш! Попытки кончились, загаданноое слово %s", answer));

                        logFile.println(String.format("Поражение! Загаданное слово %s", answer));

                        return true;
                    }
                } else {
                    logFile.println(String.format("Слово %s не соответствует длинне загаданного слова", userWord));
                    System.out.println("Слово не соответствует длинне загаданного слова");
                }
            } else {
                throw new WordNotFoundInDictionary("Cлово %s отсутствует в словаре");
            }
        } else {
            System.out.println(String.format("Проигрыш! Попытки кончились, загаданноое слово %s", answer));

            logFile.println(String.format("Поражение! Загаданное слово %s", answer));

            return true;
        }

        return false;
    }

    public String getAnswer() {
        return answer;
    }

    public int getSteps() {
        return steps;
    }

    public WordleDictionary getDictionary() {
        return dictionary;
    }

    public int getAnswerLength() {
        return answer.length();
    }

}
