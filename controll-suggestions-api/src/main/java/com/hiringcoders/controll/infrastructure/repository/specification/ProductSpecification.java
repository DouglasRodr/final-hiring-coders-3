package com.hiringcoders.controll.infrastructure.repository.specification;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import org.flywaydb.core.internal.util.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.hiringcoders.controll.domain.filter.ProductFilter;
import com.hiringcoders.controll.domain.model.Product;

public class ProductSpecification {

	public static Specification<Product> usingFilter(ProductFilter filter) {
		return (root, query, builder) -> {

			var predicates = new ArrayList<Predicate>();

			if (filter != null) {
				if (filter.getId() != null) {
					predicates.add(builder.equal(root.get("id"), filter.getId()));
				}

				if (StringUtils.hasText(filter.getName())) {
					predicates.add(builder.like(builder.lower(root.get("name")), "%" + filter.getName().toLowerCase() + "%"));
				}

				if (filter.getActive() != null) {
					if (filter.getActive()) {
						predicates.add(builder.isTrue(root.get("active")));
					} else {
						predicates.add(builder.or(builder.isFalse(root.get("active")), 
								builder.isNull(root.get("active"))));
					}
				}
			}

			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}

}
