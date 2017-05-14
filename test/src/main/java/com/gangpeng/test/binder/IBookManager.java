package com.gangpeng.test.binder;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

import com.gangpeng.test.Book;

import java.util.List;

/**
 * Created by gangpeng on 16/7/15.
 */
public interface IBookManager extends IInterface {

    public static final String descriptor = "com.gangpeng.test.binder";

    public static final int transaction_getBookList = IBinder.FIRST_CALL_TRANSACTION + 0;
    public static final int transaction_addBook = IBinder.FIRST_CALL_TRANSACTION + 1;

    public List<Book> getAllBook() throws RemoteException;

    public void addBook(Book book) throws RemoteException;

}
