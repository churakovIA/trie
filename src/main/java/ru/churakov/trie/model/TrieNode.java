package ru.churakov.trie.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="al_tree")
public class TrieNode extends AbstractBaseEntity {

    @Column
    private String name;

    @Column(name = "end_word")
    private boolean end;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "parent_id")
    private TrieNode parent;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "parent")
    private List<TrieNode> children = new ArrayList<>();

    public TrieNode() {
    }

    public TrieNode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public List<TrieNode> getChildren() {
        return children;
    }

    public TrieNode getParent() {
        return parent;
    }

    public void setParent(TrieNode parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "TrieNode{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
