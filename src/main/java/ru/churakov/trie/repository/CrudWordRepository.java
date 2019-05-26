package ru.churakov.trie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.churakov.trie.model.Word;

@Transactional(readOnly = true)
public interface CrudWordRepository extends JpaRepository<Word, Integer> {

    @Transactional
    @Override
    Word save(Word node);

}
