package com.house.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by treasure.zhou on 2019/3/21.
 */
public class DateUtil {
    /**
     * 获取当前时间秒数
     *
     * @return 当前时间秒数
     */
    public static int currentSeconds()
    {
        return (int) (System.currentTimeMillis() / 1000);
    }

    /**
     * 获取格式为yyyy-MM-dd的日期
     */

    public static Date dateFormat1(Date date){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String nowFormatStr = sdf.format(date);
            return sdf.parse(nowFormatStr);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前日期的当周的周一周末的日期，并按照格式yyyyy-MM-dd返回
     *
     */
    public static Map<String,Date> dateFormat2(Date date){
        Map<String,Date> map = new HashMap<>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        if(1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        cal.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int day = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek()-day);//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        map.put("WeekMonday",dateFormat1(cal.getTime()));

        cal.add(Calendar.DATE, 6);
        map.put("WeekSunday",dateFormat1(cal.getTime()));

        return map;
    }
}
