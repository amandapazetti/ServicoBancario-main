package com.amandaramos.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

    @Configuration
    @EnableSwagger2
    public class SwaggerConfig implements WebMvcConfigurer {

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("swagger-ui.html")
                    .addResourceLocations("classpath:/META-INF/resources/");
            registry.addResourceHandler("/webjars/**")
                    .addResourceLocations("classpath:/META-INF/resources/webjars/");
        }

        @Bean
        public Docket api() {
            String baseUrl = "http://54.85.94.71:9007";
            return new Docket(DocumentationType.SWAGGER_2)
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.amandaramos.controller"))
                    .paths(PathSelectors.any())
                    .build()
                    .apiInfo(metaInfo())
                    .host(baseUrl);
        }

        private ApiInfo metaInfo() {
            return new ApiInfo(
                    "Sistema Bancario",
                    "API REST com Spring Boot de cadastro de Clientes e Transações.",
                    "1.0",
                    "Terms of Service",
                    new Contact("Amanda Pazetti Peres Ramos", "https://www.linkedin.com/in/amanda-pazetti-peres-ramos-2b397b143/",
                            "amanda.ramos@fcamara.com.br"),
                    "Apache License Version 2.0",
                    "https://www.apache.org/licesen.html", new ArrayList<>()
            );
        }
    }


