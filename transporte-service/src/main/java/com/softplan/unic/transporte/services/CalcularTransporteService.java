package com.softplan.unic.transporte.services;

import com.softplan.unic.core.beans.*;

import java.util.List;

public interface CalcularTransporteService {

    List<CalculoTransporteBean> listar();

    CalculoTransporteBean buscarPorID(String id);

    CalculoTransporteBean salvar(CalculoTransporteBean bean);

    CalculoBean calcular(CalculoTransporteBean bean);

    CalculoBean calcularCustoTransporte(VeiculoBean veiculo, List<ItemBean> carga, List<RotaBean> rotas);
}
