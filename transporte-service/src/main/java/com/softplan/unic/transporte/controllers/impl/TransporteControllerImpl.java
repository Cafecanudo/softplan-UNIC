package com.softplan.unic.transporte.controllers.impl;

import com.softplan.unic.core.beans.CalculoBean;
import com.softplan.unic.core.beans.CalculoTransporteBean;
import com.softplan.unic.transporte.controllers.TransporteController;
import com.softplan.unic.transporte.services.CalcularTransporteService;
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
@RequestMapping("transporte")
public class TransporteControllerImpl implements TransporteController {

    @Autowired
    private CalcularTransporteService calcularTransporteService;

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CalculoTransporteBean> listar() {
        log.info("Listando calculos efetuados...");
        return calcularTransporteService.listar();
    }

    @Override
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CalculoTransporteBean buscarPorID(@Valid @PathVariable @NotBlank String id) {
        log.info("Buscar por ID...");
        return calcularTransporteService.buscarPorID(id);
    }

    @Override
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public CalculoTransporteBean salvar(@Valid @RequestBody CalculoTransporteBean bean) {
        log.info("Salvado novo calculo de transporte...");
        return calcularTransporteService.salvar(bean);
    }

    @Override
    @PostMapping(value = "/calcular", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public CalculoBean calcular(CalculoTransporteBean bean) {
        log.info("Efetuando calculo de transporte...");
        return calcularTransporteService.calcular(bean);
    }
}
