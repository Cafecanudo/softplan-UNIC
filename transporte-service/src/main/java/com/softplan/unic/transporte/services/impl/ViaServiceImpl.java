package com.softplan.unic.transporte.services.impl;

import com.softplan.unic.core.beans.ViaBean;
import com.softplan.unic.core.documents.ViaDocument;
import com.softplan.unic.core.exceptions.NoResultExceptionApi;
import com.softplan.unic.transporte.repositories.ViaRepository;
import com.softplan.unic.transporte.services.ViaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ViaServiceImpl implements ViaService {

    @Autowired
    private ViaRepository repository;


    @Override
    public List<ViaBean> listar() {
        List<ViaBean> list = repository.findAll().stream().map(this::toBean).collect(Collectors.toList());
        return Optional.ofNullable(list).orElseThrow(() -> new NoResultExceptionApi());
    }

    @Override
    public ViaBean buscarPorID(String id) {
        ViaDocument regis = repository.findById(id).orElseThrow(() -> new NoResultExceptionApi("Não foi encontrado VIA com %s", id));
        return toBean(regis);
    }

    @Override
    public ViaBean salvar(ViaBean bean) {
        ViaDocument document = toDocument(bean);
        return toBean(repository.save(document));
    }

    /**
     * Converter Documento para Bean
     *
     * @param document
     * @return
     */
    private ViaBean toBean(ViaDocument document) {
        return ViaBean.builder()
                .id(document.getId())
                .nome(document.getNome())
                .valor(document.getValor())
                .build();
    }

    /**
     * Converte Bean para Documento
     *
     * @param bean
     * @return
     */
    private ViaDocument toDocument(ViaBean bean) {
        return ViaDocument.builder()
                .id(bean.getId())
                .nome(bean.getNome())
                .valor(bean.getValor())
                .build();
    }
}
