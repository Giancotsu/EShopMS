package com.eshop.eshop.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                    name = "Gianfranco Larocca",
                    email = "gianfrancolarocca4j@gmail.com",
                    url = "https://github.com/Giancotsu"
                ),
                description = "Eshop microservices (all my knowledge)",
                title = "OpenApi - Eshop",
                version = "1.0",
                license = @License(
                        name = "license-name",
                        url = "https://license-site.com"
                ),
                termsOfService = "Terms of service"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:9000"
                ),
                @Server(
                        description = "Prod ENV",
                        url = "https://example.com"
                )
        }
)
public class OpenApiConfig {
}
