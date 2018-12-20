package com.softplan.unic.produto.services.impl;

import com.softplan.unic.core.beans.ProdutoBean;
import com.softplan.unic.core.converters.ConvertUtils;
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
        List<ProdutoBean> list = repository.findAll().stream().map(ConvertUtils.Produto::to).collect(Collectors.toList());
        return Optional.ofNullable(list).orElseThrow(() -> new NoResultExceptionApi());
    }

    @Override
    public ProdutoBean buscarPorID(String id) {
        ProdutoDocument produto = repository.findById(id).orElseThrow(() -> new NoResultExceptionApi("Não foi encontrado PRODUTO com %s", id));
        return ConvertUtils.Produto.to(produto);
    }

    @Override
    public ProdutoBean salvar(ProdutoBean produto) {
        ProdutoDocument document = ConvertUtils.Produto.to(produto);
        return ConvertUtils.Produto.to(repository.save(document));
    }

}
