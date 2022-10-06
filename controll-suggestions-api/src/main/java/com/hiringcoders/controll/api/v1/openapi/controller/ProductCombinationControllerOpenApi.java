package com.hiringcoders.controll.api.v1.openapi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.hiringcoders.controll.api.v1.model.ProductCombinationModel;
import com.hiringcoders.controll.api.v1.openapi.model.ProductsCombinationModelOpenApi;
import com.hiringcoders.controll.core.springdoc.PageableParameter;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Produtos")
public interface ProductCombinationControllerOpenApi {
	
	@PageableParameter
	@Operation(summary = "Listagem Paginada de Combinações Existentes para um Produto", responses = {
			@ApiResponse(responseCode = "200", 
					content = @Content(schema = @Schema(implementation = ProductsCombinationModelOpenApi.class))
			)
	})
	Page<ProductCombinationModel> listCombinationsFromProduct(
			@Parameter(description = "Id de um Produto", example = "1", required = true) Long productId, 
			@Parameter(hidden = true) Pageable pageable);

	@Operation(summary = "Ativar Sugestão da Combinação de 2 Produtos", responses = {
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "404", description = "Produto não encontrado",
					content = @Content(schema = @Schema(ref = "ProblemInfo"))
			)
	})
	ResponseEntity<Void> activateCombination(
			@Parameter(description = "Id de um Produto", example = "1", required = true) Long productId, 
			@Parameter(description = "Id do Produto Combinado", example = "1", required = true) Long combinedProductId);
	
	@Operation(summary = "Desativar Sugestão da Combinação de 2 Produtos", responses = {
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "404", description = "Produto não encontrado",
					content = @Content(schema = @Schema(ref = "ProblemInfo"))
			)
	})
	ResponseEntity<Void> disableCombination(
			@Parameter(description = "Id de um Produto", example = "1", required = true) Long productId, 
			@Parameter(description = "Id do Produto Combinado", example = "1", required = true) Long combinedProductId);

}