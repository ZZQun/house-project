package com.house.dto;

import com.house.pojo.Paid;

public class PaidExecution {

    private boolean flag;

    private Paid paid;

    private String reason;

    //失败
    public PaidExecution(boolean flag,String reason){
        this.flag = flag;
        this.reason = reason;
    }

    //成功返回User
    public PaidExecution(boolean flag,Paid paid){
        this.flag = flag;
        this.paid = paid;
    }

    //成功不返回
    public PaidExecution(boolean flag){
        this.flag = flag;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Paid getPaid() {
        return paid;
    }

    public void setPaid(Paid paid) {
        this.paid = paid;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
