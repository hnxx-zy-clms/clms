package com.hnxx.zy.clms.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.function.Function;

/**
 * @program: clms
 * @description: 日期工具类
 * @author: nile
 * @create: 2020-04-04 16:31
 **/
public class DateUtils {

    public String[] getDateWeek(String date) throws ParseException {
        Calendar c = getCalendarByDateStr(date);
        String[] result = getStartAndEndDayByDate(date);
        result[1] = result[1]+" 22:00:00";
        System.out.println("当前周为第 "+c.get(Calendar.WEEK_OF_YEAR)+"周");
        System.out.println("周一日期为：" + result[0]);
        System.out.println("周天日期为：" + result[1]);
        return result;
    }
    /**
     * 根据日期获取该天所在周的开始与结束日期(周跨年周，只计算本年内那部分天数)
     * @param yyyyMMdd
     * @return
     * @throws ParseException
     */
    private String[] getStartAndEndDayByDate(String yyyyMMdd) throws ParseException {
        String[] dateZone = new String[3];
        Calendar cal = getCalendarByDateStr(yyyyMMdd);
        int year = Integer.parseInt(yyyyMMdd.substring(0, 4));
        //补0
        Function<Integer, String> add0 = (i -> i < 10 ? "0" + i : String.valueOf(i));

        dateZone[0] = cal.get(Calendar.YEAR) + "-" + add0.apply(cal.get(Calendar.MONTH) + 1) + "-" + add0.apply(cal.get(Calendar.DAY_OF_MONTH));
        if (cal.get(Calendar.YEAR) < year) {
            dateZone[0] = year + "-01-01";
        }
        cal.add(Calendar.DATE, 6);
        dateZone[1] = cal.get(Calendar.YEAR) + "-" + add0.apply(cal.get(Calendar.MONTH) + 1) + "-" + add0.apply(cal.get(Calendar.DAY_OF_MONTH));
        if (cal.get(Calendar.YEAR) > year) {
            dateZone[1] = year + "-12-31";
        }
        dateZone[2] = String.valueOf(cal.get(Calendar.WEEK_OF_YEAR));
        return dateZone;
    }

    private  Calendar getCalendarByDateStr(String yyyyMMdd) throws ParseException {
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        Date d = sm.parse(yyyyMMdd);
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        //判断要计算的日期是否是周日，如果是，则减一天计算周六的，否则会出问题，计算到下一周去了
        //获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        //设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        //获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        //根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        return cal;
    }

}
