package com.hiringcoders.controll.domain.model;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
public class OrderSale implements Serializable {

	private static final long serialVersionUID = 1L;	
	
	@EqualsAndHashCode.Include
	@Id
	private String id;
	
	@Column(nullable = false)
	private OffsetDateTime creationDate;
	
	@OneToMany(mappedBy = "orderSale", cascade = CascadeType.ALL)
	private List<OrderSaleItem> items;
	
	public OrderSale() {
		this.items = new ArrayList<OrderSaleItem>();
	}

	public void addItem(OrderSaleItem orderSaleItem) {
		orderSaleItem.setOrderSale(this);		
		items.add(orderSaleItem);		
	}

}
