package eu.autenti.google.app1.services;

import eu.autenti.google.app1.entities.UserEntity;
import eu.autenti.google.app1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {


    @Autowired
    private UserRepository userRepository;

    public UserEntity createIfDoesNotExist(String email) {
        return this.createIfDoesNotExist(email, null);
    }

    public UserEntity createIfDoesNotExist(String email, String temporaryAccessToken) {
        UserEntity user = userRepository.findOneByEmail(email);
        if(user == null) {
            user = userRepository.save(new UserEntity(email, temporaryAccessToken));
        }
        return user;
    }

    public UserEntity updateTemporaryAccessToken(String email, String temporaryAccessToken) {
        UserEntity user = userRepository.findOneByEmail(email);
        if(user != null) {
            user.temporaryAccessToken = temporaryAccessToken;
            user = userRepository.save(user);
            return user;
        }
        return null;
    }

    public UserEntity findByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }
}
