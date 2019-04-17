package com.house.exception;

public class UserOperationException extends RuntimeException{

    private static final long serialVersionUID = 1666721337046067238L;

    public UserOperationException(String msg){
        super(msg);
    }
}
