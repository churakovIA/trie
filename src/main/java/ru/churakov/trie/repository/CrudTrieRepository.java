package ru.churakov.trie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;
import ru.churakov.trie.model.TrieNode;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudTrieRepository extends JpaRepository<TrieNode, Integer>, JpaSpecificationExecutor<TrieNode> {

    List<TrieNode> getAllByEndTrue();

}
