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
import com.gangpeng.ipc.aidl.Book;
import com.gangpeng.ipc.aidl.IBookManager;
import com.gangpeng.ipc.server.AidlService;

import java.util.List;

public class Aidl2Activity extends Activity {

    private IBookManager bookManager;

    private TextView addBookTv;

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

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl2);

        addBookTv = (TextView) findViewById(R.id.tv_add_book);

        Intent intent = new Intent(this, AidlService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

        addBookTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book = new Book();
                try {
                    int bookId = bookManager.getBookList().size();
                    book.setBookId(bookId);
                    book.setBookName("" + bookId);
                    bookManager.addBook(book);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        unbindService(serviceConnection);
        super.onDestroy();
    }
}
