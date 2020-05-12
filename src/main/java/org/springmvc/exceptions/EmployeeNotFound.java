package org.springmvc.exceptions;

public class EmployeeNotFound extends Exception {

	public EmployeeNotFound(String errorMessage) {
        super(errorMessage);
        
    }
}
