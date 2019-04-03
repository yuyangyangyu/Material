# MaterialTest
对携程网页的信息进行抓取app
------------------  
如图所示：  
![Image text](https://github.com/yuyangyangyu/MaterialTest/blob/master/app/src/main/res/values/name.gif?raw=true)  

抓取的方式为：Jsoup，由于安卓的机制，耗时的任务不能在主线程中执行，故采取AsyncTask的异步加载方式  
同时为增加交互在布局中设置一个ProgessBar的进度条。在子线程完成抓取后隐藏进度条，显示内容。 

目前的功能：
---------  
1.能实现对城市的景点的名称和图片进行获取。  
2.对具体的景点的介绍，评分，图片和亮点信息的获取。  

补充：
-----  
1.此app的Jsoup的实现采用的我另一个项目的源码：  
https://github.com/yuyangyangyu/splider  
2.因为测试中把获取城市的链接写死，可以通过修改城市的链接获取其他城市的景点信息，具体的获取方式可以参照1中所述。  
