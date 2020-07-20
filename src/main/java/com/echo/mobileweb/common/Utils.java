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


        SimpleDateFormat aSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = new Date();
        try {
            date1 = aSimpleDateFormat.parse(date);
        }catch (ParseException e){
            e.printStackTrace();
        }

        GregorianCalendar aGregorianCalendar = new GregorianCalendar();

        aGregorianCalendar.setTime(date1);
        aGregorianCalendar.set(Calendar.YEAR, aGregorianCalendar.get(Calendar.YEAR) - 1);

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

    /**
     * <h1>获取当月的第一天时间</h1>
     * @param datadate
     * @return
     * @throws Exception
     */
    public static String getFirstDay(String datadate)throws Exception{
        Date date = null;
        String day_first = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        date = format.parse(datadate);

        //创建日历
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        day_first = format.format(calendar.getTime());
        return day_first;
    }

    /**
     * <h1>计算日期间隔天数</h1>
     * @param start
     * @param end
     * @return
     * @throws ParseException
     */
    public static Long DaySubtractNum(String start,String end) throws ParseException {

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");

        Date d1=sdf.parse(start);

        Date d2=sdf.parse(end);

        long daysBetween=(d2.getTime()-d1.getTime()+1000000)/(60*60*24*1000);

        System.out.println(start+" 与 "+end+" 相隔 "+daysBetween+" 天");
        return Math.abs(daysBetween)+1;
    }
    public static void main(String[] args) {
        try {
            System.out.println(DaySubtractNum("2019-12-15","2019-12-01"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
