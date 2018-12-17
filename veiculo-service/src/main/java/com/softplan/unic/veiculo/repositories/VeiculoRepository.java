package com.softplan.unic.veiculo.repositories;

import com.softplan.unic.veiculo.documents.VeiculoDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VeiculoRepository extends MongoRepository<VeiculoDocument, String> {

    Optional<VeiculoDocument> findById(String id);

    Optional<VeiculoDocument> findByNomeIgnoreCase(String nome);

}
