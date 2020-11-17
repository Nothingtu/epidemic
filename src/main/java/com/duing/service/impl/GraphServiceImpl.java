package com.duing.service.impl;

import com.duing.entity.GraphBean;
import com.duing.handler.GraphHandler;
import com.duing.service.GraphService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class GraphServiceImpl implements GraphService {

    private String urlStr = "https://api.inews.qq.com/newsqa/v1/query/inner/publish/modules/list?modules=chinaDayList,chinaDayAddList,cityStatis,nowConfirmStatis,provinceCompare";
    private List<GraphBean> graphBeanList = null;

    @PostConstruct // 此方法在程序启动是自动执行
    public List<GraphBean> getGraphBeans() {
        System.out.println("graphBeanList正在初始化...");
        graphBeanList = GraphHandler.getGraphBeans(urlStr);
        return graphBeanList;
    }

    public List<Integer> getGraphBeanConfirmList() {
        List<Integer> confirmList = new ArrayList<>();
        for(int i = 0; i < graphBeanList.size(); i ++){
            confirmList.add(graphBeanList.get(i).getConfirm());
        }
        return confirmList;
    }

    public List<Integer> getGraphBeanHealList() {
        List<Integer> healList = new ArrayList<>();
        for(int i = 0; i < graphBeanList.size(); i ++){
            healList.add(graphBeanList.get(i).getHeal());
        }
        return healList;
    }

    @Override
    public List<String> getGraphBeanDateList() {
        List<String> dateList = new ArrayList<>();
        for(int i = 0; i < graphBeanList.size(); i ++){
            dateList.add(graphBeanList.get(i).getDate());
        }
        return dateList;
    }

    public List<Integer> getGraphBeanDeadList() {
        List<Integer> deadList = new ArrayList<>();
        for(int i = 0; i < graphBeanList.size(); i ++){
            deadList.add(graphBeanList.get(i).getDead());
        }
        return deadList;
    }
}
