package com.hiringcoders.controll.api.v1.openapi.controller;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.hiringcoders.controll.api.v1.model.ProductModel;
import com.hiringcoders.controll.api.v1.openapi.model.ProductsModelOpenApi;
import com.hiringcoders.controll.core.springdoc.PageableParameter;
import com.hiringcoders.controll.domain.filter.ProductFilter;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Produtos")
public interface ProductControllerOpenApi {
	
	@PageableParameter
	@Operation(summary = "Listagem Paginada de Produtos", responses = {
			@ApiResponse(responseCode = "200", 
					content = @Content(schema = @Schema(implementation = ProductsModelOpenApi.class))
			)}
	)
	Page<ProductModel> listProducts(
			@ParameterObject ProductFilter filter, 
			@Parameter(hidden = true) Pageable pageable);
	
	@Operation(summary = "Busca um Produto por um Id", responses = {
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "404", description = "Produto não encontrado",
					content = @Content(schema = @Schema(ref = "ProblemInfo"))
			)
	})
	ProductModel findById(
			@Parameter(description = "Id de um Produto", example = "1", required = true) Long productId);

}
