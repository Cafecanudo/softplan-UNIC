package com.softplan.unic.produto;

import com.softplan.unic.produto.documents.ProdutoDocument;
import com.softplan.unic.produto.repositories.ProdutoRepository;
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
public class ProdutoServiceApplication implements CommandLineRunner {

    @Autowired
    private ProdutoRepository produtoRepository;

    public static void main(String[] args) {
        SpringApplication.run(ProdutoServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<ProdutoDocument> list = new ArrayList<>();
        list.add(ProdutoDocument.builder().nome("Bloco de Concreto Estrutural Vazado 19x14x39cm Blojaf").peso(1.0).valor(2.99)
                .image("https://cdn.leroymerlin.com.br/products/bloco_de_concreto_estrutural_vazado_19x14x39cm_blojaf_87707571_0001.jpg_300x300.jpg").build());

        list.add(ProdutoDocument.builder().nome("Bloco Cerâmico Vedação 11,5x14x24cm Jad").peso(1.0).valor(0.81)
                .image("https://cdn.leroymerlin.com.br/products/bloco_ceramico_vedacao_11,5x14x24cm_85394400_7b5c_600x600.jpg").build());

        list.add(ProdutoDocument.builder().nome("Telha de PVC Cumeeira Central Colonial 56x90cm Axton").peso(2.0).valor(43.90)
                .image("https://cdn.leroymerlin.com.br/products/telha_de_pvc_cumeeira_central_colonial_ceramica_56x90cm_88024475_a353_600x600.jpg").build());

        list.add(ProdutoDocument.builder().nome("Telha de Cerâmica Romana Resinada Vermelha 23,3cm Cerâmica Barrobello").peso(1.08).valor(1.25)
                .image("https://cdn.leroymerlin.com.br/products/telha_ceramica_romana_resinada_16_pcs_m2_23_30x40cm_88431091_0001_600x600.jpg").build());

        list.add(ProdutoDocument.builder().nome("Cimento CP-II Cinza 1Kg Fortaleza").peso(1.0).valor(2.49)
                .image("https://cdn.leroymerlin.com.br/products/cimento_cinza_1kg_fortaleza_87519103_0001_600x600.jpg").build());

        list.add(ProdutoDocument.builder().nome("Cimento CP-V-ARI-RS 40Kg Cauê Estrutural").peso(40.0).valor(18.59)
                .image("https://cdn.leroymerlin.com.br/products/cimento_cpv_ari_rs_40kg_caue_estrutural__87089625_0001.jpg_600x600.jpg").build());

        list.add(ProdutoDocument.builder().nome("Argamassa Piso sobre Piso Interno e Externo Branco 20kg Axton").peso(20.0).valor(44.90)
                .image("https://cdn.leroymerlin.com.br/products/argamassa_piso_sobre_piso_interno_20_kg_axton__89388635_490d_600x600.jpg").build());

        list.add(ProdutoDocument.builder().nome("Argamassa Bloco de Vidro Interno e Externo Branco 20kg Votoran").peso(20.0).valor(37.90)
                .image("https://cdn.leroymerlin.com.br/products/argamassa_bloco_de_vidro_branca_20kg_votomassa_87636381_efcf_600x600.jpg").build());

        list.stream().forEach(produto -> {
            if (!produtoRepository.findByNomeIgnoreCase(produto.getNome()).isPresent()) {
                produtoRepository.save(produto);
            }
        });
    }
}
