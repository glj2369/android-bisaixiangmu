package com.example.jun.bisaixiangmu.bean;

public class Bean3 {
    private int chehao;
    private int jine;
    private String person;
    private String time;

    public Bean3() {
    }

    public Bean3( int chehao, int jine, String person, String time) {
        this.chehao = chehao;
        this.jine = jine;
        this.person = person;
        this.time = time;
    }



    public int getChehao() {
        return chehao;
    }

    public void setChehao(int chehao) {
        this.chehao = chehao;
    }

    public int getJine() {
        return jine;
    }

    public void setJine(int jine) {
        this.jine = jine;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
