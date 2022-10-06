package com.hiringcoders.controll.core.springdoc;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springdoc.core.SpringDocUtils;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;

import com.hiringcoders.controll.api.exceptionhandler.Problem;
import com.hiringcoders.controll.api.v1.openapi.model.PagedModelOpenApi;

import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.tags.Tag;

@Configuration
@SecurityScheme(name = "security_auth",
	type = SecuritySchemeType.OAUTH2,
	flows = @OAuthFlows(clientCredentials = @OAuthFlow(
		tokenUrl = "/oauth/token",
        scopes = {
                @OAuthScope(name = "ADMIN", description = "Admin Scope"),
                @OAuthScope(name = "STORE", description = "Store Scope")
        }
)))
public class SpringDocConfig {
	
    private static final String badRequestResponse = "BadRequestResponse";
    private static final String forbiddenResponse = "ForbiddenResponse";
    private static final String notAcceptableResponse = "NotAcceptableResponse";
    private static final String internalServerErrorResponse = "InternalServerErrorResponse";

	@Bean
	public OpenAPI hiringCoinsOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("Controll Suggestions API")
						.description("API de Sugestões de Produtos - Desafio Final Hiring Coders #3 Grupo Controll")
						.contact(new Contact()
								.name("Douglas Rodrigues")
								.email("wandersondouglasr@gmail.com"))
						.version("v1.0.0"))
				.tags(Arrays.asList(
                        new Tag().name("Produtos").description("Produtos e Combinações de Produtos"),
                        new Tag().name("Sugestões").description("Sugestões de Produtos")
                )).components(new Components()
                        .schemas(gerarSchemas())
                        .responses(gerarResponses())
                );
	}
	
    @Bean
    public OpenApiCustomiser openApiCustomiser() {
		SpringDocUtils.getConfig().replaceWithClass(Page.class, PagedModelOpenApi.class);
        return openApi -> {
            openApi.getPaths()
                    .values()
                    .stream()
                    .flatMap(pathItem -> pathItem.readOperations().stream())
                    .forEach(operation -> {
                        ApiResponses responses = operation.getResponses();

                        responses.addApiResponse("400", new ApiResponse().$ref(badRequestResponse));
                        responses.addApiResponse("403", new ApiResponse().$ref(forbiddenResponse));
                        responses.addApiResponse("406", new ApiResponse().$ref(notAcceptableResponse));
                        responses.addApiResponse("500", new ApiResponse().$ref(internalServerErrorResponse));
                    });
        };
    }

    private Map<String, Schema> gerarSchemas() {
        final Map<String, Schema> schemaMap = new HashMap<>();

        Map<String, Schema> problemSchema = ModelConverters.getInstance().read(Problem.class);

        schemaMap.putAll(problemSchema);

        return schemaMap;
    }
    
    private Map<String, ApiResponse> gerarResponses() {
        final Map<String, ApiResponse> apiResponseMap = new HashMap<>();

        Content content = new Content()
                .addMediaType(APPLICATION_JSON_VALUE,
                        new MediaType().schema(new Schema<Problem>().$ref("ProblemInfo")));

        apiResponseMap.put(badRequestResponse, new ApiResponse()
                .description("Requisição inválida")
                .content(content));
        
        apiResponseMap.put(forbiddenResponse, new ApiResponse()
                .description("Acesso Negado")
                .content(content));

        apiResponseMap.put(notAcceptableResponse, new ApiResponse()
                .description("Recurso não possui representação que poderia ser aceita pelo consumidor")
                .content(content));

        apiResponseMap.put(internalServerErrorResponse, new ApiResponse()
                .description("Erro interno no servidor")
                .content(content));

        return apiResponseMap;
    }
    
}
