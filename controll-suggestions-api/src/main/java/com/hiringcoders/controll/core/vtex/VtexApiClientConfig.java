package com.hiringcoders.controll.core.vtex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hiringcoders.controll.infrastructure.vtex.clients.VtexApiClient;

@Configuration
public class VtexApiClientConfig {

	@Autowired
	private VtexProperties properties;
	
	@Bean
	public VtexApiClient vtexApiClient() {
		return VtexApiClient.builder()
				.vtexAccountName(properties.getApi().getVtexAccountName())
				.vtexEnvironment(properties.getApi().getVtexEnvironment())
				.vtexAppKey(properties.getApi().getVtexAppKey())
				.vtexAppToken(properties.getApi().getVtexAppToken())
				.build();
	}

}
