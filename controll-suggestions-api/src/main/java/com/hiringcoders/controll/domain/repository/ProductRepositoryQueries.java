package com.hiringcoders.controll.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hiringcoders.controll.domain.filter.ProductFilter;
import com.hiringcoders.controll.domain.model.Product;

public interface ProductRepositoryQueries {
	
	Page<Product> findUsingFilter(ProductFilter filter, Pageable pageable);

}