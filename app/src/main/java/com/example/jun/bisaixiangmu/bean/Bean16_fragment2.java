package com.example.jun.bisaixiangmu.bean;

public class Bean16_fragment2  {
    private String Name;
    private String chePai;
    private int chongZhi;
    private int yuE;
    private String nowTime;

    public Bean16_fragment2(String name, String chePai, int chongZhi, int yuE) {
        Name = name;
        this.chePai = chePai;
        this.chongZhi = chongZhi;
        this.yuE = yuE;

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getChePai() {
        return chePai;
    }

    public void setChePai(String chePai) {
        this.chePai = chePai;
    }

    public int getChongZhi() {
        return chongZhi;
    }

    public void setChongZhi(int chongZhi) {
        this.chongZhi = chongZhi;
    }

    public int getYuE() {
        return yuE;
    }

    public void setYuE(int yuE) {
        this.yuE = yuE;
    }

    public String getNowTime() {
        return nowTime;
    }

    public void setNowTime(String nowTime) {
        this.nowTime = nowTime;
    }
}
