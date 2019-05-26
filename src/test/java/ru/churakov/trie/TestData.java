package ru.churakov.trie;

import ru.churakov.trie.model.Status;
import ru.churakov.trie.model.Word;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TestData {

    public static List<String> WORDS = List.of("суслик", "соль", "солнце", "сани", "мир", "пирамида", "сусло", "чтобы", "чтоб", "чек");

    public static final Word WORD01 = new Word(100000,"суслик", Status.ACTIVE, 6);
    public static final Word WORD02 = new Word(100001,"соль", Status.ACTIVE, 4);
    public static final Word WORD03 = new Word(100002,"солнце", Status.ACTIVE, 6);
    public static final Word WORD04 = new Word(100003,"сани", Status.ACTIVE, 4);
    public static final Word WORD05 = new Word(100004,"мир", Status.ACTIVE, 3);
    public static final Word WORD06 = new Word(100005,"пирамида", Status.ACTIVE, 8);
    public static final Word WORD07 = new Word(100006,"сусло", Status.ACTIVE, 5);
    public static final Word WORD08 = new Word(100007,"чтобы", Status.ACTIVE, 5);
    public static final Word WORD09 = new Word(100008,"чтоб", Status.ACTIVE, 4);
    public static final Word WORD10 = new Word(100009,"чек", Status.ACTIVE, 3);

    public static void assertMatch(Iterable<Word> actual, Word... expected) {
        assertThat(actual).containsExactlyInAnyOrder(expected);
    }

    public static void assertMatch(Iterable<String> actual, String... expected) {
        assertThat(actual).containsExactlyInAnyOrder(expected);
    }

    public static void assertMatch(Iterable<String> actual, Iterable<String> expected) {
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }
}
