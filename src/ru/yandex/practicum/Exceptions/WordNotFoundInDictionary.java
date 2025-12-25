package ru.yandex.practicum.Exceptions;

public class WordNotFoundInDictionary extends RuntimeException {

    public WordNotFoundInDictionary(final String message) {
        super(message);
    }

}
