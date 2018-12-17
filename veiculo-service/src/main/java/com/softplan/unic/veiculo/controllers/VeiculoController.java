package com.softplan.unic.veiculo.controllers;

import com.softplan.unic.core.beans.VeiculoBean;
import io.swagger.annotations.*;

import java.util.List;

@Api(description = "Servico veículo")
@ApiResponses(
        value = {
                @ApiResponse(code = 201, message = "Veículo criado"),
                @ApiResponse(code = 204, message = "Sem retorno"),
                @ApiResponse(code = 400, message = "Parametros inválidos"),
                @ApiResponse(code = 401, message = "Acesso não autorizado"),
                @ApiResponse(code = 404, message = "Veículo(s) nao encontrado(s)"),
        }
)
public interface VeiculoController {

    @ApiOperation(value = "Lista veículos cadastrados", response = VeiculoBean.class)
    List<VeiculoBean> listar();


    @ApiOperation(value = "Buscar veículo por ID", response = VeiculoBean.class)
    VeiculoBean buscarPorID(@ApiParam(value = "ID do veículo", required = true) String id);

    @ApiOperation(value = "Salvar novo veículo", response = VeiculoBean.class)
    VeiculoBean salvar(VeiculoBean veiculo);
}
