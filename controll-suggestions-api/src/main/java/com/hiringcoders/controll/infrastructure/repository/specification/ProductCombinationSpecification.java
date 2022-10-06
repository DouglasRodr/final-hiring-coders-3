package com.hiringcoders.controll.infrastructure.repository.specification;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.hiringcoders.controll.domain.model.ProductCombination;

public class ProductCombinationSpecification {

	public static Specification<ProductCombination> withProductIdEquals(Long productId) {
		return (root, query, builder) -> {

			if (ProductCombination.class.equals(query.getResultType())) {
				fetchProducts(root);
			}

			return builder.and(builder.equal(root.get("product").get("id"), productId));
		};
	}

	private static void fetchProducts(Root<ProductCombination> root) {
		root.fetch("product", JoinType.INNER);
		root.fetch("combinedProduct", JoinType.INNER);
	}

}
