package com.hiringcoders.controll.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.hiringcoders.controll.domain.model.ProductCombination;
import com.hiringcoders.controll.domain.model.ProductCombinationId;

@Repository
public interface ProductCombinationRepository extends JpaRepository<ProductCombination, ProductCombinationId>,
		JpaSpecificationExecutor<ProductCombination>, ProductCombinationRepositoryQueries {

	Optional<ProductCombination> findByProductIdAndCombinedProductId(Long productId, Long combinedProductId);
	
	List<ProductCombination> findActiveByProductId(Long productId);

}