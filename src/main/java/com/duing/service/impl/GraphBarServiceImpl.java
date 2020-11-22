package com.duing.service.impl;

import com.duing.entity.GraphBarBean;
import com.duing.handler.GraphBarHandler;
import com.duing.service.GraphBarService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class GraphBarServiceImpl implements GraphBarService {

    public List<List> getSortGraphBarList(){
        List<GraphBarBean> list = GraphBarHandler.getBarList();

        Collections.sort(list);

        List<List> result = new ArrayList();
        List<String> nameList = new ArrayList();
        List<Integer> confirmList = new ArrayList();
        /**
         *  切记：list集合的size() 是有效元素的个数，而非集合的长度
         */
        for(int i =0; i < 10; i ++){
            GraphBarBean  graphBarBean = list.get(i);

            nameList.add(graphBarBean.getName());
            confirmList.add(graphBarBean.getConfirm());
        }
        result.add(nameList);
        result.add(confirmList);
        return result;
    }
}