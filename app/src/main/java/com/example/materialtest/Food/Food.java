package com.example.materialtest.Food;

public class Food {
    private String img;
    private String name;
    private String pro;
    private String detail;
    public Food(String img,String name,String pro,String detail){
        this.img=img;
        this.name=name;
        this.pro=pro;
        this.detail=detail;
    }

    public String getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public String getDetail() {
        return detail;
    }

    public String getPro() {
        return pro;
    }
}
