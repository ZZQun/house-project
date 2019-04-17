package com.house.exception;

public class HouseOperationException extends RuntimeException{

    private static final long serialVersionUID = -2365905813758097384L;

    public HouseOperationException(String msg){
        super(msg);
    }
}
