package ru.churakov.trie.model;

import javax.persistence.*;

@Entity
@Table(name = "words")
public class Word extends AbstractBaseEntity {

    @Column
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column
    private int length;

    public Word() {
    }

    public Word(Integer id, String name, Status status, int length) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.length = length;
    }

    public Word(Word word) {
        this(word.getId(), word.getName(), word.getStatus(), word.getLength());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Word{" +
                "name='" + name + '\'' +
                ", status=" + status +
                ", length=" + length +
                ", id=" + id +
                '}';
    }
}
