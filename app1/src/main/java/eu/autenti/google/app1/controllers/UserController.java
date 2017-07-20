package eu.autenti.google.app1.controllers;

import eu.autenti.google.app1.entities.UserEntity;
import eu.autenti.google.app1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public String saveUser(@RequestBody UserEntity user) {
        UserEntity result = userService.createIfDoesNotExist(user.email, user.temporaryAccessToken);
        return result.id;
    }

    @RequestMapping(method = RequestMethod.GET)
    public UserEntity getUser(@RequestParam(name = "email") String email) {
        return userService.findByEmail(email);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String saveTemporaryAccessToken(@RequestBody UserEntity user) {
        UserEntity result = userService.updateTemporaryAccessToken(user.email, user.temporaryAccessToken);
        return result.id;
    }
}
