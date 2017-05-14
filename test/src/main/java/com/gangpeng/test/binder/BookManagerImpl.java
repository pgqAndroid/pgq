package com.gangpeng.test.binder;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

import com.gangpeng.test.Book;

import java.util.List;

/**
 * Created by gangpeng on 16/7/15.
 */
public class BookManagerImpl extends Binder implements IBookManager {

    public BookManagerImpl() {
        this.attachInterface(this, descriptor);
    }


    public static IBookManager asInterface(IBinder obj) {
        if (obj == null) {
            return null;
        }
        IInterface iin = obj.queryLocalInterface(descriptor);
        if ((iin != null) && (iin instanceof IBookManager)) {
            return (IBookManager) iin;
        }
        return new Proxy(obj);
    }

    @Override
    public List<Book> getAllBook() throws RemoteException {
        return null;
    }

    @Override
    public void addBook(Book book) throws RemoteException {

    }

    @Override
    public IBinder asBinder() {
        return this;
    }

    @Override
    protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
        switch (code) {
            case INTERFACE_TRANSACTION:
                reply.writeString(descriptor);
                return true;
            case transaction_addBook:
                data.enforceInterface(descriptor);
                Book arg0;
                if (0 != data.readInt()) {
                    arg0 = Book.CREATOR.createFromParcel(data);
                } else {
                    arg0 = null;
                }
                this.addBook(arg0);
                reply.writeNoException();
                return true;
            case transaction_getBookList:
                data.enforceInterface(descriptor);
                List<Book> result = this.getAllBook();
                reply.writeNoException();
                reply.writeTypedList(result);
                return true;
        }
        return super.onTransact(code, data, reply, flags);
    }

    private static class Proxy implements IBookManager {

        public String getInterfaceDescriptor() {
            return descriptor;
        }

        private IBinder iBinder;

        public Proxy(IBinder iBinder) {
            this.iBinder = iBinder;
        }

        @Override
        public List<Book> getAllBook() throws RemoteException {
            Parcel data = Parcel.obtain();
            Parcel reply = Parcel.obtain();
            List<Book> result;
            try {
                data.writeInterfaceToken(descriptor);
                iBinder.transact(transaction_getBookList, data, reply, 0);
                reply.readException();
                result = reply.createTypedArrayList(Book.CREATOR);
            } finally {
                reply.recycle();
                data.recycle();
            }
            return result;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            Parcel data = Parcel.obtain();
            Parcel reply = Parcel.obtain();
            try {
                data.writeInterfaceToken(descriptor);
                if (book != null) {
                    data.writeInt(1);
                    book.writeToParcel(data, 0);
                } else {
                    data.writeInt(0);
                }
                iBinder.transact(transaction_addBook, data, reply, 0);
                reply.readException();
            } finally {
                reply.recycle();
                data.recycle();
            }
        }

        @Override
        public IBinder asBinder() {
            return iBinder;
        }
    }

}
