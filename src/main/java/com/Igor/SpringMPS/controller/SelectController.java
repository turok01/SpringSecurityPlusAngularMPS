package com.Igor.SpringMPS.controller;

import com.Igor.SpringMPS.BaseTransformerSubst;
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
@RequestMapping("/select")
public class SelectController {
    @GetMapping
    public String showSelectForm(Model model){
        List<TransformerSubst> substs = Arrays.asList(
                new TransformerSubst("РП-1","192.168.0.1","РЭС-4"),
                new TransformerSubst("РП-7","192.168.0.2","РЭС-4")
        );

        model.addAttribute("select",substs);
        //model.addAttribute("select",new BaseTransformerSubst());
        return "select";
    }
    @PostMapping
    public String processSelect(){
        log.info("Processing select: ");
        return "redirect:/edit/current";
    }
}
