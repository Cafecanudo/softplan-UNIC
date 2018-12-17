package com.softplan.unic.produto.configs;

import com.softplan.unic.core.utils.SwaggerBuild;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return SwaggerBuild.build().title("Produto API")
                .description("API Servico para efetuar operacoes de produtos")
                .version("0.0.1-SNAPSHOT")
                .builder();
    }
}
