package eu.autenti.google.app1.controllers;

import eu.autenti.google.app1.entities.DocumentEntity;
import eu.autenti.google.app1.entities.UserEntity;
import eu.autenti.google.app1.repositories.DocumentRepository;
import eu.autenti.google.app1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

/**
 * Created by mateusz on 14.07.2017.
 */
@Controller
public class MainController {


    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private UserRepository userRepository;


    @RequestMapping("/")
    public String mainPage() {
        return "main";
    }

    @RequestMapping("/secured")

    public String securedPage(Model model) {
        Collection<DocumentEntity> documents = documentRepository.findAll();
        Collection<UserEntity> users = userRepository.findAll();
        model.addAttribute("documents", documents);
        model.addAttribute("users", users);
        return "secured";
    }
}
