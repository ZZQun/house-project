package com.house.dto;

import com.house.pojo.Schedule;

public class ScheduleExecution {

    private boolean flag;

    private Schedule schedule;

    private String reason;

    //失败
    public ScheduleExecution(boolean flag,String reason){
        this.flag = flag;
        this.reason = reason;
    }

    //成功返回User
    public ScheduleExecution(boolean flag,Schedule schedule){
        this.flag = flag;
        this.schedule = schedule;
    }

    //成功不返回
    public ScheduleExecution(boolean flag){
        this.flag = flag;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
