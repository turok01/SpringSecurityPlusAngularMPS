package com.Igor.SpringMPS.controller;

import com.Igor.SpringMPS.data.TransformerRepository;
import com.Igor.SpringMPS.entities.TransformerSubst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/addnew")
public class AddNewContoller {

    private TransformerRepository transformerRepository;
    @Autowired
    public AddNewContoller(TransformerRepository transformerRepository){
        this.transformerRepository = transformerRepository;
    }
    @GetMapping//("/addnew")
    public String showAddForm(Model model){
        TransformerSubst transformerSubst = new TransformerSubst();
        model.addAttribute("newSubst", transformerSubst);
        return "addnew";
    }

    @PostMapping//("/addnew")
    public String processAdd(@Valid @ModelAttribute("newSubst") TransformerSubst transformerSubst, Errors errors,Model model){
        if(errors.hasErrors()) {
            return "addnew";
        }
        transformerRepository.save(transformerSubst);
        return "successful";

    }
}
