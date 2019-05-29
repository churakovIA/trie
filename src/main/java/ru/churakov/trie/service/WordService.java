package ru.churakov.trie.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import ru.churakov.trie.model.Word;
import ru.churakov.trie.to.WordTo;

import java.util.List;
import java.util.Optional;

public interface WordService {

    Word create(WordTo wordTo);

    void delete(int id);

    void update(WordTo wordTo, int id);

    Word get(int id);

    List<Word> getAll();

    Page<Word> getAll(int pageNumber, int pageSize, Optional<Sort> sort);

    Page<Word> getAllByName(String name, int pageNumber, int pageSize, Optional<Sort> sort);
}
