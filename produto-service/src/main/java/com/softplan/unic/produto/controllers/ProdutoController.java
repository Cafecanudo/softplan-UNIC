package com.softplan.unic.produto.controllers;

import com.softplan.unic.core.beans.ProdutoBean;
import io.swagger.annotations.*;

import java.util.List;

@Api(description = "Servico Produto")
@ApiResponses(
        value = {
                @ApiResponse(code = 201, message = "Produto criado"),
                @ApiResponse(code = 204, message = "Sem retorno"),
                @ApiResponse(code = 400, message = "Parametros inválidos"),
                @ApiResponse(code = 401, message = "Acesso não autorizado"),
                @ApiResponse(code = 404, message = "Produto(s) não encontrado(s)"),
        }
)
public interface ProdutoController {

    @ApiOperation(value = "Lista Produto cadastrados", response = ProdutoBean.class)
    List<ProdutoBean> listar();


    @ApiOperation(value = "Buscar Produto por ID", response = ProdutoBean.class)
    ProdutoBean buscarPorID(@ApiParam(value = "ID do Produto", required = true) String id);

    @ApiOperation(value = "Salvar novo Produto", response = ProdutoBean.class)
    ProdutoBean salvar(ProdutoBean produto);
}
