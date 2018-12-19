package com.softplan.unic.transporte.services.impl;

import com.softplan.unic.core.beans.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Testando regras de calculo de transportes")
public class CalcularTransporteServiceImplTest {

    CalcularTransporteServiceImpl service;
    VeiculoBean veiculo;
    List<ItemBean> cargas;
    List<RotaBean> rotas;

    @BeforeEach
    public void setUp() {
        this.service = new CalcularTransporteServiceImpl();
        this.veiculo = VeiculoBean.builder().nome("Caminhão caçamba").fatorMultiplicador(1.05).build();
        this.cargas = new ArrayList<>();

        this.rotas = new ArrayList<>();
    }

    @Test
    @DisplayName("Calcular: Menor de 5 Toneladas")
    public void calculoDeTransporte() {
        rotas.add(RotaBean.builder().quilometros(100.0).via(ViaBean.builder().nome("Pavimentada").valor(0.54).build()).build());

        cargas.add(ItemBean.builder().quantidade(2500.0).produto(ProdutoBean.builder().nome("Produto 1").peso(1.0).valor(2.45).build()).build());
        cargas.add(ItemBean.builder().quantidade(2500.0).produto(ProdutoBean.builder().nome("Produto 2").peso(1.0).valor(1.21).build()).build());

        CalculoBean calculoBean = service.calcularCustoTransporte(veiculo, cargas, rotas);

        assertNotNull(calculoBean);
        assertEquals(calculoBean.getValorTransporte(), 56.7, 1);
        assertEquals(calculoBean.getPesoTransportado(), 5000.0, 1);
    }

    @Test
    @DisplayName("Calcular: Mais de 5 Toneladas")
    public void calculoDeTransporteError() {
        rotas.add(RotaBean.builder().quilometros(100.0).via(ViaBean.builder().nome("Pavimentada").valor(0.54).build()).build());

        cargas.clear();
        cargas.add(ItemBean.builder().quantidade(4000.0).produto(ProdutoBean.builder().nome("Produto 1").peso(1.0).valor(2.45).build()).build());
        cargas.add(ItemBean.builder().quantidade(4000.0).produto(ProdutoBean.builder().nome("Produto 2").peso(1.0).valor(1.21).build()).build());

        CalculoBean calculoBean = service.calcularCustoTransporte(veiculo, cargas, rotas);

        assertNotNull(calculoBean);
        assertEquals(calculoBean.getValorTransporte(), 62.7, 1);
        assertEquals(calculoBean.getPesoTransportado(), 8000.0, 1);
    }
}