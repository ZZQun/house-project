package com.house.dao;

import com.house.pojo.Schedule;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ScheduleDao {

     List<Schedule> selectByCondition();

     int deleteScheduleById(Integer id);

     int insertSchedule(Schedule schedule);

     int updateSchedule(Schedule schedule);



     List<Schedule> selectScheduleInSevenDays(Date todayDate);
}
