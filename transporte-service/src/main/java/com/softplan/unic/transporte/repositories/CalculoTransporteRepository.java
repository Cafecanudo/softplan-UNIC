package com.softplan.unic.transporte.repositories;

import com.softplan.unic.transporte.documents.CalculoTransporteDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CalculoTransporteRepository extends MongoRepository<CalculoTransporteDocument, String> {

    Optional<CalculoTransporteDocument> findById(String id);

}
