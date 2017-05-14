// IOnNewBookArrivedListener.aidl
package com.gangpeng.ipc.aidl;

// Declare any non-default types here with import statements
import com.gangpeng.ipc.aidl.Book;

interface IOnNewBookArrivedListener {
    void onNewBookArrived(in Book book);
}
