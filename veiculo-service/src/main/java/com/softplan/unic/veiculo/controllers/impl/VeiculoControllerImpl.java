package com.softplan.unic.veiculo.controllers.impl;

import com.softplan.unic.core.beans.VeiculoBean;
import com.softplan.unic.veiculo.controllers.VeiculoController;
import com.softplan.unic.veiculo.services.VeiculoService;
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
@RequestMapping("veiculo")
public class VeiculoControllerImpl implements VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<VeiculoBean> listar() {
        log.info("Listando veiculos cadastrados...");
        return veiculoService.listar();
    }

    @Override
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VeiculoBean buscarPorID(@Valid @PathVariable @NotBlank String id) {
        log.info("Buscar veiculo por ID...");
        return veiculoService.buscarPorID(id);
    }

    @Override
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public VeiculoBean salvar(@Valid @RequestBody VeiculoBean veiculo) {
        log.info("Salvando novo veiculo...");
        return veiculoService.salvar(veiculo);
    }
}
