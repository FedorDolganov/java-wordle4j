package ru.yandex.practicum;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/*
в главном классе нам нужно:
    создать лог-файл (он должен передаваться во все классы)
    создать загрузчик словарей WordleDictionaryLoader
    загрузить словарь WordleDictionary с помощью класса WordleDictionaryLoader
    затем создать игру WordleGame и передать ей словарь
    вызвать игровой метод в котором в цикле опрашивать пользователя и передавать информацию в игру
    вывести состояние игры и конечный результат
 */
public class Wordle {

    public static void main(String[] args) {
        try (PrintWriter logFile = new PrintWriter("session.log")) {
            WordleDictionaryLoader wordleDictionaryLoader = new WordleDictionaryLoader(logFile);

            WordleDictionary wordleDictionary = new WordleDictionary(wordleDictionaryLoader.getWordsListOfFile("words_ru.txt"));

            WordleGame wordleGame = new WordleGame(wordleDictionary, 6, logFile);

            startGame(wordleGame, logFile);
        } catch (IOException e) {
            System.out.println("Ошибка записи в .log файл");
        }
    }

    private static void startGame(WordleGame wordleGame, PrintWriter logFile) {
        System.out.println("Игра Wordle началась!");

        Scanner scanner = new Scanner(System.in);

        System.out.println(String.format("Введите слово состоящее из %s символов", wordleGame.getAnswerLength()));

        while (true) {
            try {
                if (wordleGame.wordProcessing(scanner.nextLine())) {
                    break;
                }
            } catch (WordNotFoundInDictionary e) {
                logFile.println(e.getMessage());
                System.out.println("Введенное слово отсутствует словаре");
            } catch (Exception e) {
                logFile.println(e.getMessage());
            }
        }
    }

}
