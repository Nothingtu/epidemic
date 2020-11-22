package com.duing.handler;

import com.duing.entity.DataBean;
import com.duing.entity.MapBean;
import com.duing.mapper.DataMapper;
import com.duing.service.DataService;
import com.duing.service.impl.DataServiceImpl;
import com.duing.util.JavaConnHttp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

public class MapHandler {

    public static List<List<MapBean>> getMapBeans(){
        List<DataBean> dataBeans = JavaConnHttp.getBeansFromTenxun();
        //mapBeanList作为返回结果
        List<MapBean> mapBeanList1 = new ArrayList<>();
        List<MapBean> mapBeanList2 = new ArrayList<>();

        for(int i = 0; i < dataBeans.size(); i ++ ){
            mapBeanList1.add(new MapBean(dataBeans.get(i).getName(),dataBeans.get(i).getConfirm()));
            mapBeanList2.add(new MapBean(dataBeans.get(i).getName(),dataBeans.get(i).getNowConfirm()));
            System.out.println("i:"+i+dataBeans.get(i).getName()+"--"+dataBeans.get(i).getConfirm()+"--"+dataBeans.get(i).getNowConfirm());

        }
        List<List<MapBean>> result = new ArrayList<>();
        result.add(mapBeanList1);
        result.add(mapBeanList2);
        return result;
    }
}
