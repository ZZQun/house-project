package com.house.exception;

public class ScheduleOperationException extends RuntimeException{
    private static final long serialVersionUID = 4365160478471097364L;

    public ScheduleOperationException(String msg){
        super(msg);
    }
}
