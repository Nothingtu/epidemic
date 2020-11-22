package com.duing.handler;

import com.duing.entity.GraphBarBean;
import com.duing.service.GraphBarService;
import com.duing.util.JavaConnHttp;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class GraphBarHandler {


//    public static void main(String[] args) {
//        List<GraphBarBean> result = getBarList();
//        System.out.println();
//    }
    //腾讯疫情实时动态
    private static String tenxunUrl = "https://view.inews.qq.com/g2/getOnsInfo?name=disease_h5";

    //获取柱状图的信息对象
    public static List<GraphBarBean> getBarList(){
        String str =  JavaConnHttp.doGet(tenxunUrl);

        List<GraphBarBean> result = new ArrayList<>();
        Gson gson = new Gson();
        Map map0 = gson.fromJson(str, Map.class);
        String dataStr = (String) map0.get("data");
        Map map = gson.fromJson(dataStr,Map.class);

        List areaTreeList = (ArrayList) map.get("areaTree");
        Map map1 = (Map)areaTreeList.get(0);

        List childrenList =(ArrayList) map1.get("children");

        for(int i = 0; i < childrenList.size(); i ++){
            Map child = (Map)childrenList.get(i);

            String name =(String) child.get("name");

            List innerChildrenList =(ArrayList) child.get("children");
            Map map2 = (Map)innerChildrenList.get(0);
            if("境外输入".equals((String)map2.get("name"))){
                Map map3 = (Map)map2.get("total");
                double confirm = (Double) map3.get("confirm");
                result.add(new GraphBarBean(name,(int)confirm));
            }
        }
        return result;
    }
}
