package com.Igor.SpringMPS;

import com.Igor.SpringMPS.entities.TransformerSubst;
import lombok.Data;

import java.util.List;

@Data
public class ListTransformerSubst {
    private String name;
    private List<TransformerSubst> substations;
}
