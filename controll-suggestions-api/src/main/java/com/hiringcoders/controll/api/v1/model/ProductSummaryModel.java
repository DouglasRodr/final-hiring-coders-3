package com.hiringcoders.controll.api.v1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductSummaryModel {

	@Schema(example = "2")
	private Long id;
	
	@Schema(example = "Produto Sugerido de Testes")
	private String name;
	
}
