package com.softplan.unic.documentation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableZuulProxy
@SpringBootApplication
public class DocumentationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DocumentationServiceApplication.class, args);
    }
}
