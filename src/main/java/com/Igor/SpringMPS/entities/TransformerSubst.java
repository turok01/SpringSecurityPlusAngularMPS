package com.Igor.SpringMPS.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
//@RequiredArgsConstructor
//@NoArgsConstructor //(access = AccessLevel.PRIVATE, force = true)
@Entity
@Table (name = "transformersubstations")
public class TransformerSubst {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column (name="NAMESUBS")
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

