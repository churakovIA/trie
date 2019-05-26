package ru.churakov.trie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.churakov.trie.model.Status;
import ru.churakov.trie.model.Word;
import ru.churakov.trie.repository.CrudWordRepository;
import ru.churakov.trie.to.WordTo;

import java.util.List;
import java.util.Objects;

import static ru.churakov.trie.util.WordUtil.fromTo;
import static ru.churakov.trie.util.WordUtil.updateFromTo;

@Service("wordService")
public class WordServiceImpl implements WordService {

    @Autowired
    CrudWordRepository wordRepository;

    @Autowired
    TrieService trieService;

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
            if(wordTo.getStatus()==Status.ACTIVE){
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
}
