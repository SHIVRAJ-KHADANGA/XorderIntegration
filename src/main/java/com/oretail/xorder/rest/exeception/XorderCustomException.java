package com.oretail.xorder.rest.exeception;

public class XorderCustomException extends RuntimeException {
	public XorderCustomException() {
	}

	public XorderCustomException(String message) {
		super(message);
	}

	public XorderCustomException(Throwable cause) {
		super(cause);
	}

	public XorderCustomException(String message, Throwable cause) {
		super(message, cause);
	}

	public XorderCustomException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
