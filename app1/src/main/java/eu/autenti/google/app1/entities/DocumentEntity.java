package eu.autenti.google.app1.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by mateusz on 14.07.2017.
 */
@Document
public class DocumentEntity {

    @Id
    public String id;

    public String title;

    public String accessToken;

    public String fileId;

    public LocalDateTime created;

    public String path;

    public DocumentEntity() {}

    public DocumentEntity(String title) {
        this.title = title;
    }

    public DocumentEntity(String title, String accessToken, String fileId) {
        this.accessToken = accessToken;
        this.fileId = fileId;
        this.title = title;
        this.created = LocalDateTime.now();
        this.path = "./" + UUID.randomUUID().toString().replaceAll("-", "") + ".pdf";
    }
}
