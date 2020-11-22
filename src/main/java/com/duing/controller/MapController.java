package com.duing.controller;

import com.duing.service.MapService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MapController {

    @Autowired
    private MapService mapService;

    @GetMapping("/map")
    public String getMapBeans(Model model){
        model.addAttribute("confirms",new Gson().toJson(mapService.getMapBeans().get(0)));
        model.addAttribute("nowConfirms",new Gson().toJson(mapService.getMapBeans().get(1)));
        return "map";
    }
}
