package com.example.jun.bisaixiangmu.bean;

public class MenuList {
    private int imageId;
    private String title;

    public MenuList() {
    }

    public MenuList(int imageId, String title) {
        this.imageId = imageId;
        this.title = title;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
