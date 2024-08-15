package com.smanager.sales_management.controller.util;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Sales Management API")
                        .description("Sistema de gestão de vendas")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Pedro H. Kober")
                                .url("http://kober.dev.com.br")
                                .email("pedro.koberstain.dev@gmail.com")
                        )
                )
                .externalDocs(new ExternalDocumentation()
                        .description("Documentação Externa")
                        .url("http://example.com/docs"));
    }
}
