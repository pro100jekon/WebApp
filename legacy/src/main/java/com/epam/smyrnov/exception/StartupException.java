package com.epam.smyrnov.exception;

public class StartupException extends RuntimeException {
	public StartupException(String message) {
		super(message);
	}

	public StartupException(String message, Throwable cause) {
		super(message, cause);
	}
}
