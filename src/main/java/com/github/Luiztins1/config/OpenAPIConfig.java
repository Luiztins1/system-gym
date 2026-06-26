package com.github.Luiztins1.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("🏋️‍♂️ System Gym API")
                        .version("1.0")
                        .description("API RESTful para gerenciamento completo de academias, matrículas e planos.")
                        .contact(new Contact()
                                .name("Luiz Gabriel Martins")
                                .url("https://github.com/Luiztins1")));
    }
}