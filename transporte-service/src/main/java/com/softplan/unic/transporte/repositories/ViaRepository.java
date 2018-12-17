package com.softplan.unic.transporte.repositories;

import com.softplan.unic.core.documents.ViaDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ViaRepository extends MongoRepository<ViaDocument, String> {

    Optional<ViaDocument> findById(String id);

}
