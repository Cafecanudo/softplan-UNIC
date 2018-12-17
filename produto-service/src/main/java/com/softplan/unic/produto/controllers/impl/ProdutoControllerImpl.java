package com.softplan.unic.produto.controllers.impl;

import com.softplan.unic.core.beans.ProdutoBean;
import com.softplan.unic.produto.controllers.ProdutoController;
import com.softplan.unic.produto.services.ProdutoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("produto")
public class ProdutoControllerImpl implements ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProdutoBean> listar() {
        log.info("Listando produtos cadastrados...");
        return produtoService.listar();
    }

    @Override
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProdutoBean buscarPorID(@Valid @PathVariable @NotBlank String id) {
        log.info("Buscar produto por ID...");
        return produtoService.buscarPorID(id);
    }

    @Override
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ProdutoBean salvar(@Valid @RequestBody ProdutoBean produto) {
        log.info("Salvando novo produto...");
        return produtoService.salvar(produto);
    }
}
