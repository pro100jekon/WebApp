package com.epam.smyrnov.exception;

public class NoSuchElementException extends RuntimeException {

	public NoSuchElementException() {
		super();
	}

	public NoSuchElementException(String message) {
		super(message);
	}

	public NoSuchElementException(String message, Throwable cause) {
		super(message, cause);
	}
}
