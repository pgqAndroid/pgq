package com.gangpeng.ipc.server;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.gangpeng.ipc.MyConstants;

/**
 * Created by gangpeng on 16/8/7.
 */
public class MessengerService extends Service {

    private static final String TAG = "MessengerService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }

    private static class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MyConstants.MSG_FROM_CLIENT:
                    Log.i("msg", "receive msg from Client: " + msg.getData().getString("msg"));
                    Messenger replyMessenger = msg.replyTo;
                    Message replyMessage = Message.obtain();
                    replyMessage.what = MyConstants.MSG_FROM_SERVER;
                    Bundle data = new Bundle();
                    data.putString("reply", "i will reply to you later.");
                    replyMessage.setData(data);
                    try {
                        replyMessenger.send(replyMessage);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    private final Messenger mMessenger = new Messenger(new MessengerHandler());

}
