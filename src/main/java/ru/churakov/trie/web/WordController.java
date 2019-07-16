package ru.churakov.trie.web;

import ru.churakov.trie.model.Status;
import ru.churakov.trie.service.WordService;
import ru.churakov.trie.to.WordTo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "word")
@SessionScoped
public class WordController {

    @ManagedProperty(value = "#{wordService}")
    private WordService service;

    private String id;
    private String name;
    private String status;
    private String length;

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public WordService getService() {
        return service;
    }

    public void setService(WordService service) {
        this.service = service;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String save() {
        WordTo wordTo = new WordTo(name, Status.valueOf(status));
        if (id == null) {
            service.create(wordTo);
        } else {
            service.update(wordTo, Integer.parseInt(id));
        }

        return "/views/wordCatalog.xhtml?faces-redirect=true";
    }

    public String close() {
        return "/views/wordCatalog.xhtml?faces-redirect=true";
    }
}
