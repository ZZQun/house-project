package com.house.dto;

import com.house.pojo.Solve;

public class SolveExecution {

    private boolean flag;

    private Solve solve;

    private String reason;

    //失败
    public SolveExecution(boolean flag,String reason){
        this.flag = flag;
        this.reason = reason;
    }

    //成功返回User
    public SolveExecution(boolean flag,Solve solve){
        this.flag = flag;
        this.solve = solve;
    }

    //成功不返回
    public SolveExecution(boolean flag){
        this.flag = flag;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Solve getSolve() {
        return solve;
    }

    public void setSolve(Solve solve) {
        this.solve = solve;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
