package com.gangpeng.pgframe.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

/**
 * className: AppUtil
 * function: App相关辅助类
 * <p/>
 * created at 16/7/7 14:50
 *
 * @author pg
 */
public class AppUtil {
    /**
     * 不能实例化对象
     */
    private AppUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");

    }

    /**
     * 获取当前应用程序名称
     */
    public static String getAppName(Context context) {
        PackageManager packageManager = null;
        ApplicationInfo applicationInfo = null;
        try {
            packageManager = context.getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            applicationInfo = null;
        }
        String applicationName =
                (String) packageManager.getApplicationLabel(applicationInfo);
        return applicationName;
    }

    /**
     * 返回当前程序版本号
     *
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_CONFIGURATIONS);
            return pi.versionCode;
        } catch (Exception e) {
            Log.e("AppUtil", "Exception", e);
            return 0;
        }

    }

    /**
     * 返回当前程序版本名称
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_CONFIGURATIONS);
            return pi.versionName;
        } catch (Exception e) {
            Log.e("AppUtil", "Exception", e);
            return "";
        }

    }

    /**
     * 判断是否安装了某程序
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isInstalledApp(Context context, String packageName) {
        try {
            PackageManager pm = context.getPackageManager();
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
        return true;
    }
}
