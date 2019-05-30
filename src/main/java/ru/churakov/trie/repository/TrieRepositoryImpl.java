package ru.churakov.trie.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.churakov.trie.model.TrieNode;

import java.util.List;

import static ru.churakov.trie.util.TrieUtil.getSpecForPrefix;

@Repository
@Transactional(readOnly = true)
public class TrieRepositoryImpl implements TrieRepository {

    private final CrudTrieRepository repository;

    public TrieRepositoryImpl(CrudTrieRepository repository) {
        this.repository = repository;
    }

    @Override
    public TrieNode findByPrefix(String prefix) {
        return repository.findOne(getSpecForPrefix(prefix)).orElse(null);
    }

    @Override
    public List<TrieNode> findAllByPrefix(String prefix) {
        return repository.findAll(getSpecForPrefix(prefix));
    }

    @Transactional
    @Override
    public void save(TrieNode node) {
        repository.save(node);
    }
}
