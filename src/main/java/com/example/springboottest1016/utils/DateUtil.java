package com.example.springboottest1016.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

public class DateUtil {
    public static final String STANDARD = "yyyy-MM-dd HH:mm:ss";

    public static Date String2Data(String strData) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(STANDARD);
        DateTime dateTime = dateTimeFormatter.parseDateTime(strData);
        return dateTime.toDate();
    }
    public static Date String2Data(String strData,String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(format);
        DateTime dateTime = dateTimeFormatter.parseDateTime(strData);
        return dateTime.toDate();
    }

    public static String Date2String(Date date) {
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(STANDARD);
    }
    public static String Date2String(Date date,String format) {
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(format);
    }
}
