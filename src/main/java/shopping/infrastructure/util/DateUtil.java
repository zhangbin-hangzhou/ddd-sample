package shopping.infrastructure.util;

import lombok.extern.slf4j.Slf4j;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.*;

@Slf4j
public class DateUtil {

    public static final String DB_DATE_FORMAT_STRING = "yyyyMMdd";

    public static final String FRONT_DATE_FORMAT_STRING = "yyyy-MM-dd";

    public static final String FRONT_DATE_FORMAT_STRING_TIME = "HH:mm:ss";

    public static final String DATA_TIME_PATTERN_1 = "yyyy-MM-dd HH:mm:ss";

    public static final String DATA_TIME_PATTERN_2 = "yyyy-MM-dd HH:mm";

    public static final String DATA_TIME_PATTERN_3 = "yyyyMMDDhhmmss";

    public static final String DATA_TIME_PATTERN_4 = "yyyyMMDDhhmmss";

    public static final String DATA_TIME_PATTERN_5 = "yyyyMMddHHmmssSSS";

    public static final String DATA_TIME_PATTERN_6 = "yyyy年MM月dd日";

    public static final String DATA_TIME_PATTERN_7 = "yyyy年MM月dd日 ahh:mm:ss";

    public static final String DATA_TIME_PATTERN_8 = "yyyy-MM-dd HH";

    public static final String DATA_TIME_PATTERN_9 = "yyyy-MM";

    public static final String DATA_TIME_PATTERN_10 = "yyyy";

    public static final String UTC_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    public static final String TIME_BEGIN = " 00:00:00";

    public static final String TIME_MIDDLE = " 12:00:00";

    public static final String TIME_END = " 23:59:59";

    public static final int HOUR_OF_MINUTE = 60;

    public static Date getStartDatetime(String startDate) {
        Date repayDatetime =
                DateUtil.strToDate(startDate + DateUtil.TIME_BEGIN, DateUtil.DATA_TIME_PATTERN_1);
        return repayDatetime;
    }

    public static Date getEndDatetime(String endDate) {
        Date repayDatetime =
                DateUtil.strToDate(endDate + DateUtil.TIME_END, DateUtil.DATA_TIME_PATTERN_1);
        return repayDatetime;
    }

