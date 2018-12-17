package com.softplan.unic.transporte.controllers.impl;

import com.softplan.unic.core.beans.ViaBean;
import com.softplan.unic.transporte.controllers.ViaController;
import com.softplan.unic.transporte.services.ViaService;
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
@RequestMapping("via")
public class ViaControllerImpl implements ViaController {

    @Autowired
    private ViaService viaService;

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ViaBean> listar() {
        log.info("Listando vias cadastradas...");
        return viaService.listar();
    }

    @Override
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ViaBean buscarPorID(@Valid @PathVariable @NotBlank String id) {
        log.info("Buscar via por ID...");
        return viaService.buscarPorID(id);
    }

    @Override
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ViaBean salvar(@Valid @RequestBody ViaBean bean) {
        log.info("Salvando nova via...");
        return viaService.salvar(bean);
    }
}
