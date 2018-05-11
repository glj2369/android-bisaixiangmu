package com.example.jun.bisaixiangmu.bean;

import android.support.annotation.NonNull;

public class DengBean {
    private int id;
    private int hong;
    private int lv;
    private int huang;

    public DengBean() {
    }

    public DengBean(int id, int hong, int lv, int huang) {
        this.id = id;
        this.hong = hong;
        this.lv = lv;
        this.huang = huang;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHong() {
        return hong;
    }

    public void setHong(int hong) {
        this.hong = hong;
    }

    public int getLv() {
        return lv;
    }

    public void setLv(int lv) {
        this.lv = lv;
    }

    public int getHuang() {
        return huang;
    }

    public void setHuang(int huang) {
        this.huang = huang;
    }


}