    public static Date getRelativeDateOfSecond(Date startDate, int second) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(startDate);
            calendar.add(Calendar.SECOND, second);
            return calendar.getTime();
        } catch (Exception e) {
            return startDate;
        }
    }

    public static Date getDateSetSecond(Date date, int second) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(date);
            calendar.set(Calendar.SECOND, second);
            return calendar.getTime();
        } catch (Exception e) {
            return date;
        }
    }

    public static Date getRelativeDateOfMinute(Date startDate, int minute) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(startDate);
            calendar.add(Calendar.MINUTE, minute);
            return calendar.getTime();
        } catch (Exception e) {
            return startDate;
        }
    }

    public static Date getDateSetMinute(Date date, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(date);
            calendar.set(Calendar.MINUTE, minute);
            calendar.set(Calendar.SECOND, second);
            return calendar.getTime();
        } catch (Exception e) {
            return date;
        }
    }

    public static Date getDateSetHour(Date date, int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minute);
            calendar.set(Calendar.SECOND, second);
            return calendar.getTime();
        } catch (Exception e) {
            return date;
        }
    }

    public static Date getRelativeDateOfHour(Date startDate, int hour) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(startDate);
            calendar.add(Calendar.HOUR_OF_DAY, hour);
            return calendar.getTime();
        } catch (Exception e) {
            return startDate;
        }
    }

    public static Date getDateOfHour(Date date, int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minute);
            calendar.set(Calendar.SECOND, second);
            return calendar.getTime();
        } catch (Exception e) {
            return date;
        }
    }

    public static Date getRelativeDateOfDays(Date startDate, int days) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(startDate);
            calendar.add(Calendar.SECOND, days * 3600 * 24);
            return calendar.getTime();
        } catch (Exception e) {
            return startDate;
        }
    }

    public static int getMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    public static int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取iso
     *
     * @param dateStr
     * @return
     */
    public static Date getIosYearWeekStart(String isoWeekString) {
        SimpleDateFormat isoWeekFormat = new SimpleDateFormat("yyyyww");
        Date isoWeekDate = null;
        try {
            isoWeekDate = isoWeekFormat.parse(isoWeekString);
        } catch (ParseException e) {
            return null;
        }
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(isoWeekDate);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return calendar.getTime();
    }

    public static int getRelativeWeekDay(Date date) {
        int dayWeek = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
            dayWeek = 6;
        } else {
            dayWeek = calendar.get(Calendar.DAY_OF_WEEK) - 2;
        }

        return dayWeek;
    }

    /**
     * Date按格式pattern转String
     *
     * @create: 2015-4-18 下午11:02:34 miyb
     * @history:
     */
    public static String dateToStr(Date date, String pattern) {
        String str = null;
        SimpleDateFormat formater = new SimpleDateFormat(pattern, Locale.ENGLISH);
        try {
            str = formater.format(date);
        } catch (Exception e) {
        }
        return str;
    }

    /**
     * 获取当天开始时间
     *
     * @create: 2014-10-14 下午4:24:57 miyb
     * @history:
     */
    public static Date getTodayStart() {
        Calendar currentDate = new GregorianCalendar();
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        currentDate.set(Calendar.MILLISECOND, 0);
        return (Date) currentDate.getTime().clone();
    }

    /**
     * 获取时间的当天开始时间 00:00:00
     *
     * @create: 2014-10-14 下午4:24:57 miyb
     * @history:
     */
    public static Date getDateStart(Date date) {
        Calendar currentDate = new GregorianCalendar();
        currentDate.setTime(date);
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        currentDate.set(Calendar.MILLISECOND, 0);
        return (Date) currentDate.getTime().clone();
    }

    /**
     * 获取时间的当天结束时间
     */
    public static Date getDateEnd(Date date) {
        Calendar currentDate = new GregorianCalendar();
        currentDate.setTime(date);
        currentDate.set(Calendar.HOUR_OF_DAY, 23);
        currentDate.set(Calendar.MINUTE, 59);
        currentDate.set(Calendar.SECOND, 59);
        currentDate.set(Calendar.MILLISECOND, 59);
        return (Date) currentDate.getTime().clone();
    }

    /**
     * 获取昨天开始时间
     *
     * @create: 2014-10-14 下午4:24:57 miyb
     * @history:
     */
    public static Date getYesterdayStart() {
        Calendar currentDate = new GregorianCalendar();
        currentDate.add(Calendar.DAY_OF_MONTH, -1); // 设置为前一天
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        currentDate.set(Calendar.MILLISECOND, 0);
        return (Date) currentDate.getTime().clone();
    }

    /**
     * 根据日期 获取 年的第几周
     */
    public static int getWeekByDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.setTime(date);
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 根据日期 获取 年的第几周
     */
    public static int getIosWeekByDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setMinimalDaysInFirstWeek(4);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 根据日期 获取 当前年
     */
    public static int getYearByDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    /**
     * 根据日期 获取 当前月
     */
    public static int getMonthByDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH) + 1;
    }

    public static Date getYearMonthDate(String strDate) {
        Date date = null;
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM");
        try {
            date = formater.parse(strDate);

        } catch (ParseException e) {
            log.error("{}", e.getMessage(), e);
        }
        return date;
    }

    /**
     * 获取给定时间的当月开始时间
     */
    public static Date startMonthByDate(Date date) {
        // 使用Calendar类进行时间的计算
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 设置1号为本月第一天
        cal.set(Calendar.DAY_OF_MONTH, 1);
        // 设置小时为0时
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取给定时间的当月结束时间
     */
    public static Date endMonthByDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取给定时间的当周开始时间
     */
    public static Date startWeekByDate(Date date) {
        // 使用Calendar类进行时间的计算
        Calendar cal = Calendar.getInstance(Locale.CHINA);
        // 设置星期一为一周开始的第一天
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.setTime(date);
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;

        if (dayWeek == 0) {
            dayWeek = 7;
        }

        // 计算本周开始的时间
        cal.add(Calendar.DAY_OF_MONTH, 1 - dayWeek);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static int getDayOfWeek(@NotNull Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;

        if (dayWeek == 0) {
            dayWeek = 7;
        }
        return dayWeek;
    }

    public static Date endWeekByDate(Date date) {
        //    Calendar calendar = Calendar.getInstance(Locale.CHINA);
        //    // 设置星期一为一周开始的第一天
        //    calendar.setFirstDayOfWeek(Calendar.MONDAY);
        //    calendar.setTime(date);
        //    int dayWeek = calendar.get(Calendar.DAY_OF_WEEK);
        //
        //    // 计算本周开始的时间
        //    calendar.add(Calendar.DAY_OF_MONTH, 7 - dayWeek);

        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(date);

        // 取当前日期是星期几(week:星期几)
        int week = aCalendar.get(Calendar.DAY_OF_WEEK);
        if (week == 1) {
            week = 7;
        } else if (week == 0) {
            week = 6;
        } else {
            week -= 1;
        }

        // 取距离当前日期最近的周日与当前日期相差的天数（count：相差的天数。正数：之后的周日，负数：之前的周日）
        int count = 7 - week;

        // 获取距离当前日期最近的周日日期
        aCalendar.add(Calendar.DAY_OF_MONTH, count);
        aCalendar.set(Calendar.HOUR_OF_DAY, 23);
        aCalendar.set(Calendar.MINUTE, 59);
        aCalendar.set(Calendar.SECOND, 59);
        aCalendar.set(Calendar.MILLISECOND, 0);
        return aCalendar.getTime();
    }

    // 计算日期所在周的最后一天（周日为最后一天）
    public static Date endWeekByDateV2(Date date) {
        Calendar calendar = Calendar.getInstance();
        // 设置星期一为一周开始的第一天
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        int dayWeek = calendar.get(Calendar.DAY_OF_WEEK);

        // 计算本周结束的时间
        if (dayWeek == 1) {
            calendar.add(Calendar.DAY_OF_MONTH, 0);
        } else {
            calendar.add(Calendar.DAY_OF_MONTH, 8 - dayWeek);
        }
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 获取给定时间的上周开始时间
     */
    public static Date prevStartWeekByDate(Date date) {
        // 使用Calendar类进行时间的计算
        //    Calendar cal = Calendar.getInstance();
        //    // 设置星期一为一周开始的第一天
        //    cal.setFirstDayOfWeek(Calendar.MONDAY);
        //    cal.setTime(date);
        //    // 获得当前日期是一个星期的第几天
        //    int dayWeek = cal.get(Calendar.DAY_OF_WEEK);

        Calendar cal = Calendar.getInstance();
        cal.setTime(startWeekByDate(date));
        cal.add(Calendar.DATE, -7);
        // 计算本周开始的时间
        //    cal.add(Calendar.DAY_OF_MONTH, 1 - dayWeek - 7);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 当前时间加减多少天
     */
    public static Date dateAddDay(Date date, int num) {
        // 使用Calendar类进行时间的计算
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, num);
        return cal.getTime();
    }

    /**
     * 当前时间加减多少月
     */
    public static Date dateAddMonth(Date date, int num) {
        // 使用Calendar类进行时间的计算
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, num);
        return cal.getTime();
    }


    /**
     * 获取昨天结束时间
     *
     * @create: 2014-10-14 下午4:24:57 miyb
     * @history:
     */
    public static Date getYesterdayEnd() {
        Calendar currentDate = new GregorianCalendar();
        currentDate.add(Calendar.DAY_OF_MONTH, -1); // 设置为前一天
        currentDate.set(Calendar.HOUR_OF_DAY, 23);
        currentDate.set(Calendar.MINUTE, 59);
        currentDate.set(Calendar.SECOND, 59);
        currentDate.set(Calendar.MILLISECOND, 999);
        return (Date) currentDate.getTime().clone();
    }

    /**
     * 忽略时分秒的日期比较方式
     */
    public static boolean sameDate(Date d1, Date d2) {
        if (null == d1 || null == d2) {
            return false;
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(d1);
        cal1.set(Calendar.HOUR_OF_DAY, 0);
        cal1.set(Calendar.MINUTE, 0);
        cal1.set(Calendar.SECOND, 0);
        cal1.set(Calendar.MILLISECOND, 0);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(d2);
        cal2.set(Calendar.HOUR_OF_DAY, 0);
        cal2.set(Calendar.MINUTE, 0);
        cal2.set(Calendar.SECOND, 0);
        cal2.set(Calendar.MILLISECOND, 0);
        return cal1.getTime().equals(cal2.getTime());
    }

    /**
     * 获取当天结束时间
     *
     * @create: 2014-10-14 下午4:24:57 miyb
     * @history:
     */
    public static Date getTodayEnd() {
        Calendar currentDate = new GregorianCalendar();
        currentDate.set(Calendar.HOUR_OF_DAY, 23);
        currentDate.set(Calendar.MINUTE, 59);
        currentDate.set(Calendar.SECOND, 59);
        currentDate.set(Calendar.MILLISECOND, 999);
        return (Date) currentDate.getTime().clone();
    }

    /**
     * 相对参数today的明日起始时刻。比如今天是11日23点，明日起始时刻为12日0点0分0秒
     *
     * @create: 2015年11月16日 上午11:49:51 myb858
     * @history:
     */
    public static Date getTomorrowStart(Date today) {
        String str = dateToStr(today, FRONT_DATE_FORMAT_STRING);
        Date tommrow = getRelativeDateOfSecond(strToDate(str, FRONT_DATE_FORMAT_STRING), 24 * 3600);
        return tommrow;
    }

    /**
     * String 按格式pattern转Date
     *
     * @create: 2015-4-18 下午11:02:34 miyb
     * @history:
     */
    public static Date strToDate(String str, String pattern) {
        Date date = null;
        SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.ENGLISH);
        try {
            date = format.parse(str);
        } catch (Exception e) {
            log.error("{}", e.getMessage(), e);
        }
        return date;
    }

    public static Date strToDateV2(String str, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = simpleDateFormat.parse(str);
        } catch (Exception e) {
            log.error("{}", e.getMessage(), e);
        }
        return date;
    }

    /**
     * 删除—
     *
     * @create: 2015年10月27日 下午7:59:41 myb858
     * @history:
     */
    public static String remove_(String strDate) {
        String string = null;
        try {
            string = strDate.replace("-", "");
        } catch (Exception e) {
            // log.error("{}", e.getMessage(), e);
        }
        return string;
    }

    /**
     * 按格式获取当前时间
     *
     * @create: 2015-5-7 上午11:22:04 miyb
     * @history:
     */
    public static String getToday(String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return df.format(new Date());
    }

    /**
     * @param addOneDay 是否加1天
     * @create: 2015-5-7 上午11:25:23 miyb
     * @history:
     */
    public static Date getFrontDate(String date, boolean addOneDay) {
        Date returnDate = null;
        try {
            returnDate = new SimpleDateFormat(FRONT_DATE_FORMAT_STRING).parse(date);
            if (addOneDay && returnDate != null) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(returnDate);
                calendar.add(Calendar.DATE, 1); // 把日期往后增加一天.整数往后推,负数往前移动
                calendar.add(Calendar.SECOND, -1); // 变成23：59：59
                returnDate = calendar.getTime(); // 这个时间就是日期往后推一天的结果
            }
        } catch (Exception e) {
            // log.error("{}", e.getMessage(), e);
        }
        return returnDate;
    }

    /**
     * 统计两个时间差，返回的是天数(即24小时算一天，少于24小时就为0，用这个的时候最好把小时、分钟等去掉)
     *
     * @param beginStr 开始时间
     * @param endStr   结束时间
     * @param format   时间格式
     */
    public static int daysBetween(String beginStr, String endStr, String format) {
        Date end = strToDate(endStr, format);
        Date begin = strToDate(beginStr, format);
        long times = end.getTime() - begin.getTime();
        return (int) (times / 60 / 60 / 1000 / 24);
    }

    /**
     * 统计两个时间差，返回的是天数(即24小时算一天，少于24小时就为0，用这个的时候最好把小时、分钟等去掉)
     *
     * @create: 2015年11月16日 上午11:20:51 myb858
     * @history:
     */
    public static int daysBetween(Date beginDate, Date endDate) {
        long times = endDate.getTime() - beginDate.getTime();
        return (int) (times / 60 / 60 / 1000 / 24);
    }

    /**
     * 统计两个时间差，返回的是s
     *
     * @create: 2015年11月16日 上午11:20:51 myb858
     * @history:
     */
    public static long secondsBetween(Date beginDate, Date endDate) {
        long times = endDate.getTime() - beginDate.getTime();
        return (long) (times / 1000);
    }

    /**
     * 统计两个时间差，返回的是分钟
     *
     * @param beginStr 开始时间
     * @param endStr   结束时间
     * @param format   时间格式
     */
    public static int minuteBetween(Date begin, Date end) {
        long times = end.getTime() - begin.getTime();
        return (int) (times / 1000 / 60);
    }

    /**
     * 统计两个时间差，返回的是分钟
     *
     * @param beginStr 开始时间
     * @param endStr   结束时间
     * @param format   时间格式
     */
    public static int ceilMinuteBetween(Date begin, Date end) {
        long times = end.getTime() - begin.getTime();
        return (int) Math.ceil((times / 1000 / (double) 60));
    }

    /**
     * 提供格式化时间，统计两个时间差之间返回的天数(即24小时算一天，少于24小时就为0，用这个的时候最好把小时、分钟等去掉)
     *
     * @create: 2017年1月8日 下午5:07:50 xieyj
     * @history:
     */
    public static int daysBetweenDate(Date beginDate, Date endDate) {
        String beginStr = DateUtil.dateToStr(beginDate, DateUtil.FRONT_DATE_FORMAT_STRING);
        String endStr = DateUtil.dateToStr(endDate, DateUtil.FRONT_DATE_FORMAT_STRING);
        Date end = strToDate(endStr, DateUtil.FRONT_DATE_FORMAT_STRING);
        Date begin = strToDate(beginStr, DateUtil.FRONT_DATE_FORMAT_STRING);
        long times = end.getTime() - begin.getTime();
        return (int) (times / 60 / 60 / 1000 / 24);
    }

    public static Date getCurrentMonthFirstDay() {
        Calendar currentDate = new GregorianCalendar();
        currentDate.set(Calendar.DATE, 1);
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        return (Date) currentDate.getTime().clone();
    }

    public static Date getCurrentMonthLastDay() {
        Date date = new Date();
        Calendar currentDate = Calendar.getInstance();
        currentDate.setTime(date);
        do {
            currentDate.add(Calendar.DATE, 1);
        } while (currentDate.get(Calendar.DATE) != 1);
        currentDate.add(Calendar.DATE, -1);
        currentDate.set(Calendar.HOUR_OF_DAY, 23);
        currentDate.set(Calendar.MINUTE, 59);
        currentDate.set(Calendar.SECOND, 59);
        return currentDate.getTime();
    }

    public static Date getPreMonthFirstDay() {
        // 获取前月的第一天
        Calendar cal = Calendar.getInstance(); // 获取当前日期
        cal.add(Calendar.MONTH, -1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    public static Date getPreMonthLastDay() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 0); // 设置为1号,当前日期既为本月第一天
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    public static Date getMonthFirstDay(Date date) {
        Calendar setDate = Calendar.getInstance();
        setDate.setTime(date);
        setDate.set(Calendar.DATE, 1);
        setDate.set(Calendar.HOUR_OF_DAY, 0);
        setDate.set(Calendar.MINUTE, 0);
        setDate.set(Calendar.SECOND, 0);
        return (Date) setDate.getTime().clone();
    }

    /**
     * 获取半个小时前的时间
     */
    public static Date getBeforeHalfHour(Date date) {
        Calendar setDate = Calendar.getInstance();
        setDate.setTime(date);
        setDate.set(Calendar.MINUTE, -30);
        return (Date) setDate.getTime().clone();
    }

    public static Date getMonthLastDay(Date date) {
        Calendar setDate = Calendar.getInstance();
        setDate.setTime(date);
        do {
            setDate.add(Calendar.DATE, 1);
        } while (setDate.get(Calendar.DATE) != 1);
        setDate.add(Calendar.DATE, -1);
        setDate.set(Calendar.HOUR_OF_DAY, 23);
        setDate.set(Calendar.MINUTE, 59);
        setDate.set(Calendar.SECOND, 59);
        return setDate.getTime();
    }

    public static Date getDate(int month) {
        Date returnDate = new Date();
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(returnDate);
            calendar.add(Calendar.MONTH, month); // 把日期往后增加一个月.整数往后推,负数往前移动
            returnDate = calendar.getTime(); // 这个时间就是日期往后推一天的结果
        } catch (Exception e) {

        }
        return returnDate;
    }

    public static Date getDate(Date date, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month); // 把日期往后增加一个月.整数往后推,负数往前移动
        return calendar.getTime(); // 这个时间就是日期往后推一天的结果
    }

    public static Date getYearStart() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date getPreYearStart(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -12);
        return calendar.getTime();
    }

    public static Date getNextYearStart(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 12);
        return calendar.getTime();
    }

    public static Date getYesterdayNow() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    // 计算两个时间相差的天数，不需要具体的时间
    public static Long getDaysBetween(Date startDate, Date endDate) {
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(startDate);
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
        fromCalendar.set(Calendar.MINUTE, 0);
        fromCalendar.set(Calendar.SECOND, 0);
        fromCalendar.set(Calendar.MILLISECOND, 0);

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(endDate);
        toCalendar.set(Calendar.HOUR_OF_DAY, 0);
        toCalendar.set(Calendar.MINUTE, 0);
        toCalendar.set(Calendar.SECOND, 0);
        toCalendar.set(Calendar.MILLISECOND, 0);

        return (toCalendar.getTime().getTime() - fromCalendar.getTime().getTime())
                / (1000 * 60 * 60 * 24);
    }


    // 计算两个时间相差的天数，不需要具体的时间
    public static Long getHoursBetween(Date startDate, Date endDate) {

        return (endDate.getTime() - startDate.getTime())
                / (1000 * 60 * 60);
    }

    public static Date TimeStamp2Date(String timestampString, String formats) {
        Long timestamp = Long.parseLong(timestampString) * 1000;
        String date = new SimpleDateFormat(formats).format(new Date(timestamp));
        return strToDate(date, formats);
    }

    /**
     * 得到UTC时间，类型为字符串，格式为"yyyy-MM-dd HH:mm:ss"<br> 如果获取失败，返回null
     */
    public static String getUTCTimeStr() {
        // 1、取得本地时间：
        Calendar cal = Calendar.getInstance();
        // 2、取得时间偏移量：
        int zoneOffset = cal.get(Calendar.ZONE_OFFSET);
        // 3、取得夏令时差：
        int dstOffset = cal.get(Calendar.DST_OFFSET);
        // 4、从本地时间里扣除这些差量，即可以取得UTC时间：
        cal.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));
        Date utc = new Date(cal.getTimeInMillis());
        return dateToStr(utc, UTC_TIME_FORMAT);
    }

    public static boolean getEffectDate(String startDateStr, String endDateStr, String format) {
        boolean result = false;
        Date startDate = DateUtil.strToDate(startDateStr, format);
        Date endDate = DateUtil.strToDate(endDateStr, format);

        Date now = new Date();
        if (now.getTime() >= startDate.getTime() && now.getTime() <= endDate.getTime()) {
            result = true;
        }
        return result;
    }

    /**
     * 获取某年第一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getYearFirst(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    /**
     * 获取某年最后一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getYearLast(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();

        return currYearLast;
    }

    public static String format(Date date, String pattern) {
        DateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    public static List<String> getYears(String start, String end) {
        List<String> dateList = new ArrayList<String>();
        if (StringUtils.isNotBlank(start) && StringUtils.isNotBlank(end)) {
            Date dBegin = strToDate(start, DATA_TIME_PATTERN_1);
            Date dEnd = strToDate(end, DATA_TIME_PATTERN_1);

            Calendar calBegin = Calendar.getInstance();
            // 使用给定的 Date 设置此 Calendar 的时间
            calBegin.setTime(dBegin);
            int year1 = calBegin.get(Calendar.YEAR);
            Calendar calEnd = Calendar.getInstance();
            // 使用给定的 Date 设置此 Calendar 的时间
            calEnd.setTime(dEnd);
            int year2 = calEnd.get(Calendar.YEAR);
            // 测试此日期是否在指定日期之后
            while (year2 >= year1) {
                dateList.add(format(calBegin.getTime(), "yyyy"));
                calBegin.add(Calendar.YEAR, 1);
                year1 = calBegin.get(Calendar.YEAR);
            }
        }
        return dateList;
    }

    // unix时间戳 移除秒毫秒部分
    public static Long getUnixTimestamp() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTimeInMillis() / 1000;
    }

    // 将时间加上分钟
    public static Date getDateAddMinute(Date startDate, int minute) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(startDate);
            calendar.add(Calendar.MINUTE, minute);
            return calendar.getTime();
        } catch (Exception e) {
            return startDate;
        }
    }

    /**
     * 将时间加上小时
     */
    public static Date getDateAddHour(Date startDate, int hours) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(startDate);
            calendar.add(Calendar.HOUR, hours);
            return calendar.getTime();
        } catch (Exception e) {
            return startDate;
        }
    }

    /**
     * 将时间加上年
     */
    public static Date getDateAddYear(Date startDate, int hours) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(startDate);
            calendar.add(Calendar.YEAR, hours);
            return calendar.getTime();
        } catch (Exception e) {
            return startDate;
        }
    }

    /**
     * 将时间加上秒
     */
    public static Date getDateAddSeconds(Date startDate, int seconds) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(startDate);
            calendar.add(Calendar.SECOND, seconds);
            return calendar.getTime();
        } catch (Exception e) {
            return startDate;
        }
    }

    // 将时间加上天数
    public static Date getDateAddDays(Date startDate, int days) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(startDate);
            calendar.add(Calendar.DAY_OF_MONTH, days);
            return calendar.getTime();
        } catch (Exception e) {
            return startDate;
        }
    }

    /**
     * 获取某个日期的开始时间
     */
    public static Date getDayStartTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        if (null != date) {
            calendar.setTime(date);
        }
        calendar.set(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                0,
                0,
                0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取某个日期的结束时间
     */
    public static Date getDayEndTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        if (null != date) {
            calendar.setTime(date);
        }
        calendar.set(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                23,
                59,
                59);
        calendar.set(Calendar.MILLISECOND, 000);
        return calendar.getTime();
    }

    /**
     * 获取当前去除分钟个位数和秒数
     */
    public static Date getCurrencyDateRemoveSecond(long timeMillis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeMillis);
        // 将个位数抹去
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) / 10 * 10);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取当前年月日字符串
     */
    public static String getYearMonthDay(long timeMillis) {
        Date date = new Date(timeMillis);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtil.DB_DATE_FORMAT_STRING);
        String string = simpleDateFormat.format(date);
        return string;
    }

    /**
     * 获取当前年月 第几周字符串 ex：2021101
     */
    public static String getYearMonthWeek(long timeMillis) {
        Calendar calendar = Calendar.getInstance();
        // 设置星期一为一周开始的第一天
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前的时间戳
        calendar.setTimeInMillis(timeMillis);
        // 获取当前年
        int year = calendar.get(Calendar.YEAR);
        // 获取当前月
        int month = calendar.get(Calendar.MONTH) + 1;
        // 获取当前周在当月
        int week = calendar.get(Calendar.WEEK_OF_MONTH);
        return "" + year + month + week;
    }

    /**
     * 获取某段时间内的所有日期
     */
    public static List<Date> findDates(Date dStart, Date dEnd) {
        Calendar cStart = Calendar.getInstance();
        cStart.setTime(dStart);

        List dateList = new ArrayList();
        // 别忘了，把起始日期加上
        dateList.add(dStart);
        // 此日期是否在指定日期之后
        while (dEnd.after(cStart.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            cStart.add(Calendar.DAY_OF_MONTH, 1);
            dateList.add(cStart.getTime());
        }
        return dateList;
    }

    /**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     *
     * @param nowTime   当前时间
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @author jqlin
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        return date.after(begin) && date.before(end);
    }

    public static Date timeZoneChange(Date date, String timeZone) {
        SimpleDateFormat sdf8 = new SimpleDateFormat(DATA_TIME_PATTERN_1);
        sdf8.setTimeZone(TimeZone.getTimeZone(timeZone));//设置时区
        String format = sdf8.format(date);
        return DateUtil.strToDate(format, DateUtil.DATA_TIME_PATTERN_1);
    }

    public static Date SecondTimestamp2Date(int timestamp) {
        return new Date(timestamp * 1000L);
    }

    public static void main(String[] args) {
        boolean b = new BigDecimal(0.35).compareTo(BigDecimal.ONE) > 0;
        Date date = new Date();
        String month = Month.of((date.getMonth() + 1)).getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        System.out.println(month + ". " + getDayOfMonth(date) + " " +getYearByDate(date) + ", " + DateUtil.format(date,FRONT_DATE_FORMAT_STRING_TIME));
    }

    public static int getDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        //时间，可以为具体的某一时间
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }


    public static Date setHour(Date date, int hour) {
        Calendar cStart = Calendar.getInstance();
        cStart.setTime(date);
        cStart.set(Calendar.HOUR_OF_DAY, hour);
        cStart.set(Calendar.MINUTE, 0);
        cStart.set(Calendar.SECOND, 0);
        return cStart.getTime();
    }

    /**
     * 秒转换为时分秒 HH:mm:ss 格式 仅当小时数大于0时 展示HH
     *
     * @param second 秒
     * @return 时分秒
     */
    public static String getFormatTimeBySecond(long second) {
        // 得到分钟数
        long hour = second / 3600;
        //剩余的秒数
        second = second % 3600;
        //得到分
        long minute = second / 60;
        //剩余的秒
        second = second % 60;
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

    /**
     * 日期转星期
     *
     * @param datetime "2017-01-01"
     * @return
     */
    public static String getweekByCurrDate() {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = { "7", "1", "2", "3", "4", "5", "6" };
        Calendar cal = Calendar.getInstance(); // 获得一个日历
        Date datet = null;
        try {
            datet = f.parse(DateUtil.getToday("yyyy-MM-dd"));
            cal.setTime(datet);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    public static String getweekByCurrDate(Date date) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = { "7", "1", "2", "3", "4", "5", "6" };
        Calendar cal = Calendar.getInstance(); // 获得一个日历
        Date datet = null;
        try {
            datet = f.parse(DateUtil.dateToStr(date,"yyyy-MM-dd"));
            cal.setTime(datet);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    public static String getCurrentDay() {
        return DateUtil.dateToStr(new Date(),DB_DATE_FORMAT_STRING);
    }


}
