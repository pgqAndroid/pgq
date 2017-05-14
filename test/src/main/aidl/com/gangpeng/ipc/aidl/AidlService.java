package com.gangpeng.ipc.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by gangpeng on 16/8/15.
 */
public class AidlService extends Service {

    private CopyOnWriteArrayList<Book> bookList = new CopyOnWriteArrayList<Book>();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Book book1 = new Book(0, "0");
        Book book2 = new Book(1, "1");
        bookList.add(book1);
        bookList.add(book2);
    }

    IBinder iBinder = new IBookManager.Stub() {

        @Override
        public List<Book> getBookList() throws RemoteException {
            Log.d("pg", "server bookList size : " + bookList.size());
            return bookList;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            Log.d("pg", "server new book : " + book.getBookName());
            bookList.add(book);
        }
    };

}
