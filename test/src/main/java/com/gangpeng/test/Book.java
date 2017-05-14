package com.gangpeng.test;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by gangpeng on 16/7/14.
 */
public class Book implements Parcelable {

    private String name;
    private int bookId;

    public Book() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public static Creator<Book> getCREATOR() {
        return CREATOR;
    }

    protected Book(Parcel in) {
        name = in.readString();
        bookId = in.readInt();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(bookId);
    }
}
