package ru.churakov.trie.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.churakov.trie.model.TrieNode;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudTrieRepository extends JpaRepository<TrieNode, Integer>, JpaSpecificationExecutor<TrieNode> {

    @EntityGraph(attributePaths = {"children"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT m FROM TrieNode m WHERE m.parent is null")
    List<TrieNode> getWithChildren();

    List<TrieNode> getAllByEndTrue();

}
