package ru.churakov.trie.repository;

import ru.churakov.trie.model.TrieNode;

import java.util.List;

public interface TrieRepository {

    TrieNode findByPrefix(String prefix);

    List<TrieNode> findAllByPrefix(String prefix);

    void save(TrieNode node);
}
