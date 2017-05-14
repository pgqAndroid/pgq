package com.gangpeng.pgframe.util;

import android.util.Log;

/**
 * className: L
 * function: 日志工具类
 * <p>
 * created at 16/7/7 11:29
 *
 * @author pg
 */
public class L {
    /**
     * 是否需要打印log
     */
    public static boolean sDebug = true;

    /**
     * 默认tag
     */
    private static String sTAG = "pgq";

    /**
     * 不允许实例化对象
     */
    private L() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 自定义debug模式，tag
     *
     * @param debug
     * @param tag
     */
    public static void init(boolean debug, String tag) {
        L.sDebug = debug;
        L.sTAG = tag;
    }

    /**
     * verbose级别错误
     *
     * @param msg
     */
    public static void v(String msg) {
        if (sDebug) {
            L.v(sTAG, msg);
        }
    }

    /**
     * debug级别错误
     *
     * @param msg
     */
    public static void d(String msg) {
        if (sDebug) {
            L.d(sTAG, msg);
        }
    }

    /**
     * info级别错误
     *
     * @param msg
     */
    public static void i(String msg) {
        if (sDebug) {
            L.i(sTAG, msg);
        }
    }

    /**
     * warning级别错误
     *
     * @param msg
     */
    public static void w(String msg) {
        if (sDebug) {
            L.w(sTAG, msg);
        }
    }

    /**
     * error级别错误
     *
     * @param msg
     */
    public static void e(String msg) {
        if (sDebug) {
            L.e(sTAG, msg);
        }
    }

    /**
     * verbose错误
     *
     * @param tag
     * @param msg
     */
    private static void v(String tag, String msg) {
        if (!sDebug) {
            return;
        }
        String finalTag = getFinalTag(tag);
        StackTraceElement targetStackTraceElement = getTargetStackTraceElement();
        Log.v(finalTag, "---------------------------------------------------");
        Log.v(finalTag, "(" + targetStackTraceElement.getFileName() + ":"
                + targetStackTraceElement.getLineNumber() + ")");
        Log.v(finalTag, msg);
        Log.v(finalTag, "---------------------------------------------------");
    }

    /**
     * debug错误
     *
     * @param tag
     * @param msg
     */
    private static void d(String tag, String msg) {
        if (!sDebug) {
            return;
        }
        String finalTag = getFinalTag(tag);
        StackTraceElement targetStackTraceElement = getTargetStackTraceElement();
        Log.d(finalTag, "---------------------------------------------------");
        Log.d(finalTag, "(" + targetStackTraceElement.getFileName() + ":"
                + targetStackTraceElement.getLineNumber() + ")");
        Log.d(finalTag, msg);
        Log.d(finalTag, "---------------------------------------------------");
    }

    /**
     * info错误
     *
     * @param tag
     * @param msg
     */
    private static void i(String tag, String msg) {
        if (!sDebug) {
            return;
        }
        String finalTag = getFinalTag(tag);
        StackTraceElement targetStackTraceElement = getTargetStackTraceElement();
        Log.i(finalTag, "---------------------------------------------------");
        Log.i(finalTag, "(" + targetStackTraceElement.getFileName() + ":"
                + targetStackTraceElement.getLineNumber() + ")");
        Log.i(finalTag, msg);
        Log.i(finalTag, "---------------------------------------------------");
    }

    /**
     * warning错误
     *
     * @param tag
     * @param msg
     */
    private static void w(String tag, String msg) {
        if (!sDebug) {
            return;
        }
        String finalTag = getFinalTag(tag);
        StackTraceElement targetStackTraceElement = getTargetStackTraceElement();
        Log.w(finalTag, "---------------------------------------------------");
        Log.w(finalTag, "(" + targetStackTraceElement.getFileName() + ":"
                + targetStackTraceElement.getLineNumber() + ")");
        Log.w(finalTag, msg);
        Log.w(finalTag, "---------------------------------------------------");
    }

    /**
     * error错误
     *
     * @param tag
     * @param msg
     */
    private static void e(String tag, String msg) {
        if (!sDebug) {
            return;
        }
        String finalTag = getFinalTag(tag);
        StackTraceElement targetStackTraceElement = getTargetStackTraceElement();
        Log.e(finalTag, "---------------------------------------------------");
        Log.e(finalTag, "(" + targetStackTraceElement.getFileName() + ":"
                + targetStackTraceElement.getLineNumber() + ")");
        Log.e(finalTag, msg);
        Log.e(finalTag, "---------------------------------------------------");
    }


    /**
     * 获取tag名称
     *
     * @param str
     * @return
     */
    private static String getFinalTag(String str) {
        if (str == null || "".equals(str.trim())) {
            return sTAG;
        }
        return str;
    }

    /**
     * 调用系统方法，后去log调用的类和行号
     *
     * @return
     */
    private static StackTraceElement getTargetStackTraceElement() {
        StackTraceElement targetStackTrace = null;
        boolean shouldTrace = false;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            boolean isLogMethod = stackTraceElement.getClassName().equals(L.class.getName());
            if (shouldTrace && !isLogMethod) {
                targetStackTrace = stackTraceElement;
                break;
            }
            shouldTrace = isLogMethod;
        }
        return targetStackTrace;
    }
}
