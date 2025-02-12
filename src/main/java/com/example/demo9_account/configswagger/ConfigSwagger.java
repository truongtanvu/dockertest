package com.example.demo9_account.configswagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ConfigSwagger {
    @Bean
    public OpenAPI openAPI() {
        Server prodServer = new Server();
        prodServer.setUrl("");
        prodServer.setDescription("Server URL in Production environment");

        Contact contact = new Contact();
        contact.setEmail("tanvu114@gmail.com");
        contact.setName("Truong Tan Vu");
        contact.setUrl("https://www.truongtanvu.com");

        License mitLicense = new License().name("MIT License").url("https://www.truongtanvu.com");

        Info info = new Info()
                .title("Service API")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to manage tutorials.")
                .license(mitLicense);
        return new OpenAPI().info(info).servers(List.of(prodServer));
    }
}
