package com.hiringcoders.controll.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Formula;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
		
	@EqualsAndHashCode.Include
	@Id
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private Boolean active;
	
	@Column(nullable = false)
	private BigDecimal quantitySold;	
	
	@Formula("(select count(*) from product_combination c where c.product_id = id)")
	private Long totalCombinations;
	
	public Product() {
		this.quantitySold = BigDecimal.ZERO;
	}
	
	public void incrementQuantitySold(BigDecimal quantitySold) {
		this.quantitySold = this.quantitySold.add(quantitySold);
	}

}