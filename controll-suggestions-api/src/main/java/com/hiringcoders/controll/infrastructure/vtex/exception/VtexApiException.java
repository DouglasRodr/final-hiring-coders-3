package com.hiringcoders.controll.infrastructure.vtex.exception;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

public class VtexApiException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public VtexApiException(String message) {
		super(message);
	}

	public VtexApiException(HttpMethod httpMethod, HttpStatus httpStatus, String message) {
		super(httpMethod.toString() + " Response Code: " + httpStatus.value() + " - " + message);
	}

}
