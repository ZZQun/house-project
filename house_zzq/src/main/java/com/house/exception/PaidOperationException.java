package com.house.exception;

public class PaidOperationException extends RuntimeException {
    private static final long serialVersionUID = 3975914359294260251L;

    public PaidOperationException(String msg){
        super(msg);
    }
}
