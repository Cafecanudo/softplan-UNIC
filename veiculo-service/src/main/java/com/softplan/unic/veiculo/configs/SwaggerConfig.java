package com.softplan.unic.veiculo.configs;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Veículo API")
                .description("API Servico para efetuar operacoes referente a veículo")
                .termsOfServiceUrl("https://cora.creativedrive.com")
                .contact(createContact())
                .license("Apache License Version 2.0")
                .licenseUrl("https://github.com/Cafecanudo/softplan-UNIC/")
                .version("0.0.1-SNAPSHOT")
                .build();
    }

    private Contact createContact() {
        return new Contact("Wellton S. Barros", "https://github.com/Cafecanudo", "cafecanudo@gmail.com");
    }

}
