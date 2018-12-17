package com.softplan.unic.produto.services.impl;

import com.softplan.unic.core.beans.ProdutoBean;
import com.softplan.unic.core.documents.ProdutoDocument;
import com.softplan.unic.core.exceptions.NoResultExceptionApi;
import com.softplan.unic.produto.repositories.ProdutoRepository;
import com.softplan.unic.produto.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository repository;


    @Override
    public List<ProdutoBean> listar() {
        List<ProdutoBean> list = repository.findAll().stream().map(this::toBean).collect(Collectors.toList());
        return Optional.ofNullable(list).orElseThrow(() -> new NoResultExceptionApi());
    }

    @Override
    public ProdutoBean buscarPorID(String id) {
        ProdutoDocument produto = repository.findById(id).get();
        return Optional.ofNullable(toBean(produto)).orElseThrow(() -> new NoResultExceptionApi("Não foi encontrado registro com %s", id));
    }

    @Override
    public ProdutoBean salvar(ProdutoBean produto) {
        ProdutoDocument document = toDocument(produto);
        return toBean(repository.save(document));
    }

    /**
     * Converter Documento para Bean
     *
     * @param document
     * @return
     */
    private ProdutoBean toBean(ProdutoDocument document) {
        return ProdutoBean.builder()
                .id(document.getId())
                .nome(document.getNome())
                .peso(document.getPeso())
                .build();
    }

    /**
     * Converte Bean para Documento
     *
     * @param bean
     * @return
     */
    private ProdutoDocument toDocument(ProdutoBean bean) {
        return ProdutoDocument.builder()
                .id(bean.getId())
                .nome(bean.getNome())
                .peso(bean.getPeso())
                .build();
    }
}
