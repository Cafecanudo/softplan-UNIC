package com.softplan.unic.produto.repositories;

import com.softplan.unic.produto.documents.ProdutoDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends MongoRepository<ProdutoDocument, String> {

    Optional<ProdutoDocument> findById(String id);

    Optional<ProdutoDocument> findByNomeIgnoreCase(String nome);
}
