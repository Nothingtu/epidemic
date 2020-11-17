package com.duing.handler;

import com.duing.entity.GraphBean;
import com.duing.util.JavaConnHttp;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GraphHandler {


    //获取GraphBean对象的list集合
    public static  List<GraphBean> getGraphBeans(String urlStr){
        String graphBeans = JavaConnHttp.doGet(urlStr);

        Gson gson = new Gson();
        Map map = gson.fromJson(graphBeans,Map.class);

        Map subMap = (Map)map.get("data");
        List graphBeanList = (ArrayList)subMap.get("chinaDayList");

        List result = new ArrayList();
        for(int i = 0; i < graphBeanList.size(); i ++){
            Map bean = (Map)graphBeanList.get(i);
            String date = (String)bean.get("date");
            int confirm = (int)((double)bean.get("confirm"));
            int heal = (int)((double)bean.get("heal"));
            int dead = (int)((double)bean.get("dead"));

            GraphBean graphBean = new GraphBean(date,confirm,heal,dead);
            result.add(graphBean);
        }
        return result;
    }
}
