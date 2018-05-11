package com.example.jun.bisaixiangmu.bean;

import java.io.Serializable;

public class GridViewBean17 implements Serializable {
    private String title;
    private String zhi;
    private int resColor;//zhibiao zhibiao1 zhibiao2
    private int imageId;
    private String imageMiaoshu;

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getImageMiaoshu() {
        return imageMiaoshu;
    }

    public void setImageMiaoshu(String imageMiaoshu) {
        this.imageMiaoshu = imageMiaoshu;
    }

    public GridViewBean17(String zhi, String title , int resColor, int imageId, String imageMiaoshu) {
        this.title = title;
        this.zhi = zhi;
        this.resColor = resColor;
        this.imageId = imageId;
        this.imageMiaoshu = imageMiaoshu;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getZhi() {
        return zhi;
    }

    public void setZhi(String zhi) {
        this.zhi = zhi;
    }

    public int getResColor() {
        return resColor;
    }

    public void setResColor(int resColor) {
        this.resColor = resColor;
    }
}
