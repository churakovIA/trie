package ru.churakov.trie.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.churakov.trie.service.TrieService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean(name = "root")
@SessionScoped
public class RootController {

    private static Logger LOG = LoggerFactory.getLogger(RootController.class);
    private static final int LIMIT_WORDS = 4;

    @ManagedProperty(value = "#{trieService}")
    private TrieService service;

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TrieService getService() {
        return service;
    }

    public void setService(TrieService service) {
        this.service = service;
    }

    public List<String> complete(String query) {
        LOG.debug("complete{}", query);
        return service.getAllByPrefix(query, LIMIT_WORDS);
    }
}