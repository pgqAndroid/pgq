package com.gangpeng.pgframe.frame;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * className: ActivityTask
 * function: activity管理类
 * <p/>
 * created at
 *
 * @author pg
 */
public class ActivityTask {

    private List<Activity> activityList;

    private ActivityTask() {
        activityList = new ArrayList<>();
    }

    /**
     * 获取对象实例
     *
     * @return
     */
    public static ActivityTask getInstance() {
        return ActivityTaskHolder.activityTask;
    }

    /**
     * 可以有效防止多线程访问造成的错误
     */
    private static class ActivityTaskHolder {
        private static final ActivityTask activityTask = new ActivityTask();
    }

    /**
     * 添加activity
     *
     * @param activity
     */
    public boolean addActivity(Activity activity) {
        if (activity == null) {
            Log.e("pg", "ActivityTask addActivity() 参数为空");
            return false;
        }
        if (activityList == null) {
            Log.e("pg", "ActivityTask addActivity() list对象为空");
            return false;
        }
        return activityList.add(activity);
    }

    /**
     * 移除activity
     *
     * @param activity
     * @return
     */
    public boolean removeActivity(Activity activity) {
        if (activity == null) {
            Log.e("pg", "ActivityTask removeActivity() 参数为空");
            return false;
        }
        if (activityList == null) {
            Log.e("pg", "ActivityTask removeActivity() list对象为空");
            return false;
        }
        return activityList.remove(activity);
    }

    /**
     * 判断activity是否在列表中
     *
     * @param activity
     * @return
     */
    public boolean hasActivity(Activity activity) {
        if (activity == null) {
            Log.e("pg", "ActivityTask hasActivity() 参数为空");
            return false;
        }
        if (activityList == null) {
            Log.e("pg", "ActivityTask hasActivity() list对象为空");
            return false;
        }
        Iterator<Activity> iterator = activityList.iterator();
        while (iterator.hasNext()) {
            if (activity.equals(iterator.next())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 退出程序
     */
    public void quit() {
        if (activityList == null) {
            Log.e("pg", "ActivityTask quit() list对象为空");
            return;
        }
        Iterator<Activity> iterator = activityList.iterator();
        while (iterator.hasNext()) {
            iterator.next().finish();
        }
    }

    /**
     * 彻底退出程序
     */
    public void exit() {
        quit();
        System.exit(0);
    }
}
