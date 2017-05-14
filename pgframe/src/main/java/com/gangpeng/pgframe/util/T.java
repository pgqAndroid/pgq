package com.gangpeng.pgframe.util;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

/**
 * className: T
 * function: 弹出框工具类
 * <p/>
 * created at 16/7/7 14:29
 *
 * @author pg
 */
public class T {

    /**
     * 是否显示toast
     */
    public static boolean isShow = true;

    /**
     * 不允许实例化对象
     */
    private T() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, CharSequence message) {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, int message) {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, CharSequence message) {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, int message) {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param message
     * @param duration
     */
    public static void show(Context context, CharSequence message, int duration) {
        if (isShow)
            Toast.makeText(context, message, duration).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param message
     * @param duration
     */
    public static void show(Context context, int message, int duration) {
        if (isShow)
            Toast.makeText(context, message, duration).show();
    }


    private static Toast mToast;
    private static Handler mHandler = new Handler();
    private static Runnable r = new Runnable() {
        public void run() {
            T.mToast.cancel();
        }
    };

    /**
     * 不重复显示toast
     *
     * @param context
     * @param text
     * @param duration
     */
    public static void showToastNotRepeat(Context context, String text, int duration) {
        mHandler.removeCallbacks(r);
        if (mToast != null) {
            mToast.setText(text);
        } else {
            mToast = Toast.makeText(context.getApplicationContext(), text, duration);
        }

        mHandler.postDelayed(r, (long) duration);
        mToast.show();
    }

    /**
     * 不重复显示toast
     *
     * @param context
     * @param resourceId
     * @param duration
     */
    public static void showToastNotRepeat(Context context, int resourceId, int duration) {
        showToastNotRepeat(context, context.getResources().getString(resourceId), duration);
    }
}
