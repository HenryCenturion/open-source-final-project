package com.dtaquito_backend.dtaquito_backend.shared.infrastructure.documentation.openapi.configuration;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI dtaquitoOpenApi() {

        var openApi = new OpenAPI();

        openApi.info(new io.swagger.v3.oas.models.info.Info())
                .info(new Info()
                        .title("Dtaquito API")
                        .description("Dtaquito REST API documentation")
                        .version("v1.0.0")
                        .license(new License().name("Apache 2.0")
                                .url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation().
                        description("Dtaquito wiki Documentation")
                        .url("https://dtaquito.wiki.github.io/docs"));


        return openApi;
    }
}
