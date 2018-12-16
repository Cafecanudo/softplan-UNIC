package com.softplan.unic.user.repositories;

import com.softplan.unic.user.documents.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserDocument, String> {

    UserDocument findUserDocumentByEmail(String email);

}
