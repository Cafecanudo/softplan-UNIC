package com.softplan.unic.veiculo;

import com.softplan.unic.veiculo.documents.VeiculoDocument;
import com.softplan.unic.veiculo.repositories.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.ArrayList;
import java.util.List;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class VeiculoServiceApplication implements CommandLineRunner {

    @Autowired
    private VeiculoRepository veiculoRepository;

    public static void main(String[] args) {
        SpringApplication.run(VeiculoServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<VeiculoDocument> list = new ArrayList<>();
        list.add(VeiculoDocument.builder().nome("Caminhão Baú").fatorMultiplicador(1.0).build());
        list.add(VeiculoDocument.builder().nome("Caminhão Caçamba").fatorMultiplicador(1.05).build());
        list.add(VeiculoDocument.builder().nome("Carreta").fatorMultiplicador(1.12).build());

        list.stream().forEach(veiculo -> {
            if (!veiculoRepository.findByNomeIgnoreCase(veiculo.getNome()).isPresent()) {
                veiculoRepository.save(veiculo);
            }
        });
    }
}
