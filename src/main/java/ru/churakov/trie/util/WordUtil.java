package ru.churakov.trie.util;

import ru.churakov.trie.model.Word;
import ru.churakov.trie.to.WordTo;

public class WordUtil {
    public static Word fromTo(WordTo wordTo) {
        return new Word(null, wordTo.getName().toLowerCase(), wordTo.getStatus(), wordTo.getName().length());
    }

    public static WordTo asTo(Word word) {
        return new WordTo(word.getName(), word.getStatus());
    }

    public static Word updateFromTo(Word word, WordTo wordTo) {
        word.setName(wordTo.getName().toLowerCase());
        word.setStatus(wordTo.getStatus());
        word.setLength(wordTo.getName().length());
        return word;
    }

}
