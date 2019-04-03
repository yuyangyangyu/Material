package com.example.materialtest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Attractions {
    private Document document;
    private List<String> httpList=new ArrayList<String>();
    private List<String> imgList=new ArrayList<String>();
    private List<String> nameList=new ArrayList<String>();
    private List<String> detaillList=new ArrayList<String>();
    /**
     * 通过传入一个城市的URL，获取城市的景点信息（默认15个）
     * @param url
     */
    public void Search(String url) {
        try {
            document= Jsoup.connect(url)
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:49.0) Gecko/20100101 Firefox/49.0")
                    .header("Connection", "close")//如果是这种方式，这里务必带上
                    .timeout(8000)//超时时间
                    .get();
            //获取http和img
            Elements elements=document.select("div.list_wide_mod2").select("div.leftimg");
            //获取景点名称和地点信息
            Elements elements_2=document.select("div.list_wide_mod2").select("dl");
            for (int i = 0; i < elements.size(); i++) {
                //添加景点的详细介绍网页
                httpList.add("https://you.ctrip.com"+elements.get(i).select("a").attr("href"));
                //添加图片链接（介绍图片）
                imgList.add(elements.get(i).select("a").select("img").attr("src"));
                //添加景点名称
                nameList.add(elements_2.get(i).select("a").text());
                //添加详情地点
                detaillList.add(elements_2.get(i).select("dd.ellipsis").text());
                //System.out.println(elements_2.get(i).select("dd.ellipsis").text());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * 返回景点链接
     * @return
     */
    public List<String> http() {
        return httpList;
    }
    /**
     * 返回一张景点图片
     * @return
     */
    public List<String> img() {
        return imgList;
    }
    /**
     * 返回景点名称
     * @return
     */
    public List<String> name() {
        return nameList;
    }
    /**
     * 返回景点的详细地点
     * @return
     */
    public List<String> detail() {
        return detaillList;
    }
    public int size(){
        return httpList.size();
    }
}