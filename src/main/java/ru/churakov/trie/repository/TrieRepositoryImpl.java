package ru.churakov.trie.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.churakov.trie.model.TrieNode;

import java.util.List;

import static ru.churakov.trie.Util.TrieUtil.getSpecForPrefix;

@Repository
@Transactional(readOnly = true)
public class TrieRepositoryImpl implements TrieRepository {

    @Autowired
    private CrudTrieRepository repository;

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
