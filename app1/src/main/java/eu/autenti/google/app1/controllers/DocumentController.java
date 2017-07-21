package eu.autenti.google.app1.controllers;

import eu.autenti.google.app1.entities.DocumentEntity;
import eu.autenti.google.app1.repositories.DocumentRepository;
import eu.autenti.google.app1.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collection;

/**
 * Created by mateusz on 14.07.2017.
 */
@RestController
@RequestMapping("/documents")
public class DocumentController {

    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private DocumentService documentService;


    @RequestMapping(method = RequestMethod.POST)
    public String saveDocument(@RequestBody DocumentEntity document) {
        DocumentEntity result = documentRepository.save(new DocumentEntity(document.title, document.accessToken, document.fileId));
        try {
            documentService.getAndStoreDocument(result.accessToken, result.fileId, result.path);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.id;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<DocumentEntity> getAllDocuments(){
        return documentRepository.findAll();
    }

    @RequestMapping(value="/download", method=RequestMethod.GET, produces = "application/pdf")
    public FileSystemResource downloadFile(@Param(value="id") String id) {
        DocumentEntity document = documentRepository.findOne(id);
        return new FileSystemResource(new File(document.path));

    }

}
