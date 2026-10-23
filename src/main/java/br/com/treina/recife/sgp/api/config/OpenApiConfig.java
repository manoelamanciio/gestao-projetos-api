package br.com.treina.recife.sgp.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI sgpApiOpenAPI() {
        return new OpenAPI().info(new Info()
                            .title("SGP API")
                            .description("API REST para gestão de tarefas")
                            .version("v1")
                            .contact(new Contact()
                                        .name("Treina Recife")
                                        .email("contato@treinarecife.com.br")
                                    )
                            .license(new License().name("Uso Interno - Treina Recife"))
                        );
    }
    
}
