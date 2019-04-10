package com.example.materialtest.Food;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class foodList {
	private List<String> nameList=new ArrayList<String>();
	private List<String> httpList=new ArrayList<String>();
	private List<String> describeList=new ArrayList<String>();
	private List<String> imgList=new ArrayList<String>();
	private List<String> Recommend=new ArrayList<String>();
	private Elements elements;
	private Elements elements2;
	private Elements elements3;
	private Elements elements4;
	public void Search(String url) {
		try {
			Document document=Jsoup.connect(url).get();
			elements=document.select("div.rdetailbox").select("dt").select("a");
			elements2=document.select("div.rdetailbox").select("dd");
			elements3=document.select("p.bottomcomment");
			elements4=document.select("div.leftimg").select("img");
			for (int i = 0; i < elements.size(); i++) {
				httpList.add("https://you.ctrip.com/fooditem/"+elements.get(i).attr("href"));
				nameList.add(elements.get(i).text());
				describeList.add(elements2.text().replace("详情", ""));
				imgList.add(elements4.get(i).attr("data-imgurl"));
				//System.out.println(elements4.get(i).attr("data-imgurl"));
			}
			//Element elements_9=elements3.get(1);
			//System.out.println(elements3);
			//System.out.println(elements3.get(1).select("[target=_blank]"));
			for (int j = 0; j < elements3.size(); j++) {
				Elements elements_1 =elements3.get(j).select("[target=_blank]");
				String produceString=new String();
				for (int k = 0; k < elements_1.size(); k++) {
					produceString=produceString+elements_1.get(k).text()+",";
				}
				//System.out.println(produceString);
				Recommend.add(produceString);
			}
			//System.out.println(Recommend.get(0)[4]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 返回美食详情页的链接
	 * @return
	 */
	public List<String> http() {
		return httpList;
	}
	/**
	 * 返回城市美食列表
	 * @return
	 */
	public List<String> name() {
		return nameList;
	}
	public List<String> describe() {
		return describeList;
	}
	/**
	 * 返回推荐地点
	 * @return
	 */
	public List<String> recommend() {
		return Recommend;
	}

    /**
     * 返回图片
     * @return
     */
    public List<String> getImgList(){
	    return imgList;
    }
    /**
     * 返回大小
     * @return
     */
	public int Size(){
	    return httpList.size();
    }

}
