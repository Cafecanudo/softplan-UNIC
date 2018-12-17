package com.softplan.unic.produto.clients;

import com.softplan.unic.core.beans.ProdutoBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@FeignClient(name = "produto-service")
public interface ProdutoClient {

    @GetMapping("/produto/{id}")
    ProdutoBean buscarPorID(@Valid @PathVariable @NotBlank String id);

}
