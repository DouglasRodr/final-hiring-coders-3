package com.hiringcoders.controll.api.v1.openapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.hiringcoders.controll.api.v1.model.ProductSummaryModel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Sugestões")
public interface ProductSuggestionControllerOpenApi {
	
	@Operation(summary = "Lista Combinações Ativas como Sugestão para um Produto", responses = {
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "404", description = "Produto não encontrado",
					content = @Content(schema = @Schema(ref = "ProblemInfo"))
			)
	})
	ResponseEntity<List<ProductSummaryModel>> listSuggestionsFromProduct(
			@Parameter(description = "Id de um Produto", example = "1", required = true) Long productId);

}