package com.example.materialtest;

public class MyThread extends Thread {
    private Attractions attractions;
    private String url;
    public MyThread(Attractions attractions,String url) {
        this.attractions=attractions;
        this.url=url;
    }
    @Override
    public void run() {
        super.run();
        MainActivity.back(attractions,url);
        }
}
