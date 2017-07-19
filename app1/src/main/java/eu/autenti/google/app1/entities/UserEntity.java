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

    public UserEntity(String email) {
        this.email = email;
        this.created = LocalDateTime.now();
    }
}
