package com.gangpeng.test;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by gangpeng on 16/7/14.
 */
public class PgqBean implements Parcelable {

    private String name;
    private String nation;
    private int age;

    public PgqBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(nation);
        dest.writeInt(age);
    }

    protected PgqBean(Parcel in) {
        name = in.readString();
        nation = in.readString();
        age = in.readInt();
    }

    public static final Creator<PgqBean> CREATOR = new Creator<PgqBean>() {
        @Override
        public PgqBean createFromParcel(Parcel in) {
            return new PgqBean(in);
        }

        @Override
        public PgqBean[] newArray(int size) {
            return new PgqBean[size];
        }
    };
}

