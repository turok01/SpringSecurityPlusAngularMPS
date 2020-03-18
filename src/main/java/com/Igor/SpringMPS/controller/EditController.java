package com.Igor.SpringMPS.controller;

import com.Igor.SpringMPS.ListTransformerSubst;
import com.Igor.SpringMPS.TempTransformerSubst;
import com.Igor.SpringMPS.entities.TransformerSubst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/edit")
@SessionAttributes(value = "currentSubst")
public class EditController {
    @GetMapping("/current")
    public String newSubstForm(@ModelAttribute("currentSubst") TransformerSubst tp){
        log.info("Processing EditController Get ");
        //model.addAttribute("listsubst",new ListTransformerSubst());
        //TransformerSubst tp = (TransformerSubst) model.getAttribute("transformerSubst");
        //TransformerSubst tp = (TransformerSubst) request.getSession().getAttribute("transformerSubst");
        //model.addAttribute("transformerSubst", tp );
        log.info("Processing /edit/current " + tp);
        return "current";
    }
    @PostMapping("/current")
    public String editSubstForm(ListTransformerSubst listSubst){
        log.info("Add new Subst Form " + listSubst);
        return "current";
    }
}
