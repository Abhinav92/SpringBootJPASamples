package com.example.DeliveryApp.AppPOC.ExceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class PostRequestException extends RuntimeException {
	private static final long serialVersionUID = -977639722561089945L;

	public PostRequestException() {
	}

}
