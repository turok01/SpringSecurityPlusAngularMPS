package com.Igor.SpringMPS.controller;

import com.Igor.SpringMPS.entities.TransformerSubst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
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
                new TransformerSubst("ll","jj","ii")
                //new TransformerSubst("РП-1","192.168.0.1","РЭС-4"),
                //new TransformerSubst("РП-7","192.168.0.2","РЭС-4")
        );
        return "select";
    }
}
