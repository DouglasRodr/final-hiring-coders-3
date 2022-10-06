package com.hiringcoders.controll.infrastructure.vtex.clients;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;

import com.hiringcoders.controll.infrastructure.vtex.exception.VtexApiException;
import com.hiringcoders.controll.infrastructure.vtex.model.OrderVtex;

import reactor.core.publisher.Mono;

final class OrderVtexApiClient {
	
	private final static String URI = "/api/oms/pvt/orders";
	
	private WebClient webClient;
	
	OrderVtexApiClient(WebClient webClient) {
		this.webClient = webClient;
	}
	
	OrderVtex getByOrderId(String orderId) {
		var httpMethod = HttpMethod.GET;
		
		var mono = this.webClient
				.method(httpMethod)
	            .uri(URI + "/" + orderId)
	            .retrieve()
	            .onStatus(HttpStatus::isError, response -> {
	            	return Mono.error(new VtexApiException(httpMethod, response.statusCode(), 
	            			response.bodyToMono(String.class).block()));
	            })
	            .bodyToMono(OrderVtex.class);
		
		return mono.block();
	}

}
