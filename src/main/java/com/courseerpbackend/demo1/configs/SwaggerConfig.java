package com.courseerpbackend.demo1.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI myOpenAPI() {

        Contact contact = new Contact();
        contact.setEmail("agayevmirsah@gmail.com");
        contact.setName("miri");

        Info info = new Info()
                .title("Course ERP API")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to manage tutorials.");

        return new OpenAPI().info(info);
//                .addSecurityItem(
//                new SecurityRequirement().addList("Authorization")
//        );
    }
}
