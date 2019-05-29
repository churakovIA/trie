package ru.churakov.trie.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ru.churakov.trie.model.Word;
import ru.churakov.trie.to.WordTo;

import java.util.Optional;

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

    public static Pageable getPageable(int pageNumber, int pageSize, Optional<Sort> sort){
        return PageRequest.of(pageNumber, pageSize, sort.orElseGet(() -> Sort.by(Sort.Direction.ASC, "name")));
    }

}
