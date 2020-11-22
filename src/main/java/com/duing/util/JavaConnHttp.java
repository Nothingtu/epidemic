package com.duing.util;

import com.duing.entity.DataBean;
import com.duing.mapper.DataMapper;
import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@EnableScheduling
@Component
public class JavaConnHttp {

    @Autowired
    private DataMapper dataMapper;

    //设计一个方法，将爬来的信息写入数据库
    @PostConstruct // 此方法在程序启动是自动执行
    public void save(){
        System.out.println("第一次初始化了");
        List<DataBean> list = getBeansFromDx();
        for(DataBean dataBean : list){
            System.out.println(dataBean.toString());
            dataMapper.insert(dataBean);
        }
    }

    //设计一个方法，自动实时更新数据库里的信息
    @Scheduled(cron = "0 0/1 * * * ?")
    public void updateDataBean(){
        System.out.println("自动更新执行了");
        //删除已有的数据库数据
        dataMapper.delete();
        List<DataBean> list = getBeansFromDx();
        for(DataBean dataBean : list){

            dataMapper.insert(dataBean);
        }
    }

    //腾讯疫情实时动态
    private static String tenxunUrl = "https://view.inews.qq.com/g2/getOnsInfo?name=disease_h5";

    //丁香疫情实时动态
    private String dxUrl = "https://ncov.dxy.cn/ncovh5/view/pneumonia?from=timeline";

    //用java代码模拟doGet请求
    public static String doGet(String urlStr) {
        StringBuffer stringBuffer = new StringBuffer();
        HttpURLConnection httpURLConnection = null;
        BufferedReader bufferedReader = null;
        try {
            URL url = new URL(urlStr);
            httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setRequestMethod("GET");

            //设置连接的时间
            httpURLConnection.setConnectTimeout(15000);
            //设置读取的时间
            httpURLConnection.setReadTimeout(60000);
            //发送请求
            httpURLConnection.connect();

            if (200 == httpURLConnection.getResponseCode()) {
                bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
                String line = bufferedReader.readLine();
                while (line != null) {
                    stringBuffer.append(line);
                    line = bufferedReader.readLine();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (httpURLConnection != null) httpURLConnection.disconnect();
        }
        return stringBuffer.toString();
    }

    //设计一个方法，直接通过URL获取json格式的字符串
    public static List<DataBean> getBeansFromTenxun() {
        String str = doGet(tenxunUrl);

        Gson gson = new Gson();
        Map map = gson.fromJson(str, Map.class);
        String dataStr = (String) map.get("data");

        Map map1 = gson.fromJson(dataStr, Map.class);

        //dataBeanList作为返回结果
        List<DataBean> dataBeanList = new ArrayList<>();

        ArrayList list = (ArrayList) map1.get("areaTree");
        Map dataMap = (Map) list.get(0);
        ArrayList childrenList = (ArrayList) dataMap.get("children");
        for (int i = 0; i < childrenList.size(); i++) {
            Map beanMap = (Map) childrenList.get(i);

            String name = (String) beanMap.get("name");
            Map innerMap = (Map) beanMap.get("total");
            Integer confirm = (int) ((double) innerMap.get("confirm"));
            Integer nowConfirm = (int) ((double) innerMap.get("nowConfirm"));
            Integer dead = (int) ((double) innerMap.get("dead"));
            Integer heal = (int) ((double) innerMap.get("heal"));

            dataBeanList.add(new DataBean(null,name, confirm, nowConfirm, dead, heal));
        }
        return dataBeanList;
    }


    //用来解析丁香医生
    public List<DataBean> getBeansFromDx()  {
        //通过url直接获取Document对象
        Document doc = null;
        String str = null;
        try {
            doc = Jsoup.connect("https://ncov.dxy.cn/ncovh5/view/pneumonia?from=timeline").get();

            Element script = doc.getElementById("getAreaStat");
            str = script.data();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //找到第一次出现“[”的索引，“]” ，截取出文本中的有用信息,保留中括号
        int begin = str.indexOf("[");
        int end = str.lastIndexOf("]");

        String afterStr = str.substring(begin,end + 1);

        Gson gson = new Gson();
        List list = gson.fromJson(afterStr,ArrayList.class);

        //dataBeanList作为返回结果
        List<DataBean> dataBeanList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            Map beanMap = (Map) list.get(i);

            String name = (String) beanMap.get("provinceShortName");
            Integer confirm = (int) ((double) beanMap.get("confirmedCount"));
            Integer nowConfirm = (int) ((double) beanMap.get("currentConfirmedCount"));
            Integer dead = (int) ((double) beanMap.get("deadCount"));
            Integer heal = (int) ((double) beanMap.get("suspectedCount"));

            dataBeanList.add(new DataBean(null,name, confirm, nowConfirm, dead, heal));
        }
        return dataBeanList;
    }

}
