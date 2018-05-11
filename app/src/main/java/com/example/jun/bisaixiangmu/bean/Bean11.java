package com.example.jun.bisaixiangmu.bean;

public class Bean11  {
    private int id;
    private int hong;
    private int lv;
    private int huang;
    private boolean checked;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Bean11(int id, int hong, int lv, int huang, boolean checked) {
        this.id = id;
        this.hong = hong;
        this.lv = lv;
        this.huang = huang;
        this.checked = checked;
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

