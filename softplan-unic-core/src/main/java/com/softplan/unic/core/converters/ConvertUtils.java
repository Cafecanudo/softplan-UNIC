package com.softplan.unic.core.converters;

import com.softplan.unic.core.beans.*;
import com.softplan.unic.core.documents.*;

import java.util.stream.Collectors;

public class ConvertUtils {

    public static class Veiculo {

        public static VeiculoBean to(VeiculoDocument document) {
            return VeiculoBean.builder()
                    .id(document.getId())
                    .nome(document.getNome())
                    .fatorMultiplicador(document.getFatorMultiplicador())
                    .dataCadastro(document.getDataCadastro())
                    .build();
        }

        public static VeiculoDocument to(VeiculoBean bean) {
            return VeiculoDocument.builder()
                    .id(bean.getId())
                    .nome(bean.getNome())
                    .fatorMultiplicador(bean.getFatorMultiplicador())
                    .dataCadastro(bean.getDataCadastro())
                    .build();
        }
    }

    public static class Produto {

        public static ProdutoBean to(ProdutoDocument document) {
            return ProdutoBean.builder()
                    .id(document.getId())
                    .nome(document.getNome())
                    .image(document.getImage())
                    .peso(document.getPeso())
                    .valor(document.getValor())
                    .build();
        }

        public static ProdutoDocument to(ProdutoBean bean) {
            return ProdutoDocument.builder()
                    .id(bean.getId())
                    .nome(bean.getNome())
                    .image(bean.getImage())
                    .peso(bean.getPeso())
                    .valor(bean.getValor())
                    .build();
        }
    }

    public static class Transporte {

        public static ViaDocument to(ViaBean bean) {
            return ViaDocument.builder()
                    .id(bean.getId())
                    .nome(bean.getNome())
                    .valor(bean.getValor())
                    .build();
        }

        public static ViaBean to(ViaDocument document) {
            return ViaBean.builder()
                    .id(document.getId())
                    .nome(document.getNome())
                    .valor(document.getValor())
                    .build();
        }

        public static ItemBean to(ItemDocument document) {
            return ItemBean.builder()
                    .quantidade(document.getQuantidade())
                    .produto(Produto.to(document.getProduto()))
                    .build();
        }

        public static ItemDocument to(ItemBean bean) {
            return ItemDocument.builder()
                    .quantidade(bean.getQuantidade())
                    .produto(Produto.to(bean.getProduto()))
                    .build();
        }

        public static RotaBean to(RotaDocument document) {
            return RotaBean.builder()
                    .quilometros(document.getQuilometros())
                    .via(Transporte.to(document.getVia()))
                    .build();
        }

        public static RotaDocument to(RotaBean bean) {
            return RotaDocument.builder()
                    .quilometros(bean.getQuilometros())
                    .via(Transporte.to(bean.getVia()))
                    .build();
        }

        public static CalculoTransporteBean to(CalculoTransporteDocument document) {
            return CalculoTransporteBean.builder()
                    .id(document.getId())
                    .veiculo(Veiculo.to(document.getVeiculo()))
                    .rotas(document.getRotas().stream().map(Transporte::to).collect(Collectors.toList()))
                    .cargas(document.getCargas().stream().map(Transporte::to).collect(Collectors.toList()))
                    .calculo(Transporte.to(document.getCalculo()))
                    .build();
        }

        public static CalculoTransporteDocument to(CalculoTransporteBean bean) {
            return CalculoTransporteDocument.builder()
                    .id(bean.getId())
                    .veiculo(Veiculo.to(bean.getVeiculo()))
                    .rotas(bean.getRotas().stream().map(Transporte::to).collect(Collectors.toList()))
                    .cargas(bean.getCargas().stream().map(Transporte::to).collect(Collectors.toList()))
                    .calculo(Transporte.to(bean.getCalculo()))
                    .build();
        }

        public static CalculoDocument to(CalculoBean bean) {
            return CalculoDocument.builder()
                    .pesoTransportado(bean.getPesoTransportado())
                    .valorTransporte(bean.getValorTransporte())
                    .valorEmMercadoria(bean.getValorEmMercadoria())
                    .distanciaPercorrido(bean.getDistanciaPercorrido())
                    .build();
        }

        public static CalculoBean to(CalculoDocument document) {
            return CalculoBean.builder()
                    .pesoTransportado(document.getPesoTransportado())
                    .valorTransporte(document.getValorTransporte())
                    .valorEmMercadoria(document.getValorEmMercadoria())
                    .distanciaPercorrido(document.getDistanciaPercorrido())
                    .build();
        }
    }
}
