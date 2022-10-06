package com.hiringcoders.controll.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
public class ProductCombination implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EqualsAndHashCode.Include
	@EmbeddedId
	private ProductCombinationId id;
	
	@ManyToOne
	@JoinColumn(insertable = false, updatable = false, nullable = false)
	private Product product;
	
	@ManyToOne
	@JoinColumn(insertable = false, updatable = false, nullable = false)
	private Product combinedProduct;
	
	@Column(nullable = false)
	private Long count;
	
	@Column(nullable = false)
	private Boolean active;

	public ProductCombination(Product product, Product combinedProduct) {
		this.product = product;
		this.combinedProduct = combinedProduct;
		this.id = new ProductCombinationId(product.getId(), combinedProduct.getId());
		this.count = Long.valueOf(0);
		this.active = false;
	}

	public void incrementCount() {
		this.count += 1;		
	}
	
}
