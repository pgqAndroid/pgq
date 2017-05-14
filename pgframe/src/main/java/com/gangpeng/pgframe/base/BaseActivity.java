package com.gangpeng.pgframe.base;

import android.app.Activity;
import android.os.Bundle;

import com.gangpeng.pgframe.frame.ActivityTask;

/**
 * className: BaseActivity
 * function: activity基类
 * <p/>
 * created at 16/6/28 15:23
 *
 * @author pg
 */
public class BaseActivity extends Activity {

    /**
     * activity管理器
     */
    private ActivityTask activityTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTask = ActivityTask.getInstance();
        activityTask.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityTask.removeActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
