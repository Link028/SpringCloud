package com.link.common.persist;

public class PersistException extends RuntimeException {

	private static final long serialVersionUID = 3959179884289933322L;

	public PersistException(String message) {
        super(message);
    }

    public PersistException(String message, Throwable cause) {
        super(message, cause);
    }
}