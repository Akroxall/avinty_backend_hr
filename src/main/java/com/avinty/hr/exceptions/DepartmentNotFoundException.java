package com.avinty.hr.exceptions;

public class DepartmentNotFoundException extends NotFoundException {

    private static final long serialVersionUID = 1L;

    public DepartmentNotFoundException() {
        super();
    }

    public DepartmentNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public DepartmentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DepartmentNotFoundException(String message) {
        super(message);
    }

    public DepartmentNotFoundException(Throwable cause) {
        super(cause);
    }

}
