package com.hiringcoders.controll.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
public class OrderSaleItem implements Serializable {

	private static final long serialVersionUID = 1L;	

	@EqualsAndHashCode.Include
	@Id
	private String id;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private OrderSale orderSale;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Product product;
	
	@Column(nullable = false)
	private BigDecimal quantity;
	
	@Column(nullable = false)
	private Boolean isGift;
	
}
