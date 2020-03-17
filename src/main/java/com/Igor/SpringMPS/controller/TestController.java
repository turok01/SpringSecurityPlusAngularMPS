package com.Igor.SpringMPS.controller;

import com.Igor.SpringMPS.entities.TransformerSubst;
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
    //public Model model;
    @GetMapping
    public String showTestForm(Model model){
        List<String> testlist = Arrays.asList((String)"Str1",(String)"Str2",(String)"Str3");
        model.addAttribute("testlist",testlist);
        model.addAttribute("model_string",new String());
        log.info("Processing GETMapping /test: Add to model " + testlist);
        return "test";
    }
    @PostMapping
    public String showTestPostForm(TransformerSubst transformerSubst){
        //model.addAttribute("strFromView_test",strFromView_test);
        log.info("Processing test Post: " + transformerSubst);
        //String strtemp = (String) model.getAttribute("name_input");
        return "test_post_view";
        //return "none";
    }
}
