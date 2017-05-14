package com.gangpeng.test;

import java.io.Serializable;

/**
 * Created by gangpeng on 16/7/14.
 */
public class PgBean implements Serializable {
    private static final long serialVersionUID = 1l;

    private String name;
    private String nation;
    private int age;
    private String gander;

    public String getGander() {
        return gander;
    }

    public void setGander(String gander) {
        this.gander = gander;
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

//    public int getAge() {
//        return age;
//    }

//    public void setAge(int age) {
//        this.age = age;
//    }

    @Override
    public String toString() {
        return "name: " + name + ", age:" + 0 + ", nation:" + nation;
    }
}
