package com.Igor.SpringMPS.controller;

import com.Igor.SpringMPS.ListTransformerSubst;
import com.Igor.SpringMPS.TempTransformerSubst;
import com.Igor.SpringMPS.data.TransformerRepository;
import com.Igor.SpringMPS.entities.TransformerSubst;
import com.Igor.SpringMPS.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/edit")
//@SessionAttributes("currentSubst")
public class EditController {
    private TransformerRepository transformerRepo;
    @Autowired
    public EditController (TransformerRepository transformerRepo){
        this.transformerRepo = transformerRepo;
    }

    @ModelAttribute("currentSubst")
    public TransformerSubst createSubst(){
        return new TransformerSubst();
    }

    @GetMapping("/current")
    //public String newSubstForm(@ModelAttribute("currentSubst") TransformerSubst tp, Errors errors,Model model){
    public String newSubstForm(@ModelAttribute("currentSubst") TransformerSubst tp, Errors errors,Model model){
    //public String newSubstForm(TransformerSubst tp, Errors errors,Model model){
        log.info("Processing EditController Get ");
        TransformerSubst transformerSubst = new TransformerSubst();
        if(tp.getId()!=null)
            transformerSubst = transformerRepo.findById(tp.getId()).orElse(new TransformerSubst());
        model.addAttribute("currentSubst",transformerSubst);
        model.addAttribute("currentId",transformerSubst.getId());
        log.info("Processing /edit/current " + tp);
        return "current";
    }
    @PostMapping("/current")
    public String editSubstForm(@Valid @ModelAttribute("currentSubst") TransformerSubst tp,
                                Errors errors, Model model,
                                @AuthenticationPrincipal User user){
        if (errors.hasErrors()) {
            return "current";
        }
        log.info("Add new Subst Form " + tp);
        tp.setUser(user);
        //tp.setId((Integer)(model.getAttribute("currentId")));
        transformerRepo.save(tp);
        //return "current";
        return "successful";
    }
}
