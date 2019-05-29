package ru.churakov.trie.web;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import ru.churakov.trie.model.Word;
import ru.churakov.trie.service.WordService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import java.util.List;
import java.util.Map;

import static ru.churakov.trie.util.Util.convertSort;

@ManagedBean(name = "wordCatalog")
@SessionScoped
public class WordCatalogController {

    private static Logger LOG = LoggerFactory.getLogger(WordCatalogController.class);

    @ManagedProperty(value = "#{wordService}")
    private WordService service;

    private Word selectedWord;

    public Word getSelectedWord() {
        return selectedWord;
    }

    public void setSelectedWord(Word selectedWord) {
        this.selectedWord = selectedWord;
    }

    private LazyDataModel<Word> model;

    public LazyDataModel<Word> getModel() {
        return model;
    }

    public void setModel(LazyDataModel<Word> model) {
        this.model = model;
    }

    @PostConstruct
    public void init() {
        model = new LazyDataModel<>() {
            @Override
            public List<Word> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                LOG.debug("model.load(first={},pageSize={},sortField={},sortOrder={},filters={})",
                        first, pageSize, sortField, sortOrder, filters);

                Page<Word> wordPage = filters != null && filters.containsKey("name") ?
                        service.getAllByName((String) filters.get("name"), first / pageSize, pageSize, convertSort(sortField, sortOrder))
                        : service.getAll(first / pageSize, pageSize, convertSort(sortField, sortOrder));

                setRowCount((int) wordPage.getTotalElements());
                return wordPage.getContent();
            }
        };
    }

    public WordService getService() {
        return service;
    }

    public void setService(WordService service) {
        this.service = service;
    }

    public String delete() {
        service.delete(selectedWord.getId());
        return "/views/wordCatalog.xhtml?faces-redirect=true";
    }

    public void loadCurrentRequest(ActionEvent event) {
        selectedWord = (Word) event.getComponent().getAttributes().get("word");
    }
}
