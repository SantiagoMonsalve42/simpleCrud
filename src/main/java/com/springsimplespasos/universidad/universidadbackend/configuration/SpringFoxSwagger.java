package com.springsimplespasos.universidad.universidadbackend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SpringFoxSwagger {

    @Bean
    public Docket getDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.springsimplespasos.universidad.universidadbackend.controlador"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo(){
        return new ApiInfo(
                "REST API FOR UNIVERSITY BUSSINNESS",
                "API TO BE CONSUMED BY A FRONT END FRAMEWORK",
                "V2",
                "very soon implementation of JTW in all end points",
                new Contact("Santiago Monsalve Salinas","www.github.com/SantiagoMonsalve42","santi.monsalve09@gmail.com"),
                "OPEN SOURCE, FEEL FREE TO USE AND IMPROVE IT",
                "OPEN SOURCE",
                Collections.emptyList()
        );
    }


}
