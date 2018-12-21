package com.softplan.unic.transporte.services.impl;

import com.softplan.unic.core.beans.ViaBean;
import com.softplan.unic.core.documents.ViaDocument;
import com.softplan.unic.transporte.repositories.ViaRepository;
import com.softplan.unic.transporte.services.ViaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

//@ExtendWith(SpringExtension.class)
@SpringBootTest
@DisplayName("Test Vias")
public class ViaServiceImplTest {

    @Autowired
    private ViaRepository viaRepository;

    @Autowired
    private ViaService viaService;

    @BeforeEach
    public void init() {
        BDDMockito.given(viaRepository.save(ViaDocument.builder().build()))
                .willReturn(ViaDocument.builder()
                        .id("3c4dd418d1e359368054a5f1f9c9b049")
                        .nome("Pavimentada")
                        .valor(1.56)
                        .build());
    }

    @Test
    @DisplayName("Criando via")
    public void criandoVia() {
        ViaBean document = viaService.salvar(ViaBean.builder()
                .nome("Pavimentada")
                .valor(15.5)
                .build());

        assertNotNull(document, "Save não retornou o objeto");
        assertNotNull(document.getId(), "Nao gerou ID do boleto!");
    }

    @Test
    @DisplayName("Testando salvar com registro null")
    public void testandoSalvarComRegistroNull() {
        ViaBean document = viaService.salvar(ViaBean.builder()
                .valor(15.5)
                .build());

        assertNotNull(document, "Save não retornou o objeto");
        assertNotNull(document.getId(), "Nao gerou ID do boleto!");
    }


}