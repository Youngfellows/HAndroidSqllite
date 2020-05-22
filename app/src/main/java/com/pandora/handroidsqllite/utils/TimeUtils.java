package com.pandora.handroidsqllite.utils;

import android.text.TextUtils;
import android.util.Log;

import com.pandora.handroidsqllite.bean.TimeDifference;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


public class TimeUtils {
    private static String TAG = "TimeUtils";
    private static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";


    /**
     * 获取格式为"yyyy-MM-dd HH:mm"的当前时间
     *
     * @return
     */
    public static String getFormatDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdf.format(new Date());
    }

    /**
     * 获取格式为"yyyy-MM-dd HH:mm:ss"的当前时间
     *
     * @return
     */
    public static String getFormatDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(new Date());
    }

    /**
     * 判断字符串是否为日期字符串
     *
     * @param datevalue
     * @return
     */
    public static boolean isDateString(String datevalue) {
        if (TextUtils.isEmpty(datevalue)) {
            return false;
        }
        try {
            SimpleDateFormat fmt = new SimpleDateFormat(DATE_FORMAT);
            Date dd = fmt.parse(datevalue);
            String format = fmt.format(dd);
            Log.i(TAG, "format: " + format);
            if (datevalue.equals(format)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 返回字符串时间的年
     *
     * @param datevalue 字符串时间  "2017-06-03 23:32:31"
     * @return 返回年
     */
    public static int getYear(String datevalue) {
        int year = 0;
        if (isDateString(datevalue)) {
            try {
                Calendar cal = Calendar.getInstance(Locale.CHINA);
                SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
                Date date = format.parse(datevalue);
                cal.setTime(date);
                year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH) + 1;
                int day = cal.get(Calendar.DAY_OF_MONTH);
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int minute = cal.get(Calendar.MINUTE);
                int second = cal.get(Calendar.SECOND);
                Log.d(TAG, "year: " + year + " ,month: " + month + " ,day: " + day + " ,hour: " + hour + " ,minute: " + minute + " ,second: " + second);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            Log.d(TAG, "字符串不是日期字符串");
        }
        return year;
    }

    /**
     * 返回字符串时间的月
     *
     * @param datevalue 字符串时间  "2017-06-03 23:32:31"
     * @return 返回月
     */
    public static int getMonth(String datevalue) {
        int month = 0;
        if (isDateString(datevalue)) {
            try {
                Calendar cal = Calendar.getInstance(Locale.CHINA);
                SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
                Date date = format.parse(datevalue);
                cal.setTime(date);
                month = cal.get(Calendar.MONTH) + 1;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            Log.d(TAG, "字符串不是日期字符串");
        }
        return month;
    }

    /**
     * 返回字符串时间的日
     *
     * @param datevalue 字符串时间  "2017-06-03 23:32:31"
     * @return 返回日
     */
    public static int getDay(String datevalue) {
        int day = 0;
        if (isDateString(datevalue)) {
            try {
                Calendar cal = Calendar.getInstance(Locale.CHINA);
                SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
                Date date = format.parse(datevalue);
                cal.setTime(date);
                day = cal.get(Calendar.DAY_OF_MONTH);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            Log.d(TAG, "字符串不是日期字符串");
        }
        return day;
    }

    /**
     * 返回字符串时间的时
     *
     * @param datevalue 字符串时间  "2017-06-03 23:32:31"
     * @return 返回时
     */
    public static int getHour(String datevalue) {
        int hour = 0;
        if (isDateString(datevalue)) {
            try {
                Calendar cal = Calendar.getInstance(Locale.CHINA);
                SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
                Date date = format.parse(datevalue);
                cal.setTime(date);
                hour = cal.get(Calendar.HOUR_OF_DAY);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            Log.d(TAG, "字符串不是日期字符串");
        }
        return hour;
    }

    /**
     * 返回字符串时间的分
     *
     * @param datevalue 字符串时间  "2017-06-03 23:32:31"
     * @return 返回分
     */
    public static int getMinute(String datevalue) {
        int minute = 0;
        if (isDateString(datevalue)) {
            try {
                Calendar cal = Calendar.getInstance(Locale.CHINA);
                SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
                Date date = format.parse(datevalue);
                cal.setTime(date);
                minute = cal.get(Calendar.MINUTE);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            Log.d(TAG, "字符串不是日期字符串");
        }
        return minute;
    }

    /**
     * 返回字符串时间的秒
     *
     * @param datevalue 字符串时间  "2017-06-03 23:32:31"
     * @return 返回秒
     */
    public static int getSecond(String datevalue) {
        int second = 0;
        if (isDateString(datevalue)) {
            try {
                Calendar cal = Calendar.getInstance(Locale.CHINA);
                SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
                Date date = format.parse(datevalue);
                cal.setTime(date);
                second = cal.get(Calendar.SECOND);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            Log.d(TAG, "字符串不是日期字符串");
        }
        return second;
    }

    private static String splitYearMonthDay(String dateValue) {
        String yearMonthDay = null;
        if (!TextUtils.isEmpty(dateValue)) {
            String[] dates = dateValue.split(" ");
            if (dates.length > 0) {
                for (int i = 0; i < dates.length; i++) {
                    String date = dates[i];
                    //                    Log.i(TAG, "dates[" + i + "]" + " = " + date);
                    yearMonthDay = dates[0];
                }
            }
        }
        return yearMonthDay;
    }

    private static String splitHourMinuteSecond(String dateValue) {
        String yearMonthDay = null;
        if (!TextUtils.isEmpty(dateValue)) {
            String[] dates = dateValue.split(" ");
            if (dates.length > 0) {
                for (int i = 0; i < dates.length; i++) {
                    String date = dates[i];
                    //                    Log.i(TAG, "dates[" + i + "]" + " = " + date);
                    yearMonthDay = dates[1];
                }
            }
        }
        return yearMonthDay;
    }

    public static int getYears(String dateValue) {
        int year = 0;
        try {
            String yearMonthDay = splitYearMonthDay(dateValue);
            String[] dates = yearMonthDay.split("-");
            if (dates.length > 0) {
                for (int i = 0; i < dates.length; i++) {
                    year = Integer.parseInt(dates[0]);
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return year;
    }

    public static int getMonths(String dateValue) {
        int month = 0;
        try {
            String yearMonthDay = splitYearMonthDay(dateValue);
            String[] dates = yearMonthDay.split("-");
            if (dates.length > 0) {
                for (int i = 0; i < dates.length; i++) {
                    month = Integer.parseInt(dates[1]);
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return month;
    }

    public static int getDays(String dateValue) {
        int day = 0;
        try {
            String yearMonthDay = splitYearMonthDay(dateValue);
            String[] dates = yearMonthDay.split("-");
            if (dates.length > 0) {
                for (int i = 0; i < dates.length; i++) {
                    day = Integer.parseInt(dates[2]);
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return day;
    }

    public static int getHours(String dateValue) {
        int hour = 0;
        try {
            String yearMonthDay = splitHourMinuteSecond(dateValue);
            String[] dates = yearMonthDay.split(":");
            if (dates.length > 0) {
                for (int i = 0; i < dates.length; i++) {
                    hour = Integer.parseInt(dates[0]);
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return hour;
    }

    public static int getMinutes(String dateValue) {
        int minute = 0;
        try {
            String yearMonthDay = splitHourMinuteSecond(dateValue);
            String[] dates = yearMonthDay.split(":");
            if (dates.length > 0) {
                for (int i = 0; i < dates.length; i++) {
                    minute = Integer.parseInt(dates[1]);
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return minute;
    }

    public static int getSeconds(String dateValue) {
        int seconds = 0;
        try {
            String yearMonthDay = splitHourMinuteSecond(dateValue);
            String[] dates = yearMonthDay.split(":");
            if (dates.length > 0) {
                for (int i = 0; i < dates.length; i++) {
                    seconds = Integer.parseInt(dates[2]);
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return seconds;
    }

    /**
     * 天数差
     *
     * @throws ParseException
     */
    public static int calDaysTimeDiff(String fromTime, String toTime) {
        try {
            SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

            //Date fromDate1 = simpleFormat.parse("2018-03-01 12:00:32");
            //Date toDate1 = simpleFormat.parse("2018-03-12 12:00:08");

            Date fromDate1 = simpleFormat.parse(fromTime);
            Date toDate1 = simpleFormat.parse(toTime);
            long from1 = fromDate1.getTime();
            long to1 = toDate1.getTime();
            int days = (int) ((to1 - from1) / (1000 * 60 * 60 * 24));
            //Log.d(TAG, "两个时间之间的天数差为：" + days);
            return days;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 小时差
     *
     * @throws ParseException
     */
    public static int calHoursTimeDiff(String fromTime, String toTime) {
        try {
            SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            //Date fromDate2 = simpleFormat.parse("2018-03-01 12:00:22");
            //Date toDate2 = simpleFormat.parse("2018-03-12 12:00:19");

            Date fromDate2 = simpleFormat.parse(fromTime);
            Date toDate2 = simpleFormat.parse(toTime);
            long from2 = fromDate2.getTime();
            long to2 = toDate2.getTime();
            int hours = (int) ((to2 - from2) / (1000 * 60 * 60));
            //Log.d(TAG, "两个时间之间的小时差为：" + hours);
            calculateTimeDifference(fromTime, toTime);
            return hours;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 分钟差
     *
     * @throws ParseException
     */
    public static int calMinutesTimeDiff(String fromTime, String toTime) {
        try {
            SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

            //Date fromDate3 = simpleFormat.parse("2018-03-01 12:00:33");
            //Date toDate3 = simpleFormat.parse("2018-03-12 12:00:19");

            Date fromDate3 = simpleFormat.parse(fromTime);
            Date toDate3 = simpleFormat.parse(toTime);
            long from3 = fromDate3.getTime();
            long to3 = toDate3.getTime();
            int minutes = (int) ((to3 - from3) / (1000 * 60));
            //Log.d(TAG, "两个时间之间的分钟差为：" + minutes);
            calculateTimeDifference(fromTime, toTime);
            return minutes;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 秒差
     *
     * @throws ParseException
     */
    public static int calSecondsTimeDiff(String fromTime, String toTime) {
        try {
            SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

            //Date fromDate3 = simpleFormat.parse("2018-03-01 12:00:33");
            //Date toDate3 = simpleFormat.parse("2018-03-12 12:00:19");

            Date fromDate3 = simpleFormat.parse(fromTime);
            Date toDate3 = simpleFormat.parse(toTime);
            long from3 = fromDate3.getTime();
            long to3 = toDate3.getTime();
            int minutes = (int) ((to3 - from3) / 1000);
            //Log.d(TAG, "两个时间之间的秒差为：" + minutes);
            calculateTimeDifference(fromTime, toTime);
            return minutes;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }


    /**
     * 是否隔天
     *
     * @param fromTime 2020-05-17 19:28:17
     * @param toTime   2020-05-18 19:28:17
     * @return
     */
    public static boolean isSameDate(String fromTime, String toTime) {
        try {
            Log.d(TAG, "isSameDate: fromTime: " + fromTime + ",toTime: " + toTime);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date fromDate = sdf.parse(fromTime);
            Calendar fromCalendar = Calendar.getInstance();
            fromCalendar.setTime(fromDate);

            Date toDate = sdf.parse(toTime);
            Calendar toCalendar = Calendar.getInstance();
            toCalendar.setTime(toDate);

            int fromYear = fromCalendar.get(Calendar.YEAR);
            int fromMonth = fromCalendar.get(Calendar.MONTH) + 1;
            int dateFrom = fromCalendar.get(Calendar.DATE);

            int toYear = toCalendar.get(Calendar.YEAR);
            int toMonth = toCalendar.get(Calendar.MONTH) + 1;
            int dateTo = toCalendar.get(Calendar.DATE);

            if (fromYear == toYear && fromMonth == toMonth && dateFrom == dateTo) {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 计算时间差
     *
     * @param fromTime
     * @param toTime
     * @throws ParseException
     */
    public static void calculateTimeDifference(String fromTime, String toTime) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date parse = format.parse(fromTime);
        Date date = format.parse(toTime);
        long between = date.getTime() - parse.getTime();
        long day = between / (24 * 60 * 60 * 1000);
        long hour = (between / (60 * 60 * 1000) - day * 24);
        long min = ((between / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (between / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        Log.d(TAG, "calculateTimeDifference: " + day + "天" + hour + "小时" + min + "分" + s + "秒");
    }


    /**
     * 计算时间差
     *
     * @param beginTime "2020-05-22 12:40:15"
     * @param endTime   "2020-05-22 14:49:15"
     */
    public static TimeDifference timeDifference(String beginTime, String endTime) {
        Log.d(TAG, "timeDifference: " + beginTime + ", " + endTime);
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        TimeDifference timeDifference = null;
        long between = 0;
        try {
            Date begin = dfs.parse(beginTime);
            Date end = dfs.parse(endTime);
            between = (end.getTime() - begin.getTime());// 得到两者的毫秒数
            long day = between / (24 * 60 * 60 * 1000);
            long hour = (between / (60 * 60 * 1000) - day * 24);
            long min = ((between / (60 * 1000)) - day * 24 * 60 - hour * 60);
            long s = (between / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
            long ms = (between - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
            Log.d(TAG, "timeDifference: " + day + "天" + hour + "小时" + min + "分" + s + "秒" + ms + "毫秒");
            timeDifference = new TimeDifference(day, hour, min, s, ms);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return timeDifference;
    }
}
