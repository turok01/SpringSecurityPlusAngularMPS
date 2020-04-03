package com.Igor.SpringMPS.controller;

import javax.validation.Valid;
import com.Igor.SpringMPS.ListTransformerSubst;
import com.Igor.SpringMPS.TempTransformerSubst;
import com.Igor.SpringMPS.data.TransformerRepository;
import com.Igor.SpringMPS.entities.TransformerSubst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;

import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/select")
//@SessionAttributes(types = TransformerSubst.class)
@SessionAttributes(value = "currentSubst")
public class SelectController {
    //private AttributedString model;


    @ModelAttribute("currentSubst")
    public TransformerSubst createSubst(){
        return new TransformerSubst();
    }

    private TransformerRepository transformerRepo;
    @Autowired
    public SelectController (TransformerRepository transformerRepo){
        this.transformerRepo = transformerRepo;
    }

    @GetMapping
    public String showSelectForm(@ModelAttribute("currentSubst") TransformerSubst tp, Model model){
        /*List<TransformerSubst> substs = Arrays.asList(
                new TransformerSubst("1","РП-1","192.168.0.1","РЭС-4"),
                new TransformerSubst("2","РП-7","192.168.0.2","РЭС-4")
        );*/
        List<TransformerSubst> substs = new ArrayList<>();
        transformerRepo.findAll().forEach(i->substs.add(i));
        Integer i = 0;
        model.addAttribute("listsubst",substs);
        //model.addAttribute("str_temp",new String());
        //model.addAttribute("intsubst", i);
        //model.addAttribute("transformerSubst", new TransformerSubst());

        //model.addAttribute("currentSubst", new TransformerSubst());
        model.addAttribute("intSubst", i);

        //model.addAttribute("select",new BaseTransformerSubst());
        return "select";
    }
    @PostMapping
    //public String processSelect( @ModelAttribute("currentSubst")  TransformerSubst tp,TempTransformerSubst tempTp){
    //public String processSelect( @ModelAttribute TransformerSubst tempTp){
    public String processSelect(TransformerSubst tempTp,Model model){
        log.info("Processing select: " + tempTp.toString());
        model.addAttribute("currentSubst",tempTp);
        model.addAttribute("id", tempTp.getId());
        //tempTp = tempTp.getId();

        //tp.setNameSubst(tempTp.getName());
        //tp.setNameSubst(intsubst.toString());
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
