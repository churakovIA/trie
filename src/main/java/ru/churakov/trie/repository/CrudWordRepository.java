package ru.churakov.trie.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.churakov.trie.model.Word;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudWordRepository extends PagingAndSortingRepository<Word, Integer>, JpaSpecificationExecutor<Word> {

    @Transactional
    @Override
    Word save(Word node);

    @Override
    List<Word> findAll();
}
