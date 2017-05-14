package com.gangpeng.ipc.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.gangpeng.ipc.aidl.Book;
import com.gangpeng.ipc.aidl.IBookManager;
import com.gangpeng.ipc.aidl.IOnNewBookArrivedListener;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by gangpeng on 16/8/15.
 */
public class AidlService extends Service {

    private CopyOnWriteArrayList<Book> bookList = new CopyOnWriteArrayList<Book>();

    private RemoteCallbackList<IOnNewBookArrivedListener> listenerList
            = new RemoteCallbackList<>();

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
            Log.d("pg", "server add a new book : " + book.getBookName());
            bookList.add(book);
            int num = listenerList.beginBroadcast();
            Log.d("pg", "server listener num : " + num);
            for (int i = 0; i < num; i++) {
                IOnNewBookArrivedListener listener = listenerList.getBroadcastItem(i);
                if(listener!=null) {
                    listener.onNewBookArrived(book);
                }
            }
            listenerList.finishBroadcast();
        }

        @Override
        public void registerListener(IOnNewBookArrivedListener listener) throws RemoteException {
            listenerList.register(listener);
        }

        @Override
        public void unRegisterListener(IOnNewBookArrivedListener listener) throws RemoteException {
            listenerList.unregister(listener);
        }
    };

}
