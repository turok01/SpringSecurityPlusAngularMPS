package com.Igor.SpringMPS.controller;

import com.Igor.SpringMPS.data.TransformerRepository;
import com.Igor.SpringMPS.entities.TransformerSubst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import java.util.Collection;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path="/rest", produces = "application/json")
public class RestSelectController {
    private TransformerRepository transformerRepo;
    //@Autowired
    public RestSelectController (TransformerRepository transformerRepo){
        this.transformerRepo = transformerRepo;
    }

    @GetMapping("/select")
    //@GetMapping("/select")
    public Collection<TransformerSubst> restSelect(){
        return transformerRepo.findAll();
        //return transformerRepo.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/recent")
    public Iterable<TransformerSubst> restRecent(){
        PageRequest page = PageRequest.of(0,5, Sort.by("id").descending());
        return transformerRepo.findAll(page).getContent();
    }
    @GetMapping("/{id}")
    public ResponseEntity<TransformerSubst> substById(@PathVariable("id") Integer id){
        Optional<TransformerSubst> optionalTransformerSubst = transformerRepo.findById(id);
        if(optionalTransformerSubst.isPresent())
            return new ResponseEntity<>(optionalTransformerSubst.get(),HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    @GetMapping("/add")
    public Collection<TransformerSubst> restSelectAddTemp(){
        return transformerRepo.findAll();
    }

    @PutMapping ("/{editByName}")
    public TransformerSubst putSubst(@PathVariable("editByName") Integer id,
                                     @RequestBody TransformerSubst putSubst){
        TransformerSubst transformerSubst = transformerRepo.findById(id).get();
        if (putSubst.getNameSubst() != null){
            transformerSubst.setNameSubst(putSubst.getNameSubst());
        }
        if (putSubst.getIP() != null){
            transformerSubst.setIP(putSubst.getIP());
        }
        if (putSubst.getZone() != null){
            transformerSubst.setZone(putSubst.getZone());
        }
        return transformerRepo.save(transformerSubst);
    }
    //@PatchMapping (path="/{editByName}", consumes="application/json")
    @PatchMapping ("/{editByName}")
    public TransformerSubst patchSubst(@PathVariable("editByName") Integer id,
                                       @RequestBody TransformerSubst patch){
        TransformerSubst transformerSubst = transformerRepo.findById(id).get();
        if (patch.getNameSubst() != null){
            transformerSubst.setNameSubst(patch.getNameSubst());
        }
        if (patch.getIP() != null){
            transformerSubst.setIP(patch.getIP());
        }
        if (patch.getZone() != null){
            transformerSubst.setZone(patch.getZone());
        }
        return transformerRepo.save(transformerSubst);
    }

    /*public TransformerSubst substById(@PathVariable("id") Integer id){
        Optional<TransformerSubst> optionalTransformerSubst = transformerRepo.findById(id);
        return optionalTransformerSubst.isPresent() ? optionalTransformerSubst.get() : null;

    }*/
}
