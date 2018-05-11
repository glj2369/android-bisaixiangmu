package com.example.jun.bisaixiangmu.bean;

import java.io.Serializable;

public class GridViewBean implements Serializable {
    private String title;
    private int zhi;
    private int resColor;//zhibiao zhibiao1 zhibiao2

    public GridViewBean(String title, int zhi, int resColor) {
        this.title = title;
        this.zhi = zhi;
        this.resColor = resColor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getZhi() {
        return zhi;
    }

    public void setZhi(int zhi) {
        this.zhi = zhi;
    }

    public int getResColor() {
        return resColor;
    }

    public void setResColor(int resColor) {
        this.resColor = resColor;
    }
}
