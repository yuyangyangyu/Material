package com.example.materialtest;

public class City {
    private String name;
    private String imageId;
    private String detail;
    private String http;
    public City(String name, String imageId, String detail, String http){
        this.name=name;
        this.imageId=imageId;
        this.detail=detail;
        this.http=http;
    }
    public String getName(){
        return name;
    }
    public String getImageId(){
        return imageId;
    }
    public String getDetail(){
        return detail;
    }
    public String getHttp(){
        return http;
    }
}
