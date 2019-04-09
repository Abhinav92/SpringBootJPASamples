package com.example.DeliveryApp.AppPOC.ExceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ReportAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = -7277038601802015695L;

	public ReportAlreadyExistsException() {
	}

}
