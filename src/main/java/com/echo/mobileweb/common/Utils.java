package com.echo.mobileweb.common;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Utils {
    /**
     * 环比增长率=（本期数-上期数）/上期数×100%
     * @param a 本期数
     * @param b 上期数
     * @return
     */
    public static String hb(BigDecimal a,BigDecimal b){
        if (a.compareTo(new BigDecimal(0))==0||b.compareTo(new BigDecimal(0))==0){
            return "0.00";
        }
        NumberFormat percent = NumberFormat.getPercentInstance();
        percent.setMaximumFractionDigits(2);
        BigDecimal hb = a.subtract(b).divide(b,4,BigDecimal.ROUND_HALF_UP);
        System.out.println( "环比： "+hb);
        return percent.format(hb);
    }

    /**
     * （本期数－同期数）÷同期数×100%
     * @param a 本期数
     * @param b 同期数
     * @return
     */
    public static String tb(BigDecimal a,BigDecimal b){
        if (a.compareTo(new BigDecimal(0))==0||b.compareTo(new BigDecimal(0))==0){
            return "0.00";
        }
        b = b.abs();
        NumberFormat percent = NumberFormat.getPercentInstance();
        percent.setMaximumFractionDigits(2);
        BigDecimal tb =  a.subtract(b).divide(b,4,BigDecimal.ROUND_HALF_UP);
        System.out.println("同比: "+tb);
        return percent.format(tb);

    }

    /**
     * 完成率（%）= 实际完成数bai / 计划完成du数 * 100%
     * @param a 实际完成数
     * @param b 计划完成数
     * @return
     */
    public static String wcl(BigDecimal a,BigDecimal b){
        if (a.compareTo(new BigDecimal(0))==0||b.compareTo(new BigDecimal(0))==0){
            return "0.00";
        }
        NumberFormat percent = NumberFormat.getPercentInstance();
        percent.setMaximumFractionDigits(2);
        BigDecimal wcl =   a.divide(b,4,BigDecimal.ROUND_HALF_UP);
        System.out.println("完成率： "+ wcl);
        return percent.format(wcl);
    }

    /**
     * <h1>获取去年今天的日期</h1>
     * @param date 日期 yyyy-MM-dd
     * @return
     * @throws ParseException
     */
    public  static  String getNowOfLastYear(String date) {
        // Date Format will be display

        SimpleDateFormat aSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = new Date();
        try {
            date1 = aSimpleDateFormat.parse(date);
        }catch (ParseException e){
            e.printStackTrace();
        }

        GregorianCalendar aGregorianCalendar = new GregorianCalendar();
        // Get last month GregorianCalendar object
        aGregorianCalendar.setTime(date1);
        aGregorianCalendar.set(Calendar.YEAR, aGregorianCalendar.get(Calendar.YEAR) - 1);
        // Format the date to get year and month
        String currentYearAndMonth = aSimpleDateFormat.format(aGregorianCalendar.getTime());
        return currentYearAndMonth;
    }

    public static String getNowOfLastMonth(String date){
        SimpleDateFormat aSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = new Date();
        try {
            date1 = aSimpleDateFormat.parse(date);
        }catch (ParseException e){
            e.printStackTrace();
        }
        GregorianCalendar aGregorianCalendar = new GregorianCalendar();
        aGregorianCalendar.setTime(date1);
        aGregorianCalendar.set(Calendar.MONTH, aGregorianCalendar.get(Calendar.MONTH) - 1);
        return aSimpleDateFormat.format(aGregorianCalendar.getTime());
    }
    public static void main(String[] args) {
//        System.out.println(hb(new BigDecimal(10),new BigDecimal(3)));
//        System.out.println(tb(new BigDecimal(300),new BigDecimal(100)));

            System.out.println(getNowOfLastMonth("2019-12-12"));


    }
}
