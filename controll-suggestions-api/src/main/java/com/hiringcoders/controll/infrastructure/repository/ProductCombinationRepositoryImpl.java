package com.hiringcoders.controll.infrastructure.repository;

import static com.hiringcoders.controll.infrastructure.repository.specification.ProductCombinationSpecification.withProductIdEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.hiringcoders.controll.domain.model.ProductCombination;
import com.hiringcoders.controll.domain.repository.ProductCombinationRepository;
import com.hiringcoders.controll.domain.repository.ProductCombinationRepositoryQueries;

@Repository
public class ProductCombinationRepositoryImpl implements ProductCombinationRepositoryQueries {
	
	@Autowired
	@Lazy
	private ProductCombinationRepository productCombinationRepository;

	@Override
	public Page<ProductCombination> findByProductId(Long productId, Pageable pageable) {
		
		if (productId == null) {
			throw new IllegalArgumentException("Product Id deve ser informado");
		}
		
		return productCombinationRepository.findAll(withProductIdEquals(productId), pageable);
	}

}
