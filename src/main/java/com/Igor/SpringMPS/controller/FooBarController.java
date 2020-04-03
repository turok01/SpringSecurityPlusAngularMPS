package com.Igor.SpringMPS.controller;

import com.Igor.SpringMPS.entities.Option;
import com.Igor.SpringMPS.entities.TransformerSubst;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("foo")
public class FooBarController {

    @RequestMapping("bar/{option}")
    public String getBar(@PathVariable("option") Option option) {
        // всяко-разно
        return "home";
    }
    @RequestMapping("select/{option}")
    public String getBar(@PathVariable("option") TransformerSubst option) {
        // всяко-разно
        return "current";
    }
}

