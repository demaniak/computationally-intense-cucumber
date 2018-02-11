/*
 * Project: cic
 * File:    CicNotFoundException.java
 * Created: Feb 11, 2018
 * 
 * Copyright (C) AfriGIS 2018
 */
package coetzee.hendrik.cic.err;

/**
 * Raised when we are unable to locate the desired Cic.
 * @author <a href="mailto:hendrikc@afrigis.co.za?subject=CicNotFoundException.java">hendrik</a>
 * 
 */
public class CicNotFoundException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 7804617397851045561L;

    public CicNotFoundException() {
        super();
        // TODO Auto-generated constructor stub
    }

    public CicNotFoundException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

    public CicNotFoundException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public CicNotFoundException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public CicNotFoundException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

}
