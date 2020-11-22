package com.duing.service.impl;

import com.duing.entity.MapBean;
import com.duing.handler.MapHandler;
import com.duing.service.MapService;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class MapServiceImpl implements MapService {

    @Override
    public List<List<MapBean>> getMapBeans() {
        return  MapHandler.getMapBeans();
    }
}
