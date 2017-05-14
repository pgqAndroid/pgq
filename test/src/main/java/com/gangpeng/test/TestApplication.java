package com.gangpeng.test;

import android.app.Application;
import android.os.Process;
import android.util.Log;

/**
 * Created by gangpeng on 16/7/14.
 */
public class TestApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("pgq", "process: " + Process.myPid());
    }
}
