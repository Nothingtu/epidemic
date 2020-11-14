package com.duing.service.impl;

import com.duing.entity.DataBean;
import com.duing.mapper.DataMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DataServiceImpl {
    @Autowired
    private DataMapper dataMapper;

    //通过调用mapper对象查询数据库里的数据
    public List<DataBean> getDataBeanList(){
        return dataMapper.selectList();
    }


//    public List<DataBean> getDataBeanList(){
//        //dataBeanList作为返回结果
//        List<DataBean> dataBeanList = new ArrayList<>();
//
//        Gson gson = new Gson();
//        Map map = gson.fromJson(this.getString(), Map.class);
//
//        ArrayList list = (ArrayList) map.get("areaTree");
//        Map dataMap =(Map) list.get(0);
//        ArrayList childrenList = (ArrayList)dataMap.get("children");
//        for(int i = 0; i < childrenList.size(); i ++){
//            Map beanMap = (Map)childrenList.get(i);
//
//            String name = (String) beanMap.get("name");
//            Map innerMap = (Map)beanMap.get("total");
//            Integer confirm = (int)((double) innerMap.get("confirm"));
//            Integer nowConfirm = (int)((double) innerMap.get("nowConfirm"));
//            Integer dead = (int)((double) innerMap.get("dead"));
//            Integer heal = (int)((double) innerMap.get("heal"));
//
//            dataBeanList.add(new DataBean(null,name,confirm,nowConfirm,dead,heal));
//        }
//        return dataBeanList;
//    }
//
//    //设计一个方法，用来解析通过URL获得的json字符串String url
//    private String getString(){
//        StringBuffer stringBuffer = new StringBuffer();
//        FileInputStream fis = null;
//        try {
//            fis = new FileInputStream(new File("message.txt"));
//            byte[] b = new byte[1024];
//            int code = fis.read(b);
//            while (code != -1){
//                stringBuffer.append(new String(b,0,code));
//                code = fis.read(b);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                if(fis != null)  fis.close();
//            } catch(Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return stringBuffer.toString();
//    }

}
