package com.softplan.unic.transporte.services.impl;

import com.softplan.unic.core.beans.*;
import com.softplan.unic.core.clients.ProdutoClient;
import com.softplan.unic.core.clients.VeiculoClient;
import com.softplan.unic.core.converters.ConvertUtils;
import com.softplan.unic.core.documents.CalculoTransporteDocument;
import com.softplan.unic.core.exceptions.BadRequestExceptionApi;
import com.softplan.unic.core.exceptions.NoResultExceptionApi;
import com.softplan.unic.core.exceptions.NoResultExternalExceptionApi;
import com.softplan.unic.core.utils.NumberUtils;
import com.softplan.unic.transporte.repositories.CalculoTransporteRepository;
import com.softplan.unic.transporte.services.CalcularTransporteService;
import com.softplan.unic.transporte.services.ViaService;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CalcularTransporteServiceImpl implements CalcularTransporteService {

    @Autowired
    private CalculoTransporteRepository repository;

    @Autowired
    private ViaService viaService;

    @Autowired
    private ProdutoClient produtoClient;

    @Autowired
    private VeiculoClient veiculoClient;

    @Override
    public List<CalculoTransporteBean> listar() {
        List<CalculoTransporteBean> list = repository.findAll().stream().map(ConvertUtils.Transporte::to).collect(Collectors.toList());
        return Optional.ofNullable(list).orElseThrow(() -> new NoResultExceptionApi());
    }

    @Override
    public CalculoTransporteBean buscarPorID(String id) {
        CalculoTransporteDocument regis = repository.findById(id).orElseThrow(() -> new NoResultExceptionApi("Não foi encontrado registro com %s", id));
        return ConvertUtils.Transporte.to(regis);
    }

    @Override
    public CalculoTransporteBean salvar(CalculoTransporteBean bean) {
        bean.setCalculo(calcular(bean));
        CalculoTransporteDocument document = ConvertUtils.Transporte.to(bean);
        return ConvertUtils.Transporte.to(repository.save(document));
    }

    @Override
    public CalculoBean calcular(CalculoTransporteBean bean) {
        validarVeiculo(bean.getVeiculo());
        validarListaProdutos(bean.getCarga());
        validarVias(bean.getRotas());
        return calcularCustoTransporte(bean.getVeiculo(), bean.getCarga(), bean.getRotas());
    }

    /**
     * Calcular custo de
     *
     * @param veiculo
     * @param carga
     * @param rotas
     * @return
     */
    @Override
    public CalculoBean calcularCustoTransporte(VeiculoBean veiculo, List<ItemBean> carga, List<RotaBean> rotas) {
        double custoRota = rotas.stream().mapToDouble(rota -> rota.getQuilometros() * rota.getVia().getValor())
                .sum() * veiculo.getFatorMultiplicador();

        double peso = carga.stream().mapToDouble(item -> item.getQuantidade() * item.getProduto().getPeso()).sum();
        double valorProdutos = carga.stream().mapToDouble(item -> item.getQuantidade() * item.getProduto().getValor()).sum();
        double distanciaPercorrido = rotas.stream().mapToDouble(rota -> rota.getQuilometros()).sum();

        //Calculando excedente
        custoRota += (((peso / 1000) - 5.0) * 0.02) * 100;
        return CalculoBean.builder()
                .valorTransporte(NumberUtils.arredondarParaCima(custoRota))
                .pesoTransportado(NumberUtils.arredondarParaCima(peso))
                .valorEmMercadoria(NumberUtils.arredondarParaCima(valorProdutos))
                .distanciaPercorrido(NumberUtils.arredondarParaCima(distanciaPercorrido))
                .build();
    }

    /**
     * Valida lista de viar da requisicao
     *
     * @param rotas
     */
    private void validarVias(List<RotaBean> rotas) {
        try {
            rotas.stream().forEach(rota -> {
                ViaBean _via = Optional.ofNullable(viaService.buscarPorID(rota.getVia().getId()))
                        .orElseThrow(() -> new BadRequestExceptionApi("Via \"%s\" da lista não foi encontrado!", rota.getVia().getNome()));

                if (!_via.equals(rota.getVia())) {
                    throw new BadRequestExceptionApi("Uma VIA da lista é inválida!");
                }
            });
        } catch (FeignException e) {
            if (e.getMessage().indexOf("NOT_FOUND") > -1) {
                throw NoResultExternalExceptionApi.extractJSON(e.getMessage().substring(e.getMessage().indexOf("{")));
            }
            throw e;
        }
    }

    /**
     * Validar lista de produto
     *
     * @param listItens
     * @return
     */
    private void validarListaProdutos(List<ItemBean> listItens) {
        try {
            listItens.stream().forEach(itemBean -> {
                ProdutoBean produto = Optional.ofNullable(produtoClient.buscarPorID(itemBean.getProduto().getId()))
                        .orElseThrow(() -> new BadRequestExceptionApi("Item \"%s\" da lista de produtos não foi encontrado!",
                                itemBean.getProduto().getNome()));
                if (!produto.equals(itemBean.getProduto())) {
                    throw new BadRequestExceptionApi("Produto '%s' da solicitação é inválido!", produto.getNome());
                }
            });
        } catch (FeignException e) {
            if (e.getMessage().indexOf("NOT_FOUND") > -1) {
                throw NoResultExternalExceptionApi.extractJSON(e.getMessage().substring(e.getMessage().indexOf("{")));
            }
            throw e;
        }
    }

    /**
     * Validar se dados enviado sao iguais ao solicitados
     *
     * @param veiculo
     * @return
     */
    private void validarVeiculo(VeiculoBean veiculo) {
        try {
            VeiculoBean _veiculo = Optional.ofNullable(veiculoClient.buscarPorID(veiculo.getId()))
                    .orElseThrow(() -> new BadRequestExceptionApi("Veículo \"%s\" não foi encontrado!",
                            veiculo.getNome()));

            if (!veiculo.equals(_veiculo)) {
                throw new BadRequestExceptionApi("Veículo da solicitação é inválido!");
            }
        } catch (FeignException e) {
            if (e.getMessage().indexOf("NOT_FOUND") > -1) {
                throw NoResultExternalExceptionApi.extractJSON(e.getMessage().substring(e.getMessage().indexOf("{")));
            }
            throw e;
        }
    }

}
