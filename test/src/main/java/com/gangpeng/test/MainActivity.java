package com.gangpeng.test;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.support.v4.view.ViewPager;
import android.telecom.ConnectionService;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.gangpeng.pgframe.util.FileUtil;
import com.google.gson.Gson;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.List;


public class MainActivity extends Activity {

    CustomView settingCv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("pg", String.valueOf(BuildConfig.BUILD_CONTANT));

        settingCv = (CustomView) findViewById(R.id.cv_setting);
        settingCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.addCategory("com.gangpeng.category");
                intent.setDataAndType(Uri.parse("pg:abc"), "pg/jpg");
                intent.putExtra("test", "ahahahahahhahah");
                PgqBean pgqBean = new PgqBean();
                pgqBean.setName("pg");
                pgqBean.setNation("nation");
                pgqBean.setAge(123);
                intent.putExtra("parcelable", pgqBean);
                List<ResolveInfo> list = MainActivity.this.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
                if (!list.isEmpty()) {
                    startActivity(intent);
                }
            }
        });
        Log.d("pgq", "oncreate");

        try{
            String imagePath = FileUtil.getSdPath()+"aaa.jpg";



            Bitmap bmp = BitmapFactory.decodeStream(new URL(imagePath).openStream());
            Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, 150, 150, true);
            bmp.recycle();

        } catch(Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "startshare4", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("pgq", "start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("pgq", "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("pgq", "onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("pgq", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("pgq", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("pgq", "onDestroy");
    }
}
