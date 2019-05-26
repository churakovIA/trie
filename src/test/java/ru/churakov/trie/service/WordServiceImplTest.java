package ru.churakov.trie.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.churakov.trie.model.Status;
import ru.churakov.trie.model.Word;
import ru.churakov.trie.to.WordTo;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.churakov.trie.TestData.*;
import static ru.churakov.trie.util.WordUtil.asTo;
import static ru.churakov.trie.util.WordUtil.fromTo;

class WordServiceImplTest extends AbstractServiceTest {

    @Autowired
    WordService service;

    @Test
    void create() {
        WordTo newWordTo = new WordTo("баня", Status.ACTIVE);
        Word created = service.create(newWordTo);
        Word expected = fromTo(newWordTo);
        expected.setId(created.getId());
        assertThat(created).isEqualToComparingFieldByField(expected);
    }

    @Test
    void delete() {
        service.delete(WORD01.getId());
        assertMatch(service.getAll(), WORD02, WORD03, WORD04, WORD05, WORD06, WORD07, WORD08, WORD09, WORD10);
    }

    @Test
    void update() {
        Word updated = new Word(WORD01);
        updated.setStatus(Status.INACTIVE);
        service.update(asTo(updated), WORD01.getId());
        assertThat(service.get(WORD01.getId())).isEqualToComparingFieldByField(updated);
    }

    @Test
    void get() {
        Word word = service.get(WORD01.getId());
        assertThat(word).isEqualTo(WORD01);
    }

    @Test
    void getAll() {
        assertMatch(service.getAll(), WORD01, WORD02, WORD03, WORD04, WORD05, WORD06, WORD07, WORD08, WORD09, WORD10);
    }
}