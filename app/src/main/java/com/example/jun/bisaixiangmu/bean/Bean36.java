package com.example.jun.bisaixiangmu.bean;

public class Bean36 {

    private int bgColorInt;
    private int timeInt;
    private String timeString;
    private int image;
    private String tianqi;
    private int maxWendu;
    private int minWendu;

    public Bean36(int bgColorInt, int timeInt, String timeString, int image, String tianqi, int maxWendu, int minWendu) {
        this.bgColorInt = bgColorInt;
        this.timeInt = timeInt;
        this.timeString = timeString;
        this.image = image;
        this.tianqi = tianqi;
        this.maxWendu = maxWendu;
        this.minWendu = minWendu;
    }

    public int getBgColorInt() {
        return bgColorInt;
    }

    public void setBgColorInt(int bgColorInt) {
        this.bgColorInt = bgColorInt;
    }

    public int getTimeInt() {
        return timeInt;
    }

    public void setTimeInt(int timeInt) {
        this.timeInt = timeInt;
    }

    public String getTimeString() {
        return timeString;
    }

    public void setTimeString(String timeString) {
        this.timeString = timeString;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTianqi() {
        return tianqi;
    }

    public void setTianqi(String tianqi) {
        this.tianqi = tianqi;
    }

    public int getMaxWendu() {
        return maxWendu;
    }

    public void setMaxWendu(int maxWendu) {
        this.maxWendu = maxWendu;
    }

    public int getMinWendu() {
        return minWendu;
    }

    public void setMinWendu(int minWendu) {
        this.minWendu = minWendu;
    }
}
