package com.softplan.unic.transporte.services.impl;

import com.softplan.unic.core.beans.*;
import com.softplan.unic.core.clients.ProdutoClient;
import com.softplan.unic.core.clients.VeiculoClient;
import com.softplan.unic.core.exceptions.BadRequestExceptionApi;
import com.softplan.unic.core.exceptions.NoResultExceptionApi;
import com.softplan.unic.transporte.documents.CalculoTransporteDocument;
import com.softplan.unic.transporte.repositories.CalculoTransporteRepository;
import com.softplan.unic.transporte.services.CalcularTransporteService;
import com.softplan.unic.transporte.services.ViaService;
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
        List<CalculoTransporteBean> list = repository.findAll().stream().map(this::toBean).collect(Collectors.toList());
        return Optional.ofNullable(list).orElseThrow(() -> new NoResultExceptionApi());
    }

    @Override
    public CalculoTransporteBean buscarPorID(String id) {
        CalculoTransporteDocument regis = repository.findById(id).get();
        return Optional.ofNullable(toBean(regis)).orElseThrow(() -> new NoResultExceptionApi("Não foi encontrado registro com %s", id));
    }

    @Override
    public CalculoTransporteBean salvar(CalculoTransporteBean bean) {
        CalculoTransporteDocument document = toDocument(bean);
        return toBean(repository.save(document));
    }

    @Override
    public CalculoBean calcular(CalculoTransporteBean bean) {
        validarVeiculo(bean.getVeiculo());
        validarListaProdutos(bean.getItens());
        validarVias(bean.getVias());


        return CalculoBean.builder().build();
    }

    /**
     * Valida lista de viar da requisicao
     *
     * @param vias
     */
    private void validarVias(List<ViaBean> vias) {
        vias.stream().forEach(via -> {
            ViaBean _via = Optional.ofNullable(viaService.buscarPorID(via.getId()))
                    .orElseThrow(() -> new BadRequestExceptionApi("Via \"%s\" da lista não foi encontrado!", via.getNome()));

            if (!_via.equals(via)) {
                throw new BadRequestExceptionApi("Uma VIA da lista é inválida!");
            }
        });
    }

    /**
     * Validar lista de produto
     *
     * @param listItens
     * @return
     */
    private void validarListaProdutos(List<ItemBean> listItens) {
        listItens.stream().forEach(itemBean -> {
            ProdutoBean produto = Optional.ofNullable(produtoClient.buscarPorID(itemBean.getProduto().getId()))
                    .orElseThrow(() -> new BadRequestExceptionApi("Item \"%s\" da lista de produtos não foi encontrado!",
                            itemBean.getProduto().getNome()));
            if (!produto.equals(itemBean.getProduto())) {
                throw new BadRequestExceptionApi("Produto da solicitação é inválido!");
            }
        });
    }

    /**
     * Validar se dados enviado sao iguais ao solicitados
     *
     * @param veiculo
     * @return
     */
    private void validarVeiculo(VeiculoBean veiculo) {
        VeiculoBean _veiculo = Optional.ofNullable(veiculoClient.buscarPorID(veiculo.getId()))
                .orElseThrow(() -> new BadRequestExceptionApi("Veículo \"%s\" não foi encontrado!",
                        veiculo.getNome()));

        if (!veiculo.equals(_veiculo)) {
            throw new BadRequestExceptionApi("Veículo da solicitação é inválido!");
        }
    }

    /**
     * Converter Documento para Bean
     *
     * @param document
     * @return
     */
    private CalculoTransporteBean toBean(CalculoTransporteDocument document) {
        return CalculoTransporteBean.builder()
                .id(document.getId())
                .build();
    }

    /**
     * Converte Bean para Documento
     *
     * @param bean
     * @return
     */
    private CalculoTransporteDocument toDocument(CalculoTransporteBean bean) {
        return CalculoTransporteDocument.builder()
                .id(bean.getId())
                .build();
    }

}
