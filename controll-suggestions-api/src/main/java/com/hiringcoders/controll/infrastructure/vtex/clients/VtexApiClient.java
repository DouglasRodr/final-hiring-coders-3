package com.hiringcoders.controll.infrastructure.vtex.clients;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;

import com.hiringcoders.controll.infrastructure.vtex.exception.InvalidParametersVtexApiException;
import com.hiringcoders.controll.infrastructure.vtex.model.CommitItemFeedOrderVtex;
import com.hiringcoders.controll.infrastructure.vtex.model.FeedOrderVtex;
import com.hiringcoders.controll.infrastructure.vtex.model.OrderVtex;
import com.hiringcoders.controll.infrastructure.vtex.model.ProductVtex;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class VtexApiClient {
	
	private WebClient webClient;
	
	private ProductVtexApiClient productVtexApiClient;
	
	private OrderVtexApiClient orderVtexApiClient;
	
	private FeedOrderVtexApiClient feedOrderVtexApiClient;
	
	@Builder
	public VtexApiClient(String vtexAccountName, String vtexEnvironment, String vtexAppKey, String vtexAppToken) {
		
		if (!StringUtils.hasText(vtexAccountName)) {
			throw new InvalidParametersVtexApiException("Invalid VTEX Account Name");			
		}
		
		if (!StringUtils.hasText(vtexEnvironment)) {
			throw new InvalidParametersVtexApiException("Invalid VTEX Environment");			
		}
		
		if (!StringUtils.hasText(vtexAppKey)) {
			throw new InvalidParametersVtexApiException("Invalid VTEX APP Key");			
		}
		
		if (!StringUtils.hasText(vtexAppToken)) {
			throw new InvalidParametersVtexApiException("Invalid VTEX APP Token");			
		}
		
		this.webClient = WebClient.builder().baseUrl("https://" + vtexAccountName + "." + vtexEnvironment + ".com.br")
				.defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.defaultHeader("X-VTEX-API-AppKey", vtexAppKey)
				.defaultHeader("X-VTEX-API-AppToken", vtexAppToken)
				.build();
		
		this.productVtexApiClient = new ProductVtexApiClient(webClient);
		this.orderVtexApiClient = new OrderVtexApiClient(webClient);
		this.feedOrderVtexApiClient = new FeedOrderVtexApiClient(webClient);
	}
	
	public ProductVtex getProductById(Long productId) {
		return productVtexApiClient.getById(productId);
	}
	
	public OrderVtex getOrderById(String orderId) {
		return orderVtexApiClient.getByOrderId(orderId);
	}
	
	public List<FeedOrderVtex> getFeedOrder() {
		return feedOrderVtexApiClient.getFeedOrder();
	}
	
	public List<FeedOrderVtex> getFeedOrder(Long maxlot) {
		return feedOrderVtexApiClient.getFeedOrder(maxlot);
	}
	
	public void commitItemFeed(String handle) {
		feedOrderVtexApiClient.commitItemFeed(handle);
	}
	
	public void commitItemFeed(List<String> handles) {
		feedOrderVtexApiClient.commitItemFeed(handles);
	}
	
	public void commitItemFeed(CommitItemFeedOrderVtex commitItemFeedOrderVtex) {
		feedOrderVtexApiClient.commitItemFeed(commitItemFeedOrderVtex);
	}

}