package eu.autenti.google.app1.util;

import eu.autenti.google.app1.entities.UserEntity;
import eu.autenti.google.app1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

@Component
public class AuthenticationListener implements ApplicationListener<AuthenticationSuccessEvent> {


    @Autowired
    private UserRepository userRepository;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        LinkedHashMap<String, String> details = (LinkedHashMap<String, String>)((OAuth2Authentication) event.getAuthentication()).getUserAuthentication().getDetails();
        UserEntity user = userRepository.findOneByEmail(details.get("email"));
        if(user == null) {
            userRepository.save(new UserEntity(details.get("email")));
        }
    }
}