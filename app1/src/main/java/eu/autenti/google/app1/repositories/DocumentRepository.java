package eu.autenti.google.app1.repositories;

import eu.autenti.google.app1.entities.DocumentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by mateusz on 14.07.2017.
 */
public interface DocumentRepository extends MongoRepository<DocumentEntity, String> {
}
