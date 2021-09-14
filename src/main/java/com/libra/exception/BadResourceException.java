package com.libra.exception;

import java.util.ArrayList;
import java.util.List;

public class BadResourceException extends Exception{
	 private static final long serialVersionUID = 2958233535819021682L;

	 List<String> errorMessages = new ArrayList<>();

	    public BadResourceException() {
	    }

	    public BadResourceException(String message) { super(message); }

	    public void addErrorMessage(String message) {
	        errorMessages.add(message);
	    }
}