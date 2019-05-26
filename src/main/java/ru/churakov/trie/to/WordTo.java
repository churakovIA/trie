package ru.churakov.trie.to;

import ru.churakov.trie.model.Status;

public class WordTo {
    private String name;
    private Status status;

    public WordTo(String name, Status status) {
        this.name = name;
        this.status = status;
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
}
