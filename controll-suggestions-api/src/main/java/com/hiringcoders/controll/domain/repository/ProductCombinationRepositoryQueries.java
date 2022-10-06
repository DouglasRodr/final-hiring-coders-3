package com.hiringcoders.controll.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hiringcoders.controll.domain.model.ProductCombination;

public interface ProductCombinationRepositoryQueries {
	
	Page<ProductCombination> findByProductId(Long productId, Pageable pageable);

}