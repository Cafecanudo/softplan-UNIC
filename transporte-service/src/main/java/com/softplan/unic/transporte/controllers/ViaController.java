package com.softplan.unic.transporte.controllers;

import com.softplan.unic.core.beans.ViaBean;
import io.swagger.annotations.*;

import java.util.List;

@Api(description = "Servico operações sobre VIAS")
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
public interface ViaController {

    @ApiOperation(value = "Lista Registro cadastrados", response = ViaBean.class, responseContainer = "List")
    List<ViaBean> listar();


    @ApiOperation(value = "Buscar Registro por ID", response = ViaBean.class)
    ViaBean buscarPorID(@ApiParam(value = "ID do Registro", required = true) String id);

    @ApiOperation(value = "Salvar novo Registro", response = ViaBean.class)
    ViaBean salvar(ViaBean via);
}
