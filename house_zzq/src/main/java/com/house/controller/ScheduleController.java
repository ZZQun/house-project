package com.house.controller;

import com.house.common.Result;
import com.house.common.StatusCode;
import com.house.dto.ScheduleExecution;
import com.house.pojo.Schedule;
import com.house.service.ScheduleService;
import com.house.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@CrossOrigin
@RequestMapping(value="/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @RequestMapping(value = "/getallschedulelist",method = RequestMethod.GET)
    public Result getAllScheduleList(){
        return new Result(true, StatusCode.SUCCESS,"查找公告信息列表成功",scheduleService.findByCondition());
    }

    @RequestMapping(value = "/getscheduleinsevendays",method = RequestMethod.GET)
    public Result getScheduleInsevenDays(){
        return new Result(true, StatusCode.SUCCESS,"查找公告信息列表成功",scheduleService.findScheduleInSevenDays(DateUtil.dateFormat1(new Date())));
    }

    @RequestMapping(value = "/getschedulelistbycondition",method = RequestMethod.POST)
    public Result getScheduleListByCondition(@RequestBody Schedule schedule){
        return new Result(true, StatusCode.SUCCESS,"按条件查找公告信息列表成功",scheduleService.findByCondition());
    }

    @RequestMapping(value="/addschedule",method = RequestMethod.POST)
    public Result addSchedule(@RequestBody Schedule schedule){
        ScheduleExecution se;
        try{
            se = scheduleService.addSchedule(schedule);
            if(se.isFlag()){
                return new Result(true,StatusCode.SUCCESS,"添加公告信息成功");
            }else {
                return new Result(false,StatusCode.ERROR,"添加公告信息失败：" + se.getReason());
            }
        }catch (Exception e){
            return new Result(false,StatusCode.ERROR,"添加公告信息失败：" + e.toString());
        }
    }

    @RequestMapping(value="/updateschedule",method = RequestMethod.POST)
    public Result updateSchedule(@RequestBody Schedule schedule){
        ScheduleExecution se;
        try{
            se = scheduleService.updateSchedule(schedule);
            if(se.isFlag()){
                return new Result(true,StatusCode.SUCCESS,"修改公告信息成功");
            }else {
                return new Result(false,StatusCode.ERROR,"修改公告信息失败：" + se.getReason());
            }
        }catch (Exception e){
            return new Result(false,StatusCode.ERROR,"修改公告信息失败：" + e.toString());
        }
    }

    @RequestMapping(value="/deleteschedule",method = RequestMethod.DELETE)
    public Result deleteSchedule(@RequestParam("scheduleId")Integer scheduleId){
        ScheduleExecution se;
        try{
            se = scheduleService.deleteSchedule(scheduleId);
            if(se.isFlag()){
                return new Result(true,StatusCode.SUCCESS,"删除公告信息成功");
            }else {
                return new Result(false,StatusCode.ERROR,"删除公告信息失败：" + se.getReason());
            }
        }catch (Exception e){
            return new Result(false,StatusCode.ERROR,"删除公告信息失败：" + e.toString());
        }
    }
}
