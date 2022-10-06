package com.hiringcoders.controll.core.vtex;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties("vtex")
public class VtexProperties {
	
	private Api api = new Api();
	
	private OrderFeed orderFeed = new OrderFeed();
	
	@Getter
	@Setter
	public class Api {
	    
		private String vtexAccountName;
		
		private String vtexEnvironment;
		
		private String vtexAppKey;
		
		private String vtexAppToken;
	    
	}
	
	@Getter
	@Setter
	public class OrderFeed {
		
		private Boolean active = false;
		
	}

}
