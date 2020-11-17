package com.duing.service;

import com.duing.entity.GraphBean;

import java.util.List;

public interface GraphService {

    List<GraphBean> getGraphBeans();

    List<Integer> getGraphBeanHealList();

    List<String> getGraphBeanDateList();

    List<Integer> getGraphBeanConfirmList();

    List<Integer> getGraphBeanDeadList();
}
