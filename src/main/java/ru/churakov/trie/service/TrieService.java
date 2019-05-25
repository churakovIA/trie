package ru.churakov.trie.service;

import java.util.List;

public interface TrieService {

    void insert(String word);

    boolean delete(String word);

    List<String> getAllByPrefix(String prefix, int limit);
}
