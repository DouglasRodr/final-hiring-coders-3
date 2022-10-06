package com.hiringcoders.controll.infrastructure.vtex.clients;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;

import com.hiringcoders.controll.infrastructure.vtex.exception.VtexApiException;
import com.hiringcoders.controll.infrastructure.vtex.model.ProductVtex;

import reactor.core.publisher.Mono;

final class ProductVtexApiClient {
	
	private final static String URI = "/api/catalog/pvt/product";
	
	private WebClient webClient;
	
	ProductVtexApiClient(WebClient webClient) {
		this.webClient = webClient;
	}
	
	ProductVtex getById(Long productId) {
		var httpMethod = HttpMethod.GET;
		
		var mono = this.webClient
				.method(httpMethod)
	            .uri(URI + "/" + productId)
	            .retrieve()
	            .onStatus(HttpStatus::isError, response -> {
	            	return Mono.error(new VtexApiException(httpMethod, response.statusCode(), 
	            			response.bodyToMono(String.class).block()));
	            })
	            .bodyToMono(ProductVtex.class);
		
		return mono.block();
	}

}
