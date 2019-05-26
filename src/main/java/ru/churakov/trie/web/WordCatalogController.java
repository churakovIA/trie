package ru.churakov.trie.web;

import ru.churakov.trie.model.Word;
import ru.churakov.trie.service.WordService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;
import java.util.List;

@ManagedBean(name = "wordCatalog")
@RequestScoped
public class WordCatalogController {

    @ManagedProperty(value = "#{wordService}")
    private WordService service;

    private List<Word> words;

    private Word selectedWord;

    public Word getSelectedWord() {
        return selectedWord;
    }

    public void setSelectedWord(Word selectedWord) {
        this.selectedWord = selectedWord;
    }

    @PostConstruct
    public void init() {
        words = service.getAll();
    }

    public WordService getService() {
        return service;
    }

    public void setService(WordService service) {
        this.service = service;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    public String delete(){
        service.delete(selectedWord.getId());
        return "/views/wordCatalog.xhtml?faces-redirect=true";
    }

    public void loadCurrentRequest(ActionEvent event) {
        selectedWord = (Word)event.getComponent().getAttributes().get("word");
    }
}
