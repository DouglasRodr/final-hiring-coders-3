package com.hiringcoders.controll.infrastructure.repository;

import static com.hiringcoders.controll.infrastructure.repository.specification.ProductSpecification.usingFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.hiringcoders.controll.domain.filter.ProductFilter;
import com.hiringcoders.controll.domain.model.Product;
import com.hiringcoders.controll.domain.repository.ProductRepository;
import com.hiringcoders.controll.domain.repository.ProductRepositoryQueries;

@Repository
public class ProductRepositoryImpl implements ProductRepositoryQueries {
	
	@Autowired
	@Lazy
	private ProductRepository productRepository;

	@Override
	public Page<Product> findUsingFilter(ProductFilter filter, Pageable pageable) {
		return productRepository.findAll(usingFilter(filter), pageable);
	}

}
