package com.Igor.SpringMPS.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
//@RequiredArgsConstructor
//@NoArgsConstructor //(access = AccessLevel.PRIVATE, force = true)
@Entity
public class TransformerSubst {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nameSubst;
    private String IP;
    private String zone;
    /*public TransformerSubst(){};
    public TransformerSubst(String nameSubst,String IP,String zone){
        this.nameSubst = nameSubst;
        this.IP = IP;
        this.zone = zone;
    };*/
}

