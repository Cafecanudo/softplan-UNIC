package com.softplan.unic.transporte.services;

import com.softplan.unic.core.beans.ViaBean;

import java.util.List;

public interface ViaService {

    List<ViaBean> listar();

    ViaBean buscarPorID(String id);

    ViaBean salvar(ViaBean via);
}
