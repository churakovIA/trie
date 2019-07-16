package ru.churakov.trie.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.churakov.trie.model.TrieNode;
import ru.churakov.trie.repository.TrieRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static ru.churakov.trie.util.TrieUtil.isAllowedWord;
import static ru.churakov.trie.util.TrieUtil.prepareWord;

@Service("trieService")
public class TrieServiceImpl implements TrieService {

    private final TrieRepository repository;

    public TrieServiceImpl(TrieRepository repository) {
        this.repository = repository;
    }

    @CacheEvict(value = "words", allEntries = true)
    @Transactional
    @Override
    public void insert(String word) {
        Objects.requireNonNull(word, "word must not be null");
        String preparedWord = prepareWord(word);
        if(!isAllowedWord(preparedWord)){
            return;
        }
        TrieNode branch = getBranch(preparedWord);
        if (branch.getParent() != null) {
            branch.setEnd(true);
        }
        repository.save(branch);
    }

    @CacheEvict(value = "words", allEntries = true)
    @Transactional
    @Override
    public boolean delete(String word) {
        Objects.requireNonNull(word, "word must not be null");
        String preparedWord = prepareWord(word);
        if(!isAllowedWord(preparedWord)){
            return false;
        }
        TrieNode node = repository.findByPrefix(preparedWord);
        if (node != null && node.isEnd()) {
            node.setEnd(false);
            return true;
        }
        return false;
    }

    @Cacheable("words")
    @Transactional(readOnly = true)
    @Override
    public List<String> getAllByPrefix(String prefix, int limit) {
        Objects.requireNonNull(prefix, "prefix must not be null");
        String preparedWord = prepareWord(prefix);
        if(!isAllowedWord(preparedWord)){
            return Collections.emptyList();
        }
        if (limit < 1) limit = Integer.MAX_VALUE;
        TrieNode node = repository.findByPrefix(preparedWord);
        if(node==null) return Collections.emptyList();
        List<TrieNode> leaves = new ArrayList<>();
        readWords(node.getChildren(), leaves, limit);
        return leaves.stream()
                .map(this::wordFromTreeNode)
                .collect(Collectors.toList());
    }

    private TrieNode getBranch(String prefix) {
        TrieNode node = repository.findByPrefix(prefix);
        if (node == null) {
            if ("".equals(prefix)) {
                node = new TrieNode("");
                return node;
            }
            node = new TrieNode(prefix.substring(prefix.length() - 1));
            node.setParent(getBranch(prefix.substring(0, prefix.length() - 1)));
            return node;
        } else {
            return node;
        }
    }

    private String wordFromTreeNode(TrieNode node) {
        StringBuilder joiner = new StringBuilder();
        if (node != null) {
            do {
                joiner.append(node.getName());
                node = node.getParent();
            } while (node.getParent() != null);
        }
        return joiner.reverse().toString();
    }

    private void readWords(List<TrieNode> nodes, List<TrieNode> words, int limit) {
        if (words.size() == limit) return;
        for (TrieNode node : nodes) {
            if (node.isEnd()) {
                words.add(node);
                if (words.size() == limit) return;
            }
            readWords(node.getChildren(), words, limit);
        }
    }

}