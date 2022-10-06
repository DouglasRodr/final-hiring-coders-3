package com.hiringcoders.controll.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hiringcoders.controll.domain.model.OrderSale;
import com.hiringcoders.controll.domain.model.OrderSaleItem;
import com.hiringcoders.controll.domain.model.ProductCombination;
import com.hiringcoders.controll.domain.repository.ProductCombinationRepository;

@Service
public class ProductCombinationHandlerService {

	private ProductCombinationRepository productCombinationRepository;

	@Autowired
	public ProductCombinationHandlerService(ProductCombinationRepository productCombinationRepository) {
		this.productCombinationRepository = productCombinationRepository;
	}

	@Transactional
	public void handleProductCombinations(OrderSale orderSale) {
		if (orderSale.getItems().size() > 1) {
			List<OrderSaleItem> list1 = new ArrayList<OrderSaleItem>(orderSale.getItems());
			List<OrderSaleItem> list2 = new ArrayList<OrderSaleItem>(orderSale.getItems());

			for (OrderSaleItem item1 : list1) {
				for (OrderSaleItem item2 : list2) {

					if (!item1.getProduct().equals(item2.getProduct()) && !item1.getIsGift() && !item2.getIsGift()) {
						ProductCombination productCombination = productCombinationRepository
								.findByProductIdAndCombinedProductId(item1.getProduct().getId(), item2.getProduct().getId())
								.orElse(new ProductCombination(item1.getProduct(), item2.getProduct()));

						productCombination.incrementCount();
						productCombinationRepository.saveAndFlush(productCombination);
					}

				}
			}

		}
	}

}
