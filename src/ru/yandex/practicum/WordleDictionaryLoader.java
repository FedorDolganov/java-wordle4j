package ru.yandex.practicum;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/*
этот класс содержит в себе всю рутину по работе с файлами словарей и с кодировками
    ему нужны методы по загрузке списка слов из файла по имени файла
    на выходе должен быть класс WordleDictionary
 */
public class WordleDictionaryLoader {

    private List<String> words = new ArrayList<>();
    private PrintWriter logFile;

    public WordleDictionaryLoader(PrintWriter logFile) {
        this.logFile = logFile;
    }

    public List<String> getWordsListOfFile(String fileName) {
        try (FileReader reader = new FileReader(fileName)) {
            BufferedReader br = new BufferedReader(reader);

            while (br.ready()) {
                words.add(br.readLine());
            }
        }catch (Exception e){
            logFile.println(e.getMessage());
        }
        return this.words;
    }

}
