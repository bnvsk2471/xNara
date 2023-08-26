package com.xNARA.Exception;

public class XNaraException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public XNaraException(String message) {
        super(message);
    }

    public XNaraException(String message, Throwable cause) {
        super(message, cause);
    }

}
