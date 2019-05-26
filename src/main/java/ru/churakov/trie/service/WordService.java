package ru.churakov.trie.service;

import ru.churakov.trie.model.Word;
import ru.churakov.trie.to.WordTo;

import java.util.List;

public interface WordService {

    Word create(WordTo wordTo);

    void delete(int id);

    void update(WordTo wordTo, int id);

    Word get(int id);

    List<Word> getAll();
}
