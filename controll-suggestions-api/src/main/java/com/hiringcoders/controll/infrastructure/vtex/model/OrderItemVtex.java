package com.hiringcoders.controll.infrastructure.vtex.model;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class OrderItemVtex implements Serializable {

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	private String uniqueId;

	private Long id;

	private Long productId;

	private BigDecimal quantity;

	private String name;

	private BigDecimal price;

	private String refId;

	private String measurementUnit;

	private BigDecimal unitMultiplier;

	private Boolean isGift;

}