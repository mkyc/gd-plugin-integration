package eu.autenti.google.app1.repositories;

import eu.autenti.google.app1.entities.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEntity, String> {

    UserEntity findOneByEmail(String email);
}
