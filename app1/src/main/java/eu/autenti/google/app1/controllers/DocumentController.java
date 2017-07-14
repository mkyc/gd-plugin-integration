package eu.autenti.google.app1.controllers;

import eu.autenti.google.app1.entities.DocumentEntity;
import eu.autenti.google.app1.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Created by mateusz on 14.07.2017.
 */
@RestController
@RequestMapping("/documents")
public class DocumentController {

    @Autowired
    private DocumentRepository documentRepository;

    @RequestMapping(method = RequestMethod.POST)
    public String saveDocument(@RequestBody DocumentEntity document) {
        DocumentEntity result = documentRepository.save(document);
        return result.id;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<DocumentEntity> getAllDocuments(){
        return documentRepository.findAll();
    }
}
