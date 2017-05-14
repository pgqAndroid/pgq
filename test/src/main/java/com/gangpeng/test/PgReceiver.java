package com.gangpeng.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by gangpeng on 16/7/13.
 */
public class PgReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "ahahha", Toast.LENGTH_SHORT).show();
        Log.d("pgq", "ahahahh");
        Intent intent1 = new Intent(context, SecondActivity.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent1);
    }
}
