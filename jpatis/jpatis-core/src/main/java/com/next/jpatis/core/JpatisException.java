package com.next.jpatis.core;

public class JpatisException extends RuntimeException{

	private static final long serialVersionUID = -4027408941852862283L;
    public JpatisException() {
        super();
    }

    public JpatisException(String message) {
        super(message);
    }

    public JpatisException(String message, Throwable cause) {
        super(message, cause);
    }


    public JpatisException(Throwable cause) {
        super(cause);
    }

    protected JpatisException(String message, Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
