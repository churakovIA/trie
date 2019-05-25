package ru.churakov.trie.Util;

import org.springframework.data.jpa.domain.Specification;
import ru.churakov.trie.model.TrieNode;

import javax.persistence.criteria.*;

public class TrieUtil {

    public static Specification<TrieNode> getSpecForPrefix(String prefix) {
        return (Specification<TrieNode>) (node, query, cb) -> {

            if ("".equals(prefix)) {
                return cb.and(cb.equal(node.get("name"), ""), cb.isNull(node.get("parent")));
            }

            Predicate[] predicates = new Predicate[prefix.length() * 2];
            for (int i = 0; i < prefix.length(); i++) {
                String name = String.valueOf(prefix.charAt(prefix.length() - i - 1));
                String parentName = prefix.length() - i - 2 < 0 ? "" : String.valueOf(prefix.charAt(prefix.length() - i - 2));

                Path<Object> path = null;
                Path<Object> parentPath = node.get("parent");
                for (int j = 0; j < i; j++) {
                    path = path == null ? node.get("parent") : path.get("parent");
                    parentPath = parentPath.get("parent");
                }
                predicates[i * 2] = cb.equal(path == null ? node.get("name") : path.get("name"), name);
                predicates[i * 2 + 1] = cb.equal(parentPath.get("name"), parentName);

            }
            return cb.and(predicates);
        };
    }
}
