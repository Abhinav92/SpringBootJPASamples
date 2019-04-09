package com.example.DeliveryApp.AppPOC.ExceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReleaseNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5652842887386460892L;

	public ReleaseNotFoundException() {
	}

}
