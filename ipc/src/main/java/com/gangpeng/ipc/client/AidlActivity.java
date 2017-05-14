package com.gangpeng.ipc.client;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.gangpeng.ipc.R;
import com.gangpeng.ipc.aidl.IOnNewBookArrivedListener;
import com.gangpeng.ipc.server.AidlService;
import com.gangpeng.ipc.aidl.Book;
import com.gangpeng.ipc.aidl.IBookManager;

import java.util.List;

public class AidlActivity extends Activity implements View.OnClickListener {

    private IBookManager bookManager;

    private TextView addBookTv;
    private TextView aidlTv;
    private TextView registerTv;
    private TextView unRegisterTv;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            bookManager = IBookManager.Stub.asInterface(iBinder);
            try {
                List<Book> bookList = bookManager.getBookList();

                Log.d("pg", bookList.toString());
                for (Book book : bookList) {
                    Log.d("pg", book.getBookName());
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d("pg", "aidl client disconnected");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);

        addBookTv = (TextView) findViewById(R.id.tv_add_book);
        aidlTv = (TextView) findViewById(R.id.tv_to_aidl2);
        registerTv = (TextView) findViewById(R.id.tv_register);
        unRegisterTv = (TextView) findViewById(R.id.tv_unregister);

        addBookTv.setOnClickListener(this);
        aidlTv.setOnClickListener(this);
        registerTv.setOnClickListener(this);
        unRegisterTv.setOnClickListener(this);

        Intent intent = new Intent(this, AidlService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

    }

    @Override
    protected void onDestroy() {

        if (bookManager != null && bookManager.asBinder().isBinderAlive()) {
            try {
                Log.d("pg", "aidl register listener");
                bookManager.unRegisterListener(listener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        unbindService(serviceConnection);
        super.onDestroy();
    }

    private IOnNewBookArrivedListener listener = new IOnNewBookArrivedListener.Stub() {
        @Override
        public void onNewBookArrived(Book book) throws RemoteException {
            addBookTv.setText("addbooktv");
            Log.d("pg", "aidl activity receive a new book :" + book.getBookName());
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_add_book:
                Book book = new Book();
                try {
                    int bookId = bookManager.getBookList().size();
                    book.setBookId(bookId);
                    book.setBookName("" + bookId);
                    bookManager.addBook(book);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.tv_to_aidl2:
                Intent intent = new Intent(AidlActivity.this, Aidl2Activity.class);
                startActivity(intent);
                break;
            case R.id.tv_register:
                try {
                    Log.d("pg", "aidl register listener");
                    bookManager.registerListener(listener);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.tv_unregister:
                try {
                    Log.d("pg", "aidl unregister listener");
                    bookManager.unRegisterListener(listener);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }
}
