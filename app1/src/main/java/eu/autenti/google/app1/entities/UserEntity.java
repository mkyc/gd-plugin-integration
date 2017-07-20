package eu.autenti.google.app1.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public class UserEntity {

    @Id
    public String id;

    public String email;

    public LocalDateTime created;

    public String temporaryAccessToken;

    public UserEntity() {}

    public UserEntity(String email) {
        new UserEntity(email, null);
    }

    public UserEntity(String email, String temporaryAccessToken) {
        this.email = email;
        this.temporaryAccessToken = temporaryAccessToken;
        this.created = LocalDateTime.now();
    }
}
