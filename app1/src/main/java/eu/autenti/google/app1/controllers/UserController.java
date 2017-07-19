package eu.autenti.google.app1.controllers;

import eu.autenti.google.app1.entities.UserEntity;
import eu.autenti.google.app1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.POST)
    public String saveUser(@RequestBody UserEntity user) {
        UserEntity result = userRepository.save(new UserEntity(user.email));
        return result.id;
    }

    @RequestMapping(method = RequestMethod.GET)
    public UserEntity getUser(@RequestParam(name = "email") String email) {
        return userRepository.findOneByEmail(email);
    }
}
