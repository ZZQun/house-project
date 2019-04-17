package com.house.exception;

public class SolveOperationException extends RuntimeException {
    private static final long serialVersionUID = -237710301962969420L;

    public SolveOperationException(String msg){
        super(msg);
    }
}
