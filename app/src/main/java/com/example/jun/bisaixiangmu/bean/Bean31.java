package com.example.jun.bisaixiangmu.bean;

public class Bean31 {
    private String title;
    private String content;
    private String phone;
    private String save_time;

    public Bean31(String title, String content, String phone, String save_time) {
        this.title = title;
        this.content = content;
        this.phone = phone;
        this.save_time = save_time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return phone;
    }

    public void setTime(String time) {
        this.phone = time;
    }

    public String getSave_time() {
        return save_time;
    }

    public void setSave_time(String save_time) {
        this.save_time = save_time;
    }
}
