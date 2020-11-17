package com.duing.controller;

import com.duing.service.GraphService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class GraphController {

    @Autowired
    private GraphService graphService;

    @GetMapping("/graph")
    public String graph(Model model){
        model.addAttribute("dateList",new Gson().toJson(graphService.getGraphBeanDateList()));
        model.addAttribute("healList",new Gson().toJson(graphService.getGraphBeanHealList()));
        model.addAttribute("confirmList",new Gson().toJson(graphService.getGraphBeanConfirmList()));
        model.addAttribute("deadList",new Gson().toJson(graphService.getGraphBeanDeadList()));

        return "graph";
    }
}
