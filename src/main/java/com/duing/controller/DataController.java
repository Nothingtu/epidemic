package com.duing.controller;


import com.duing.entity.DataBean;
import com.duing.service.impl.DataServiceImpl;
import com.duing.util.JavaConnHttp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DataController {


    @Autowired
    private DataServiceImpl dataService;

    //参数说明：from仅是一个表示，若为“t“ ，则表示页面展示的信息来自于腾讯，否则就是丁香医生
    //http://localhost:8080/?from=t
    @GetMapping("/")
    public String dataList(Model model,String from){
        List<DataBean> dataList ;
        if("t".equals(from)) dataList = javaConnHttp.getBeansFromTenxun();
        else dataList = dataService.getDataBeanList();
        model.addAttribute("dataList",dataList);
        return "index";
    }

    @Autowired
    private JavaConnHttp javaConnHttp;

//    @GetMapping("/")
//    public String dataList(Model model){
////        List<DataBean> dataList = javaConnHttp.getBeansFromTenxun();
//        List<DataBean> dataList = javaConnHttp.getBeansFromDx();
//        model.addAttribute("dataList",dataList);
//        return "index";
//    }

}
