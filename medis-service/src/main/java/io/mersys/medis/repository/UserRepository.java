package io.mersys.medis.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import io.mersys.medis.documents.user.User;


@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User findByUsername(final String userName);
}
