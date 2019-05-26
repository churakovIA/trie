package ru.churakov.trie.web;

import org.springframework.web.context.annotation.SessionScope;
import ru.churakov.trie.model.Status;
import ru.churakov.trie.service.WordService;
import ru.churakov.trie.to.WordTo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean(name = "word")
@SessionScope
public class WordController {

    @ManagedProperty(value = "#{wordService}")
    private WordService service;

    private String name;
    private String status;

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    @PostConstruct
//    public void init(){
//        FacesContext fc = FacesContext.getCurrentInstance();
//        Map<String,String> params =
//                fc.getExternalContext().getRequestParameterMap();
//        //name =  params.get("name");
//    }

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

    public String save(){
        WordTo wordTo = new WordTo(name, Status.valueOf(status));
        if(id==null){
            service.create(wordTo);
        }else {
            service.update(wordTo, Integer.parseInt(id));
        }

        return "/views/wordCatalog.xhtml?faces-redirect=true";
    }

    public String close(){
        return "/views/wordCatalog.xhtml?faces-redirect=true";
    }
}
