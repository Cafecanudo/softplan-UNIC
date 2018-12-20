package com.softplan.unic.transporte;

import com.softplan.unic.core.clients.ProdutoClient;
import com.softplan.unic.core.clients.VeiculoClient;
import com.softplan.unic.core.documents.ViaDocument;
import com.softplan.unic.transporte.repositories.ViaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.ArrayList;
import java.util.List;

@EnableFeignClients(basePackageClasses = {ProdutoClient.class, VeiculoClient.class})
@EnableDiscoveryClient
@SpringBootApplication
public class TransporteServiceApplication implements CommandLineRunner {

    @Autowired
    private ViaRepository viaRepository;

    public static void main(String[] args) {
        SpringApplication.run(TransporteServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<ViaDocument> list = new ArrayList<>();
        list.add(ViaDocument.builder().nome("Pavimentada").valor(0.54).build());
        list.add(ViaDocument.builder().nome("Não-pavimentada").valor(0.62).build());
        list.add(ViaDocument.builder().nome("Marítima").valor(1.32).build());
        list.add(ViaDocument.builder().nome("Aérea").valor(2.45).build());

        list.stream().forEach(viaDocument -> {
            if (!viaRepository.findByNomeIgnoreCase(viaDocument.getNome()).isPresent()) {
                viaRepository.save(viaDocument);
            }
        });
    }
}
