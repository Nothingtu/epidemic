package com.duing.controller;

import com.duing.service.GraphBarService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GraphBarController {
    @Autowired
    private GraphBarService graphBarService;

    @GetMapping("/graphBar")
    public String getGraphBar(Model model){
        model.addAttribute("nameList",new Gson().toJson(graphBarService.getSortGraphBarList().get(0)));
        model.addAttribute("confirmList",new Gson().toJson(graphBarService.getSortGraphBarList().get(1)));
        return "graphBar";
    }
}
