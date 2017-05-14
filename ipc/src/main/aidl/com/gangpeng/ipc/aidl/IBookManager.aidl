// IBookManager.aidl
package com.gangpeng.ipc.aidl;

// Declare any non-default types here with import statements
import com.gangpeng.ipc.aidl.Book;
import com.gangpeng.ipc.aidl.IOnNewBookArrivedListener;

interface IBookManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
     List<Book> getBookList();
     void addBook(in Book book);
     void registerListener(in IOnNewBookArrivedListener listener);
     void unRegisterListener(in IOnNewBookArrivedListener listener);
}
