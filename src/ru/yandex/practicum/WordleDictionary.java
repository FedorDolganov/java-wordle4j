package ru.yandex.practicum;

import java.util.*;

/*
этот класс содержит в себе список слов List<String>
    его методы похожи на методы списка, но учитывают особенности игры
    также этот класс может содержать рутинные функции по сравнению слов, букв и т.д.
 */
public class WordleDictionary {

    private List<String> words;

    public WordleDictionary(List<String> words) {
        this.words = words;
    }

    public List<String> getWords() {
        return words;
    }

    public static String generateRandomWord(List<String> list){
        Random random = new Random();
        return list.get(random.nextInt(list.size()));
    }

    public static String testLettersInWord(String answer, String userWord){
        StringBuilder result = new StringBuilder();

        for (int i = 0;i < answer.length();i++){

            String userLetter = userWord.substring(i, i + 1);
            String answerLetter = answer.substring(i, i + 1);

            if (answerLetter.equals(userLetter)){
                result.append("+");
            }else if (answer.contains(userLetter)){
                result.append("^");
            }else{
                result.append("-");
            }
        }

        return result.toString();
    }

    public String getTip(LinkedHashMap<String, String> map, String answer){

        List<String> allowedWords = new ArrayList<>();

        List<Integer> trueID = new ArrayList<>();

        List<String> containsLetters = new ArrayList<>();

        for (String key : map.keySet()) {
            for (int i = 0;i < key.length();i++){
                if (map.get(key).charAt(i) == '+') {
                    trueID.add(i);
                    containsLetters.add(key.substring(i, i + 1));
                }else if (map.get(key).charAt(i) == '^') {
                    containsLetters.add(key.substring(i, i + 1));
                }
            }
        }

        for (String word : words) {
            if (word.length() == answer.length()) {
                if (testWordAllowed(trueID, containsLetters, word, answer)){
                    if (!map.containsKey(word)){
                        allowedWords.add(word);
                    }
                }
            }
        }

        return generateRandomWord(allowedWords);

    }

    public boolean testWordAllowed(List<Integer> trueID, List<String> containsLetters, String word, String answer) {
        for (String cletter : containsLetters) {
            if (!word.contains(cletter)) {
                return false;
            }
        }

        for (int trID : trueID){
            if (!word.substring(trID, trID + 1).equals(answer.substring(trID, trID + 1))) {
                return false;
            }
        }

        return true;
    }

}
