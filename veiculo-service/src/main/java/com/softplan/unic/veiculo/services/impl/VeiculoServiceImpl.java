package com.softplan.unic.veiculo.services.impl;

import com.softplan.unic.core.beans.VeiculoBean;
import com.softplan.unic.core.documents.VeiculoDocument;
import com.softplan.unic.core.exceptions.NoResultExceptionApi;
import com.softplan.unic.veiculo.repositories.VeiculoRepository;
import com.softplan.unic.veiculo.services.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class VeiculoServiceImpl implements VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Override
    public List<VeiculoBean> listarVeiculo() {
        List<VeiculoBean> list = veiculoRepository.findAll().stream().map(this::toBean).collect(Collectors.toList());
        return Optional.ofNullable(list).orElseThrow(() -> new NoResultExceptionApi());
    }

    @Override
    public VeiculoBean buscarPorID(String id) {
        VeiculoDocument veiculo = veiculoRepository.findById(id).get();
        return Optional.ofNullable(toBean(veiculo)).orElseThrow(() -> new NoResultExceptionApi());
    }

    @Override
    public VeiculoBean salvarVeiculo(VeiculoBean veiculo) {
        VeiculoDocument document = toDocument(veiculo);
        document.setDataCadastro(LocalDate.now());
        return toBean(veiculoRepository.save(document));
    }

    /**
     * Converter Documento para Bean
     *
     * @param document
     * @return
     */
    private VeiculoBean toBean(VeiculoDocument document) {
        return VeiculoBean.builder()
                .id(document.getId())
                .nome(document.getNome())
                .descricao(document.getDescricao())
                .dataCadastro(document.getDataCadastro())
                .build();
    }

    /**
     * Converte Bean para Documento
     *
     * @param bean
     * @return
     */
    private VeiculoDocument toDocument(VeiculoBean bean) {
        return VeiculoDocument.builder()
                .id(bean.getId())
                .nome(bean.getNome())
                .descricao(bean.getDescricao())
                .dataCadastro(bean.getDataCadastro())
                .build();
    }
}
