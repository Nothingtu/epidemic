package com.duing.controller;

import com.duing.entity.DataBean;
import com.duing.entity.GraphBarBean;
import com.duing.entity.MapBean;
import com.duing.handler.GraphBarHandler;
import com.duing.service.GraphService;
import com.duing.service.MapService;
import com.duing.service.impl.DataServiceImpl;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class MyController {


    @Autowired
    private GraphService graphService;

    @Autowired
    private MapService mapService;

    @Autowired
    private DataServiceImpl dataService;

    @GetMapping("all")
    public String all(Model model){
        //表格数据
        List<DataBean> dataList  = dataService.getDataBeanList();
        model.addAttribute("dataList",dataList);

        //添加地图所需要的数据 二选一
//        model.addAttribute("mapConfirms",new Gson().toJson(mapService.getMapBeans().get(0)));
//        model.addAttribute("mapNowConfirms",new Gson().toJson(mapService.getMapBeans().get(1)));
        List<MapBean> result1 = new ArrayList();
        List<MapBean> result2 = new ArrayList();
        for(int i = 0; i < dataList.size(); i ++){
            result1.add(new MapBean(dataList.get(i).getName(),dataList.get(i).getConfirm()));
            result2.add(new MapBean(dataList.get(i).getName(),dataList.get(i).getNowConfirm()));
        }
        model.addAttribute("mapConfirms",new Gson().toJson(result1));
        model.addAttribute("mapNowConfirms",new Gson().toJson(result2));

        //添加折线图所需要的数据
        model.addAttribute("dateList",new Gson().toJson(graphService.getGraphBeanDateList()));
        model.addAttribute("healList",new Gson().toJson(graphService.getGraphBeanHealList()));
        model.addAttribute("confirmList",new Gson().toJson(graphService.getGraphBeanConfirmList()));
        model.addAttribute("deadList",new Gson().toJson(graphService.getGraphBeanDeadList()));

        //添加柱状图所需要的数据
        List<GraphBarBean> list = GraphBarHandler.getBarList();
        Collections.sort(list);

        List<String> nameList = new ArrayList();
        List<Integer> confirmList = new ArrayList();
        for(int i =0; i < 10; i ++){
            GraphBarBean  graphBarBean = list.get(i);
            nameList.add(graphBarBean.getName());
            confirmList.add(graphBarBean.getConfirm());
        }
        model.addAttribute("nameList",new Gson().toJson(nameList));
        model.addAttribute("barConfirm",new Gson().toJson(confirmList));

        return "all";
    }



}
