package eu.autenti.google.app1.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mateusz on 14.07.2017.
 */
@Document
public class DocumentEntity {

    @Id
    public String id;

    public String title;

    public DocumentEntity() {}

    public DocumentEntity(String title) {
        this.title = title;
    }
}
