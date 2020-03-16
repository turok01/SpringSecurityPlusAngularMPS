package com.Igor.SpringMPS.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/test")
public class TestController {
    public Model model;
    @GetMapping
    public String showTestForm(){
        List<String> testlist = Arrays.asList("Str1","Str2","Str3");
        this.model.addAttribute("testlist",testlist);
        this.model.addAttribute("model_string","");
        log.info("Processing GETMapping /test: Add to model " + testlist);
        return "test";
    }
    @PostMapping
    public String showTestPostForm(){
        //model.addAttribute("strFromView_test",strFromView_test);
        String str = (String) model.getAttribute("model_string");
        return "test_post_view";
        //return "none";
    }
}
