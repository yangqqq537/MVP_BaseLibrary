package com.mecby.base.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Author:Jerry
 * Time:2017/11/2 0002   11:43
 * Des:
 */

public class DateUtils {
    private static final SimpleDateFormat DATE_FORMAT =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    //private static final TimeZone DEFAULT_TIMEZONE = TimeZone.getTimeZone("GMT+8");

    public static final String PATTERN_YEAR = "yyyy";
    public static final String PATTERN_MONTH = "yyyy-MM";
    public static final String PATTERN_DAY = "yyyy-MM-dd";
    public static final String PATTERN_MINUTES = "yyyy-MM-dd HH:mm";
    public static final String PATTERN_SECONDS = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN_MONTH_TO_SECONDS = "MM-dd HH:mm:ss";
    public static final String PATTERN_HOURS_TO_MINUTES = "HH:mm";
    public static final String PATTERN_MONTHS_TO_DAYS = "MM-dd";

    public static String timeToDate(String millis, String pattern) {
        DATE_FORMAT.applyPattern(pattern);
        try {
            long m=Long.valueOf(millis);
            return DATE_FORMAT.format(new Date(m));
        } catch (Exception e) {
            if (e != null) {
                e.printStackTrace();
            }
            return "";
        }
    }
}
