package com.Igor.SpringMPS.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@RequiredArgsConstructor
//@NoArgsConstructor //(access = AccessLevel.PRIVATE, force = true)
@Entity
@Table (name = "transformersubstations")
public class TransformerSubst implements Serializable {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotBlank(message = "Необходимо указать название подстанции")
    @Column (name="NAMESUBS")
    private String nameSubst;
    @NotBlank(message = "Необходимо указать IP подстанции")
    private String IP;
    @NotBlank(message = "Необходимо указать зону обслуживания подстанции")
    private String zone;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private User user;

}

