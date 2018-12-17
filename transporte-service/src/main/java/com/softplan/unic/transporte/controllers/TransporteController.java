package com.softplan.unic.transporte.controllers;

import com.softplan.unic.core.beans.CalculoBean;
import com.softplan.unic.core.beans.CalculoTransporteBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Api(description = "Servico operações de transpote")
@ApiResponses(
        value = {
                @ApiResponse(code = 201, message = "Registro criado"),
                @ApiResponse(code = 204, message = "Sem retorno"),
                @ApiResponse(code = 400, message = "Parametros inválidos"),
                @ApiResponse(code = 401, message = "Acesso não autorizado"),
                @ApiResponse(code = 404, message = "Registro(s) não encontrado(s)"),
                @ApiResponse(code = 409, message = "Registro duplicado"),
        }
)
public interface TransporteController {

    @ApiOperation(value = "Lista Registro cadastrados", response = CalculoTransporteBean.class, responseContainer = "List")
    List<CalculoTransporteBean> listar();

    @ApiOperation(value = "Buscar Registro por ID", response = CalculoTransporteBean.class)
    CalculoTransporteBean buscarPorID(@Valid @PathVariable @NotBlank String id);

    @ApiOperation(value = "Salvar novo calculo", response = CalculoTransporteBean.class)
    CalculoTransporteBean salvar(CalculoTransporteBean bean);

    @ApiOperation(value = "Efetua o calculo do transporte", response = CalculoBean.class)
    CalculoBean calcular(CalculoTransporteBean bean);
}
