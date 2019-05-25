package ru.churakov.trie.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.churakov.trie.model.TrieNode;
import ru.churakov.trie.repository.CrudTrieRepository;
import ru.churakov.trie.repository.TrieRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static ru.churakov.trie.TestData.WORDS;
import static ru.churakov.trie.TestData.assertMatch;

class TrieServiceImplTest extends AbstractServiceTest {

    @Autowired
    TrieService service;

    @Autowired
    TrieRepository repository;

    @Autowired
    CrudTrieRepository crudTrieRepository;

    @Test
    void insertRoot() {
        service.insert("");
        TrieNode root = repository.findByPrefix("");
        assertNotNull(root);
        assertNull(root.getParent());
        assertEquals("", root.getName());
    }

    @Test
    void insertExisting() {

        service.insert("суслик");
        service.insert("сусло");
        service.insert("чтобы");
        service.insert("чтоб");
        service.insert("чек");

        testInsert(WORDS, 35);

    }

    @Test
    void insert() {
        service.insert("минимум");
        List<String> words = new ArrayList<>(WORDS);
        words.add("минимум");
        testInsert(words, 40);
    }

    @Test
    void insertNew() {
        service.insert("щука");
        List<String> words = new ArrayList<>(WORDS);
        words.add("щука");
        testInsert(words, 39);
    }

    @Test
    void getAllByPrefix() {
        assertMatch(service.getAllByPrefix("с", Integer.MAX_VALUE),
                "суслик", "сусло", "соль", "солнце", "сани");
        assertMatch(service.getAllByPrefix("с", 4), "суслик", "сусло", "соль", "солнце");
        assertMatch(service.getAllByPrefix("чт", 1), "чтоб");
        assertMatch(service.getAllByPrefix("чт", Integer.MAX_VALUE), "чтобы", "чтоб");
        assertMatch(service.getAllByPrefix("п", 4), "пирамида");
        assertMatch(service.getAllByPrefix("ы", 4));
    }

    @Test
    void delete() {
        String w = "соль";
        assertTrue(service.delete(w));
        List<String> actual = service.getAllByPrefix("", Integer.MAX_VALUE);
        List<String> expected = new ArrayList<>(WORDS);
        expected.remove(w);
        assertMatch(actual, expected);
    }

    @Test
    void deletePrefixExisting() {
        String w = "что";
        assertFalse(service.delete(w));
        List<String> actual = service.getAllByPrefix("", Integer.MAX_VALUE);
        assertMatch(actual, WORDS);
    }

    @Test
    void deleteAbsented() {
        String w = "яблоко";
        assertFalse(service.delete(w));
        List<String> actual = service.getAllByPrefix("", Integer.MAX_VALUE);
        assertMatch(actual, WORDS);
    }

    @Test
    void deleteIncluded() {
        String w = "чтоб";
        assertTrue(service.delete(w));
        List<String> actual = service.getAllByPrefix("", Integer.MAX_VALUE);
        List<String> expected = new ArrayList<>(WORDS);
        expected.remove(w);
        assertMatch(actual, expected);
        assertThat(actual).contains("чтобы");
    }

    void testInsert(List<String> expected, int count) {
        List<String> actual = service.getAllByPrefix("", Integer.MAX_VALUE);
        assertNotNull(actual);
        assertEquals(actual.size(), expected.size());

        assertMatch(actual, expected);

        assertEquals(crudTrieRepository.count(), count);

        assertEquals(crudTrieRepository.getAllByEndTrue().size(), expected.size());
    }

    @Disabled
    @Test void testCache(){
        service.getAllByPrefix("с", Integer.MAX_VALUE);
        service.getAllByPrefix("с", Integer.MAX_VALUE);
        service.getAllByPrefix("п", 4);
        service.getAllByPrefix("п", 4);
        service.getAllByPrefix("с", Integer.MAX_VALUE);
        service.delete("мир");
        service.getAllByPrefix("с", Integer.MAX_VALUE);
    }
}