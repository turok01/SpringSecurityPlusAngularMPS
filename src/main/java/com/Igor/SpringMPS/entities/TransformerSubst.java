package com.Igor.SpringMPS.entities;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
//@RequiredArgsConstructor
public class TransformerSubst {
    private String nameSubst;
    private String IP;
    private String zone;
    public TransformerSubst(){};
    public TransformerSubst(String nameSubst,String IP,String zone){
        this.nameSubst = nameSubst;
        this.IP = IP;
        this.zone = zone;
    };
}

