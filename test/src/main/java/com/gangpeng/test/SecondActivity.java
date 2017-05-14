package com.gangpeng.test;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class SecondActivity extends Activity {

    CustomView settingCv;

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secend);

        Intent serviceIntent = new Intent(this, TestService.class);
        bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE);

        settingCv = (CustomView) findViewById(R.id.cv_setting);
        settingCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                intent.putExtra("test", "aahhhha");
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        if (intent != null) {
            Log.d("pgq", intent.getStringExtra("test"));
            PgqBean pgqBean = intent.getParcelableExtra("parcelable");
            Log.d("pgq", new Gson().toJson(pgqBean));
        }

        Log.d("pgq", "oncreate");
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
