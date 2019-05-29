package ru.churakov.trie.util;

import org.primefaces.model.SortOrder;
import org.springframework.data.domain.Sort;

import java.util.Optional;

public class Util {
    public static Optional<Sort> convertSort(String sortField, SortOrder sortOrder) {
        return sortOrder == SortOrder.UNSORTED || sortField == null ? Optional.empty() :
                Optional.of(Sort.by(sortOrder == SortOrder.ASCENDING ? Sort.Direction.ASC : Sort.Direction.DESC, sortField));
    }
}