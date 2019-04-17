package com.house.dto;

import com.house.pojo.UserList;

public class UserExecution {

    private boolean flag;

    private UserList userList;

    private String reason;

    //失败
    public UserExecution(boolean flag,String reason){
        this.flag = flag;
        this.reason = reason;
    }

    //成功返回User
    public UserExecution(boolean flag,UserList userList){
        this.flag = flag;
        this.userList = userList;
    }

    //成功不返回
    public UserExecution(boolean flag){
        this.flag = flag;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public UserList getUserList() {
        return userList;
    }

    public void setUserList(UserList userList) {
        this.userList = userList;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}
