package com.sinosoft.aspect.softphone.soap;

public class ServiceException extends Exception {

	public ServiceException() {
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String message, Throwable cause) {
		super(message);
		this.cause = cause;
	}

	public ServiceException(Throwable cause) {
		super(cause != null ? cause.toString() : null);
		this.cause = cause;
	}

	public Throwable getLinkedCause() {
		return cause;
	}

	Throwable cause;
}
