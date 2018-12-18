package com.softplan.unic.transporte.repositories;

import com.softplan.unic.core.documents.CalculoTransporteDocument;
import com.softplan.unic.core.utils.ObjectUtils;
import org.apache.commons.io.FileUtils;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataMongoTest
@ExtendWith(SpringExtension.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@DisplayName("Test: Calculo transporte.")
public class CalculoTransporteRepositoryTest {

    @Autowired
    private MongoTemplate mongodb;

    @Test
    @DisplayName("Salvando CalculoTransporteDocumento")
    public void A_salvar() throws IOException {
        CalculoTransporteDocument document = ObjectUtils.toObject(FileUtils.readFileToString(
                new File(this.getClass().getResource("/CalculoTransporteDocumento.json").getFile())),
                CalculoTransporteDocument.class);
        mongodb.save(document);
        assertNotNull(document.getId(), "Não gerou ID");
    }

    @Test
    @DisplayName("Buscar por ID")
    public void B_buscandoPorID() {
        CalculoTransporteDocument document = mongodb.findById("5c1846407d892292c878ffa8", CalculoTransporteDocument.class);
        assertNotNull(document, "Não encontrou registro");

        assertNotNull(document.getVeiculo(), "Não encontrou veículo");

        assertNotNull(document.getCarga(), "Não encontrou items");
        assertThat("Lista está vazia.", document.getCarga(), not(equalTo(Collections.EMPTY_MAP)));
    }

    @Test
    @DisplayName("Buscar por ID que nao existe")
    public void C_buscandoPorIDQueNaoExiste() {
        assertNull(mongodb.findById("AAAAAAAAAAAAAAAA", CalculoTransporteDocument.class));
    }
}