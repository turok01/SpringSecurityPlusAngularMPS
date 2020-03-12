package com.Igor.SpringMPS;

import com.Igor.SpringMPS.entities.TransformerSubst;
import lombok.Data;

import java.util.List;

@Data
public class BaseTransformerSubst {
    private String name;
    private List<TransformerSubst> substations;
}
