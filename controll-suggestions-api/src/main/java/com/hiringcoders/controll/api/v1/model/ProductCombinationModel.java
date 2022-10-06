package com.hiringcoders.controll.api.v1.model;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCombinationModel {
	
	@Schema(example = "1")
	private Long productId;
	
	@Schema(example = "Produto de Testes")
	private String productName;
	
	@Schema(example = "22.00")
	private BigDecimal productQuantitySold;
	
	@Schema(example = "2")
	private Long combinedProductId;
	
	@Schema(example = "Produto Sugerido de Testes")
	private String combinedProductName;
	
	@Schema(example = "25.00")
	private BigDecimal combinedProductQuantitySold;
	
	@Schema(example = "35")
	private Long combinationCount;
	
	@Schema(example = "true")
	private Boolean combinationActive;

}
