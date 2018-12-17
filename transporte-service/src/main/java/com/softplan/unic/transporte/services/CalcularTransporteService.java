package com.softplan.unic.transporte.services;

import com.softplan.unic.core.beans.CalculoBean;
import com.softplan.unic.core.beans.CalculoTransporteBean;

import java.util.List;

public interface CalcularTransporteService {

    List<CalculoTransporteBean> listar();

    CalculoTransporteBean buscarPorID(String id);

    CalculoTransporteBean salvar(CalculoTransporteBean bean);

    CalculoBean calcular(CalculoTransporteBean bean);
}
