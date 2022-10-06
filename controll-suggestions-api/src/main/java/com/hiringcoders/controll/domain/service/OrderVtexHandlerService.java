package com.hiringcoders.controll.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hiringcoders.controll.domain.model.OrderSale;
import com.hiringcoders.controll.domain.model.OrderSaleItem;
import com.hiringcoders.controll.domain.model.Product;
import com.hiringcoders.controll.domain.repository.OrderSaleRepository;
import com.hiringcoders.controll.domain.repository.ProductRepository;
import com.hiringcoders.controll.infrastructure.vtex.clients.VtexApiClient;
import com.hiringcoders.controll.infrastructure.vtex.model.OrderItemVtex;
import com.hiringcoders.controll.infrastructure.vtex.model.ProductVtex;

@Service
public class OrderVtexHandlerService {

	private VtexApiClient vtexApi;

	private ProductRepository productRepository;

	private ProductRegistrationService productRegistration;

	private OrderSaleRepository orderSaleRepository;

	private ProductCombinationHandlerService productCombinationHandler;

	@Autowired
	public OrderVtexHandlerService(VtexApiClient vtexApi, ProductRepository productRepository,
			ProductRegistrationService productRegistration, OrderSaleRepository orderSaleRepository,
			ProductCombinationHandlerService productCombinationHandler) {
		this.vtexApi = vtexApi;
		this.productRepository = productRepository;
		this.productRegistration = productRegistration;
		this.orderSaleRepository = orderSaleRepository;
		this.productCombinationHandler = productCombinationHandler;
	}

	@Transactional
	public void handleOrder(String orderId) {
		var orderSaleOptional = orderSaleRepository.findById(orderId);

		if (!orderSaleOptional.isPresent()) {
			var orderVtex = vtexApi.getOrderById(orderId);			
			
			var orderSale = new OrderSale();
			orderSale.setId(orderVtex.getOrderId());
			orderSale.setCreationDate(orderVtex.getCreationDate().toOffsetDateTime());

			for (OrderItemVtex orderItemVtex : orderVtex.getItems()) {
				var product = handleProduct(orderItemVtex);

				var orderSaleItem = new OrderSaleItem();
				orderSaleItem.setId(orderItemVtex.getUniqueId());
				orderSaleItem.setProduct(product);
				orderSaleItem.setQuantity(orderItemVtex.getQuantity());
				orderSaleItem.setIsGift(orderItemVtex.getIsGift());

				orderSale.addItem(orderSaleItem);
			}

			orderSaleRepository.saveAndFlush(orderSale);

			productCombinationHandler.handleProductCombinations(orderSale);
		}

	}

	@Transactional
	private Product handleProduct(OrderItemVtex orderItemVtex) {
		ProductVtex productVtex = vtexApi.getProductById(orderItemVtex.getProductId());

		var product = productRepository.findById(orderItemVtex.getProductId()).orElse(new Product());
		product.setId(orderItemVtex.getProductId());
		product.setName(productVtex.getName());
		product.setActive(productVtex.getIsActive());

		if (!orderItemVtex.getIsGift()) {
			product.incrementQuantitySold(orderItemVtex.getQuantity());
		}

		return productRegistration.save(product);
	}

}
