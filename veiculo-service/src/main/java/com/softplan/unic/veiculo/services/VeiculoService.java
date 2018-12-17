package com.softplan.unic.veiculo.services;

import com.softplan.unic.core.beans.VeiculoBean;

import java.util.List;

public interface VeiculoService {

    List<VeiculoBean> listar();

    VeiculoBean buscarPorID(String id);

    VeiculoBean salvar(VeiculoBean veiculo);
}
