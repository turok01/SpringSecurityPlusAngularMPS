package com.Igor.SpringMPS.controller;

import com.Igor.SpringMPS.ListTransformerSubst;
import com.Igor.SpringMPS.entities.TransformerSubst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/edit")
public class EditController {
    @GetMapping("/current")
    public String newSubstForm(Model model){
        log.info("Processing EditController Get ");
        //model.addAttribute("listsubst",new ListTransformerSubst());
        TransformerSubst tp = (TransformerSubst) model.getAttribute("transformerSubst");
        model.addAttribute("listsubst",new ListTransformerSubst());
        log.info("Processing /edit/current " + tp);
        return "current";
    }
    @PostMapping("/current")
    public String editSubstForm(ListTransformerSubst listSubst){
        log.info("Add new Subst Form " + listSubst);
        return "current";
    }
}
