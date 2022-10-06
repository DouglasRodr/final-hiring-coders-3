package com.hiringcoders.controll.domain.exception;

public class ProductNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public ProductNotFoundException(Long id) {
		super(String.format("Não existe um Produto com Id %d", id));
	}

}