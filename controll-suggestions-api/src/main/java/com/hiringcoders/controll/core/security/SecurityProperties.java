package com.hiringcoders.controll.core.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties("security")
public class SecurityProperties {
	
	private String adminId;
	
	private String adminSecret;
	
	private String storeId;
	
	private String storeSecret;
	
	private String jwtSigninKey;

}
