package com.Igor.SpringMPS.controller;

import com.Igor.SpringMPS.data.TransformerRepository;
import com.Igor.SpringMPS.entities.Option;
import com.Igor.SpringMPS.entities.TransformerSubst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TransformerSubstByIdConverter implements
        Converter<Integer, TransformerSubst> {
    private TransformerRepository transformerRepo;

    @Autowired
    public TransformerSubstByIdConverter(TransformerRepository transformerRepo){
        this.transformerRepo = transformerRepo;
    }
    @Override
    public TransformerSubst convert(Integer id)
    {
        Optional<TransformerSubst> optionalTransformerSubst = transformerRepo.findById(id);
        return optionalTransformerSubst.isPresent() ?
                optionalTransformerSubst.get() : null;
    }
}
