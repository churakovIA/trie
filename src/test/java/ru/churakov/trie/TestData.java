package ru.churakov.trie;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TestData {

    public static List<String> WORDS = List.of("суслик", "соль", "солнце", "сани", "мир", "пирамида", "сусло", "чтобы", "чтоб", "чек");

    public static void assertMatch(Iterable<String> actual, String... expected) {
        assertThat(actual).containsExactlyInAnyOrder(expected);
    }

    public static void assertMatch(Iterable<String> actual, Iterable<String> expected) {
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }
}
