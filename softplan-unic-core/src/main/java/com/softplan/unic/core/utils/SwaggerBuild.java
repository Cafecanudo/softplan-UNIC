package com.softplan.unic.core.utils;

import com.google.common.base.Predicates;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@NoArgsConstructor
public class SwaggerBuild {

    @Getter
    private String title;

    @Getter
    private String description;

    @Getter
    private String version;

    public static SwaggerBuild build(){
        return new SwaggerBuild();
    }

    public SwaggerBuild title(String title) {
        this.title = title;
        return this;
    }

    public SwaggerBuild description(String description) {
        this.description = description;
        return this;
    }

    public SwaggerBuild version(String version) {
        this.version = version;
        return this;
    }

    @Bean
    public Docket builder() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .termsOfServiceUrl("https://www.softplan.com.br")
                .contact(createContact())
                .license("Apache License Version 2.0")
                .licenseUrl("https://github.com/Cafecanudo/softplan-UNIC/")
                .version(version)
                .build();
    }

    private Contact createContact() {
        return new Contact("Wellton S. Barros", "https://www.linkedin.com/in/wellton-barros/", "cafecanudo@gmail.com");
    }

}
