package com.example.jun.bisaixiangmu.bean;

public class Bean9 {
    private int imageId;
    private String chePai;
    private String cheZhu;
    private Boolean aBoolean;

    public Boolean getaBoolean() {
        return aBoolean;
    }

    public void setaBoolean(Boolean aBoolean) {
        this.aBoolean = aBoolean;
    }

    private int yuE;

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public Bean9(int imageId, String chePai, String cheZhu, int yuE,Boolean aBoolean) {
        this.imageId = imageId;
        this.chePai = chePai;
        this.cheZhu = cheZhu;
        this.yuE = yuE;
        this.aBoolean = aBoolean;
    }

    public String getChePai() {
        return chePai;
    }

    public void setChePai(String chePai) {
        this.chePai = chePai;
    }

    public String getCheZhu() {
        return cheZhu;
    }

    public void setCheZhu(String cheZhu) {
        this.cheZhu = cheZhu;
    }

    public int getYuE() {
        return yuE;
    }

    public void setYuE(int yuE) {
        this.yuE = yuE;
    }
}
