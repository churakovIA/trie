package ru.churakov.trie.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.churakov.trie.model.Status;
import ru.churakov.trie.model.Word;
import ru.churakov.trie.repository.CrudWordRepository;
import ru.churakov.trie.to.WordTo;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static ru.churakov.trie.util.WordUtil.*;

@Service("wordService")
public class WordServiceImpl implements WordService {

    private Logger log = LoggerFactory.getLogger(getClass());

    private final CrudWordRepository wordRepository;

    private final TrieService trieService;

    public WordServiceImpl(CrudWordRepository wordRepository, TrieService trieService) {
        this.wordRepository = wordRepository;
        this.trieService = trieService;
    }

    @Transactional
    @Override
    public Word create(WordTo wordTo) {
        Objects.requireNonNull(wordTo);
        trieService.insert(wordTo.getName());
        return wordRepository.save(fromTo(wordTo));
    }

    @Transactional
    @Override
    public void delete(int id) {
        Word word = get(id);
        trieService.delete(word.getName());
        wordRepository.delete(word);
    }

    @Transactional
    @Override
    public void update(WordTo wordTo, int id) {
        Objects.requireNonNull(wordTo);
        wordRepository.findById(id).ifPresent(word -> {
            trieService.delete(word.getName());
            if (wordTo.getStatus() == Status.ACTIVE) {
                trieService.insert(wordTo.getName());
            }
            updateFromTo(word, wordTo);
        });
    }

    @Override
    public Word get(int id) {
        return wordRepository.findById(id).orElse(null);
    }

    @Override
    public List<Word> getAll() {
        return wordRepository.findAll();
    }

    @Override
    public Page<Word> getAll(int pageNumber, int pageSize, Optional<Sort> sort) {
        log.debug("getAll(pageNumber={},pageSize={},sort={})", pageNumber, pageSize, sort);
        return wordRepository.findAll(getPageable(pageNumber,pageSize,sort));
    }

    @Override
    public Page<Word> getAllByName(String name, int pageNumber, int pageSize, Optional<Sort> sort) {
        log.debug("getAllByNameStartWith(name={},pageNumber={},pageSize={},sort={})", name, pageNumber, pageSize, sort);
        return wordRepository.findAll(
                (root, query, cb) -> cb.like(root.get("name"),name+"%"),
                getPageable(pageNumber,pageSize,sort));
    }
}
