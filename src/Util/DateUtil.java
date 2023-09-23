package PengTallyBook.Util;

import java.lang.ref.Cleaner;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    //获取日历
    private static Calendar c = Calendar.getInstance();

    public static java.sql.Timestamp util2sql(java.util.Date d) {
        return new java.sql.Timestamp(d.getTime());
    }

    //获取这个月的总天数
    public static int thisMonthTotalDay() {
        c.setTime(new Date());
        monthEnd();
        return c.get(Calendar.DATE);
    }

    /**
     * @return 今天0点的时间
     */
    public static Date today() {
        c.setTime(new Date());
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * @return 本月剩余天数
     */
    public static int thisMonthLeftDay() {
        int TotalDay = thisMonthTotalDay();
        today();
        int today = c.get(Calendar.DATE);
        return TotalDay - today + 1;
    }

    //返回这个月刚开始的时间
    public static Date monthBegin() {
        c.setTime(new Date());
        c.set(Calendar.DATE, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }


    //返回start日期所在月份结束的时间
    public static Date monthEnd(Date start) {
        c.setTime(start);
        c.set(Calendar.DATE, 1);
        c.add(Calendar.MONTH, 1);
        c.add(Calendar.DATE, -1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    public static Date monthEnd() {
        return monthEnd(monthBegin());
    }

    /**
     * @return 本年月份数
     */
    public static int thisMonth() {
        today();
        return c.get(Calendar.MONTH);
    }


    /**
     * @return 本年年份数
     */
    public static int thisYear() {
        today();
        return c.get(Calendar.YEAR);
    }
}

