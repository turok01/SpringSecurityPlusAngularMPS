package com.Igor.SpringMPS.controller;

import com.Igor.SpringMPS.ListTransformerSubst;
import com.Igor.SpringMPS.TempTransformerSubst;
import com.Igor.SpringMPS.entities.TransformerSubst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.AttributedString;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/select")
//@SessionAttributes(types = TicketForm.class)
public class SelectController {
    //private AttributedString model;

    @GetMapping
    public String showSelectForm(Model model){
        List<TransformerSubst> substs = Arrays.asList(
                new TransformerSubst("РП-1","192.168.0.1","РЭС-4"),
                new TransformerSubst("РП-7","192.168.0.2","РЭС-4")
        );

        model.addAttribute("listsubst",substs);
        model.addAttribute("namesubst",new String());
        model.addAttribute("transformerSubst", new TransformerSubst());

        //model.addAttribute("select",new BaseTransformerSubst());
        return "select";
    }
    @PostMapping
    public String processSelect(TempTransformerSubst tp){
        log.info("Processing select: " + tp);
        //return "redirect:/edit/current";
        return "redirect:/edit/current";
/*-
        public String getQuestion(ModelMap model, HttpServletRequest request) {
            String[] questions ={ "a","b", "c"};
            request.getSession().setAttribute("questions", questions);
            return "index";
        }
 */
    }
}
