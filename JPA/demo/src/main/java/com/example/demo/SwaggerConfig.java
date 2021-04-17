package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableAutoConfiguration
public class SwaggerConfig {
    public static Contact DEFAULT_CONTACT = new Contact("El oga", "https://swagger.io", "oscar.garcia@mariachi.io");
    public static ApiInfo API_INFO = new ApiInfo(
        "Apis de la tienda de mascotas",
        "Docuemntacion del API",
        "1.0",
        "urn:tos",
        DEFAULT_CONTACT,
        "Apache 2.0",
        "http://www.apache.org/licenses/LICENSE-2.0",
        new ArrayList<>());

    public static Set<String> DEFAULT_PRODUCES = new HashSet<>(Arrays.asList("application/json", "application/xml"));

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                        .apiInfo(API_INFO)
                        .produces(DEFAULT_PRODUCES)
                        .consumes(DEFAULT_PRODUCES)
                        .select()
                        .apis(RequestHandlerSelectors.any())
                        .paths(PathSelectors.any()).build();
    }
}
