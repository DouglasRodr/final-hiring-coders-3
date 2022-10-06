package com.hiringcoders.controll.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hiringcoders.controll.domain.exception.ProductNotFoundException;
import com.hiringcoders.controll.domain.model.Product;
import com.hiringcoders.controll.domain.repository.ProductRepository;

@Service
public class ProductRegistrationService {
	
	private ProductRepository productRepository;
	
	@Autowired
	public ProductRegistrationService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	public Product findProductById(Long id) {
		return productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException(id));
	}

	@Transactional
	public Product save(Product product) {
		return productRepository.saveAndFlush(product);
	}

}
