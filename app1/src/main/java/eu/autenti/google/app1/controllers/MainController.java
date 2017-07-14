package eu.autenti.google.app1.controllers;

import eu.autenti.google.app1.entities.DocumentEntity;
import eu.autenti.google.app1.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.LinkedHashMap;

/**
 * Created by mateusz on 14.07.2017.
 */
@Controller
public class MainController {


    @Autowired
    private DocumentRepository documentRepository;

    @RequestMapping("/")
    public String mainPage(Model model) {
        return "main";
    }

    @RequestMapping("/secured")
    public String securedPage(OAuth2Authentication authentication, Model model) {
        LinkedHashMap<String, String> details = (LinkedHashMap<String, String>)authentication.getUserAuthentication().getDetails();
        Collection<DocumentEntity> documents = documentRepository.findAll();
        model.addAttribute("user", details.get("email"));
        model.addAttribute("documents", documents);
        return "secured";
    }
}
