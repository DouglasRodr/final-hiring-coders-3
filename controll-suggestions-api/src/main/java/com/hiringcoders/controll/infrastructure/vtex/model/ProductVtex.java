package com.hiringcoders.controll.infrastructure.vtex.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class ProductVtex implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("Name")
	private String name;
	
	@JsonProperty("IsVisible")
	private Boolean isVisible;
	
	@JsonProperty("IsActive")
	private Boolean isActive;

}
