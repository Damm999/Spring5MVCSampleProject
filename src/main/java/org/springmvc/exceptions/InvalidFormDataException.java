package org.springmvc.exceptions;

public class InvalidFormDataException extends Exception{

	public InvalidFormDataException(String errorMessage) {
		super(errorMessage);
	}
}
