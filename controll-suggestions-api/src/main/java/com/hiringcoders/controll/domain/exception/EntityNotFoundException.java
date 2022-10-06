package com.hiringcoders.controll.domain.exception;

public abstract class EntityNotFoundException extends DomainException {

	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(String mensagem) {
		super(mensagem);
	}
	
}