package com.softplan.unic.produto.services;

import com.softplan.unic.core.beans.ProdutoBean;

import java.util.List;

public interface ProdutoService {

    List<ProdutoBean> listar();

    ProdutoBean buscarPorID(String id);

    ProdutoBean salvar(ProdutoBean produto);
}
