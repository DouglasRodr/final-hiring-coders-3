package com.hiringcoders.controll.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hiringcoders.controll.domain.exception.ProductCombinationNotFoundException;
import com.hiringcoders.controll.domain.model.ProductCombination;
import com.hiringcoders.controll.domain.repository.ProductCombinationRepository;

@Service
public class ProductCombinationRegistrationService {

	private ProductRegistrationService productRegistration;

	private ProductCombinationRepository productCombinationRepository;

	@Autowired
	public ProductCombinationRegistrationService(ProductRegistrationService productRegistration,
			ProductCombinationRepository productCombinationRepository) {
		this.productRegistration = productRegistration;
		this.productCombinationRepository = productCombinationRepository;
	}

	public ProductCombination findProductCombination(Long productId, Long combinedProductId) {
		return productCombinationRepository.findByProductIdAndCombinedProductId(productId, combinedProductId)
				.orElseThrow(() -> new ProductCombinationNotFoundException(productId, combinedProductId));
	}

	@Transactional
	public void activateCombination(Long productId, Long combinedProductId) {
		ProductCombination productCombination = getProductCombination(productId, combinedProductId);
		productCombination.setActive(true);
		productCombinationRepository.saveAndFlush(productCombination);
	}

	@Transactional
	public void disableCombination(Long productId, Long combinedProductId) {
		ProductCombination productCombination = getProductCombination(productId, combinedProductId);
		productCombination.setActive(false);
		productCombinationRepository.saveAndFlush(productCombination);
	}
	
	private ProductCombination getProductCombination(Long productId, Long combinedProductId) {
		productRegistration.findProductById(productId);
		productRegistration.findProductById(combinedProductId);
		return findProductCombination(productId, combinedProductId);
	}

}
