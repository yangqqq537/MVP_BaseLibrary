package com.mecby.base.utils;

import android.util.Log;

import java.util.Locale;

/**
 * @author listen
 * @desc 日志工具类
 */
public class Logger {
    public static String TAG = "Logger";

    private static final String BOTTOM_LEFT = "║";
    private static final String BOTTOM_BORDER = "╚═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════";

    public static boolean DEBUG = true;

    public static void v(String format, Object... args) {
        Log.v(TAG, buildMessage(format, args));
    }


    public static void d() {
        d("", "");
    }

    public static void d(String format, Object... args) {
        if (!DEBUG) {
            return;
        }
        Log.d(TAG,  BOTTOM_LEFT + buildMessage(format, args) + "\n" + BOTTOM_BORDER);
    }

    public static void e(String format, Object... args) {
        Log.e(TAG, buildMessage(format, args));
    }

    public static void e(Throwable tr, String format, Object... args) {
        Log.e(TAG, buildMessage(format, args), tr);
    }

    public static void wtf(String format, Object... args) {
        Log.wtf(TAG, buildMessage(format, args));
    }

    public static void wtf(Throwable tr, String format, Object... args) {
        Log.wtf(TAG, buildMessage(format, args), tr);
    }

    /**
     * Formats the caller's provided message and prepends useful info like
     * calling thread ID and submitReport name.
     */
    private static String buildMessage(String format, Object... args) {
        String msg = (args == null) ? format : String.format(Locale.US, format, args);
        // Walk up the stack looking for the first caller outside of Logger.
        // It will be at least two frames up, so start there.
        if (DEBUG) {
            /** debug模式下才获取method，release混淆时找不到method名称 */
            StackTraceElement[] trace = new Throwable().fillInStackTrace().getStackTrace();
            String caller = "<unknown>";
            for (int i = 2; i < trace.length; i++) {
                Class<?> clazz = trace[i].getClass();
                if (!clazz.equals(Logger.class)) {
                    String callingClass = trace[i].getClassName();
                    callingClass = callingClass.substring(callingClass.lastIndexOf('.') + 1);
                    callingClass = callingClass.substring(callingClass.lastIndexOf('$') + 1);

                    caller = callingClass + "." + trace[i].getMethodName();
                    break;
                }
            }
            return String.format(Locale.US,"[%d] %s: %s", Thread.currentThread().getId(), caller, msg);
        } else {
            return String.format(Locale.US,"[%d]: %s", Thread.currentThread().getId(), msg);
        }
    }

}
