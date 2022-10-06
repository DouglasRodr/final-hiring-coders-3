package com.hiringcoders.controll.domain.exception;

public class ProductCombinationNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public ProductCombinationNotFoundException(Long productId, Long combinedProductId) {
		super(String.format("Não existe uma Combinação entre o produto de Id %d com o produto de Id %d",
				combinedProductId, productId));
	}

}