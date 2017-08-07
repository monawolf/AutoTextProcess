package com.cofco.util.exception;

import com.cofco.util.enumclass.ExceptionType;

@SuppressWarnings("serial")
public class UserException extends Exception {
	String errorMessage;

	public UserException(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public UserException(ExceptionType errorType) {
		this.errorMessage = errorType.getName();
	}

	public String toString() {
		return errorMessage;
	}

	public String getMessage() {
		return errorMessage;
	}
}
