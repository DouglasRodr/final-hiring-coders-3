package com.hiringcoders.controll.api.v1.model;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductModel {
	
	@Schema(example = "1")
	private Long id;
	
	@Schema(example = "Produto de Testes")
	private String name;
	
	@Schema(example = "true")
	private Boolean active;
	
	@Schema(example = "22.00")
	private BigDecimal quantitySold;
	
	@Schema(example = "10")
	private Long totalCombinations;

}