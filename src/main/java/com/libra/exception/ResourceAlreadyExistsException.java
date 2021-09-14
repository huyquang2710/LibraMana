package com.libra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class ResourceAlreadyExistsException extends Exception{
	private static final long serialVersionUID = 1L;


    public ResourceAlreadyExistsException() {}

    public ResourceAlreadyExistsException(String message) {
        super(message);
    }
}
