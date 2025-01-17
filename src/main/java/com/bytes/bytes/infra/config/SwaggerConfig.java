package com.bytes.bytes.infra.config;

import com.bytes.bytes.exceptions.ErrorMessageResponse;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("Bytes").description("API de autoatendimento de fast food ")
                        .version("1"))
                .components(new Components()
                        .addSecuritySchemes("jwt_auth", createSecurityScheme())
                        .addResponses("Validation", new ApiResponse()
                                .description("Erro de validação")
                                .content(new Content()
                                        .addMediaType("application/json",
                                                new MediaType().schema(new ArraySchema()
                                                        .items(new Schema<>().$ref("ErrorValidationField"))
                                                        .example("{\"field\": \"campo\", \"message\": \"Valor inválido\"}")))))
                        .addResponses("Forbidden", new ApiResponse()
                                .description("Sem permissão de acesso"))
                        .addResponses("ForbiddenAdmin", new ApiResponse()
                                .description("Sem permissão de acesso ou usuário não é administrador"))
                        .addResponses("BusinessError", new ApiResponse()
                                .description("Erro de negócio")
                                .content(new Content()
                                        .addMediaType("application/json",
                                                new MediaType().schema(new Schema<ErrorMessageResponse>())
                                                        .example("{\"message\": \"Um recurso já foi cadastro com esses dados\"}"))))
                        .addResponses("NotFoundResource", new ApiResponse()
                                .description("Recurso não encontrado")
                                .content(new Content()
                                        .addMediaType("application/json",
                                                new MediaType().schema(new Schema<ErrorMessageResponse>())
                                                        .example("{\"message\": \"Recurso não encontrado\"}")))));
    }

    private SecurityScheme createSecurityScheme() {
        return new SecurityScheme().name("jwt_auth").type(SecurityScheme.Type.HTTP).scheme("bearer")
                .bearerFormat("JWT");
    }

}
