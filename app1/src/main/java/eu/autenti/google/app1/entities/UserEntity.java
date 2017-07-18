package eu.autenti.google.app1.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserEntity {

    @Id
    public String id;

    public String email;

    public UserEntity(String email) {
        this.email = email;
    }
}
