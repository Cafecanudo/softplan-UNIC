package com.softplan.unic.veiculo.services.impl;

import com.softplan.unic.core.beans.VeiculoBean;
import com.softplan.unic.core.converters.ConvertUtils;
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
    private VeiculoRepository repository;

    @Override
    public List<VeiculoBean> listar() {
        List<VeiculoBean> list = repository.findAll().stream().map(ConvertUtils.Veiculo::to).collect(Collectors.toList());
        return Optional.ofNullable(list).orElseThrow(() -> new NoResultExceptionApi());
    }

    @Override
    public VeiculoBean buscarPorID(String id) {
        VeiculoDocument veiculo = repository.findById(id).orElseThrow(() -> new NoResultExceptionApi("Não foi encontrado VEÍCULO com %s", id));
        return ConvertUtils.Veiculo.to(veiculo);
    }

    @Override
    public VeiculoBean salvar(VeiculoBean veiculo) {
        VeiculoDocument document = ConvertUtils.Veiculo.to(veiculo);
        document.setDataCadastro(LocalDate.now());
        return ConvertUtils.Veiculo.to(repository.save(document));
    }

}
