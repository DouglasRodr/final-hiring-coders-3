package com.hiringcoders.controll.infrastructure.vtex.clients;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.hiringcoders.controll.infrastructure.vtex.exception.VtexApiException;
import com.hiringcoders.controll.infrastructure.vtex.model.CommitItemFeedOrderVtex;
import com.hiringcoders.controll.infrastructure.vtex.model.FeedOrderVtex;

import reactor.core.publisher.Mono;

final class FeedOrderVtexApiClient {
	
	private final static String URI = "/api/orders/feed";
	
	private WebClient webClient;

	FeedOrderVtexApiClient(WebClient webClient) {
		this.webClient = webClient;
	}
	
	List<FeedOrderVtex> getFeedOrder() {
		return getFeedOrder(Long.valueOf(10));
	}
	
	List<FeedOrderVtex> getFeedOrder(Long maxlot) {
		var httpMethod = HttpMethod.GET;
		
		var mono = this.webClient
				.method(httpMethod)
			    .uri(uriBuilder -> uriBuilder.path(URI)
			            .queryParam("maxlot", maxlot)
			            .build())
				.retrieve()
	            .onStatus(HttpStatus::isError, response -> {
	            	return Mono.error(new VtexApiException(httpMethod, response.statusCode(), 
	            			response.bodyToMono(String.class).block()));
	            })
				.bodyToMono(new ParameterizedTypeReference<List<FeedOrderVtex>>() {});
		
		return mono.block();		
	}
	
	void commitItemFeed(String handle) {
		var commitItemFeedOrderVtex = new CommitItemFeedOrderVtex();
		commitItemFeedOrderVtex.addHandle(handle);
		commitItemFeed(commitItemFeedOrderVtex);
	}
	
	void commitItemFeed(List<String> handles) {
		var commitItemFeedOrderVtex = new CommitItemFeedOrderVtex();
		commitItemFeedOrderVtex.addHandles(handles);
		commitItemFeed(commitItemFeedOrderVtex);		
	}
	
	void commitItemFeed(CommitItemFeedOrderVtex commitItemFeedOrderVtex) {
		var httpMethod = HttpMethod.POST;
		
		var mono = this.webClient
				.method(httpMethod)
	            .uri(URI)
	            .body(BodyInserters.fromValue(commitItemFeedOrderVtex))
	            .retrieve()
	            .onStatus(HttpStatus::isError, response -> {
	            	return Mono.error(new VtexApiException(httpMethod, response.statusCode(), 
	            			response.bodyToMono(String.class).block()));
	            })
	            .toBodilessEntity();
		
		mono.block();		
	}

}
