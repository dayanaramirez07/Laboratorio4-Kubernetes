package com.udea.flight;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Api de Gestión de Vuelos")
                        .version("1.0")
                        .description("Documentación de la API de vuelos para gestionar información de vuelos"));
    }

}
