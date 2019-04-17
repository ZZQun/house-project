package com.house.service;


import com.house.dao.ScheduleDao;
import com.house.dto.ScheduleExecution;
import com.house.exception.ScheduleOperationException;
import com.house.pojo.Schedule;
import com.house.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class ScheduleService {

    @Autowired
    private ScheduleDao scheduleDao;

    public List<Schedule> findByCondition() {
        return scheduleDao.selectByCondition();
    }

    public List<Schedule> findScheduleInSevenDays(Date todayDate) {
        return scheduleDao.selectScheduleInSevenDays(todayDate);
    }

    public ScheduleExecution addSchedule(Schedule schedule) {
        if(schedule == null){
            return new ScheduleExecution(false,"添加公告信息为空");
        }
        schedule.setDate(DateUtil.dateFormat1(new Date()));
        try{
            int effectedNum = scheduleDao.insertSchedule(schedule);
            if(effectedNum < 1){
                return new ScheduleExecution(false,"添加公告信息失败");
            }
        }catch (Exception e){
            throw new ScheduleOperationException(e.toString());
        }
        return new ScheduleExecution(true);
    }

    public ScheduleExecution deleteSchedule(Integer id) {
        if(id == null){
            return new ScheduleExecution(false,"删除公告信息Id为空");
        }
        try{
            int effectedNum = scheduleDao.deleteScheduleById(id);
            if(effectedNum < 1){
                return new ScheduleExecution(false,"删除公告信息失败");
            }
        }catch (Exception e){
            throw new ScheduleOperationException(e.toString());
        }
        return new ScheduleExecution(true);
    }

    public ScheduleExecution updateSchedule(Schedule schedule) {
        if(schedule == null){
            return new ScheduleExecution(false,"更新公告信息为空");
        }
        try{
            int effectedNum = scheduleDao.updateSchedule(schedule);
            if(effectedNum < 1){
                return new ScheduleExecution(false,"更新公告信息失败");
            }
        }catch (Exception e){
            throw new ScheduleOperationException(e.toString());
        }
        return new ScheduleExecution(true);
    }

    public Schedule findScheduleById(Integer id) {
        return findScheduleById(id);
    }
}
