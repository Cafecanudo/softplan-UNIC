package com.softplan.unic.produto.clients;

import com.softplan.unic.core.beans.VeiculoBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@FeignClient(name = "veiculo-service")
public interface VeiculoClient {

    @GetMapping("/veiculo/{id}")
    VeiculoBean buscarPorID(@Valid @PathVariable @NotBlank String id);

}
