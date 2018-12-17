package com.softplan.unic.veiculo.services;

import com.softplan.unic.core.beans.VeiculoBean;

import java.util.List;

public interface VeiculoService {

    List<VeiculoBean> listarVeiculo();

    VeiculoBean buscarPorID(String id);

    VeiculoBean salvarVeiculo(VeiculoBean veiculo);
}
