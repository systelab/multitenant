package com.werfen.laboratory.core.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "Authorization",
        type = SecuritySchemeType.HTTP,
        description = "JWT Token Authentication",
        scheme = "bearer"
)
@OpenAPIDefinition(
        info = @Info(
                title = "Multi-Tenant application programming interface (API)",
                version = "v1.0",
                description = "Restful API to manage a Sample Multi-Tenant Application to be used as an example.",
                license = @License(name = "Apache License Version 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0"),
                contact = @Contact(url = "https://github.com/systelab/", name = "Werfen Clinical Software", email = "systelab@werfen.com")
        ))
public class SwaggerConfig {
        @Bean
        public OperationCustomizer customize() {
                return (operation, handlerMethod) -> operation.addParametersItem(
                        new Parameter()
                                .in("header")
                                .required(true)
                                .description("Tenant Id")
                                .name("X-TenantID"));
        }
}