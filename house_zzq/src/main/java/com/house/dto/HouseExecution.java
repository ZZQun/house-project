package com.house.dto;

import com.house.pojo.HouseList;

public class HouseExecution {

    private boolean flag;

    private HouseList houseList;

    private String reason;

    //失败
    public HouseExecution(boolean flag,String reason){
        this.flag = flag;
        this.reason = reason;
    }

    //成功返回User
    public HouseExecution(boolean flag,HouseList houseList){
        this.flag = flag;
        this.houseList = houseList;
    }

    //成功不返回
    public HouseExecution(boolean flag){
        this.flag = flag;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public HouseList getHouseList() {
        return houseList;
    }

    public void setHouseList(HouseList houseList) {
        this.houseList = houseList;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
