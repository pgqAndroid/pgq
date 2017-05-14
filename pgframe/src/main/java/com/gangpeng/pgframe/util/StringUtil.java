package com.gangpeng.pgframe.util;

/**
 * className: StringUtil
 * function: 字符串工具类
 * <p/>
 * created at 16/7/7 15:46
 *
 * @author pg
 */
public class StringUtil {

    /**
     * 是否为空
     *
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     * 是否不为空
     *
     * @param str
     * @return
     */
    public static boolean isNotBlank(String str) {
        return str != null && str.trim().length() != 0;
    }
}
