package ru.churakov.trie.repository;

import ru.churakov.trie.model.TrieNode;

public interface TrieRepository {

    TrieNode findByPrefix(String prefix);

    void save(TrieNode node);
}
