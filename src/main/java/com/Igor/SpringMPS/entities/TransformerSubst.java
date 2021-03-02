package com.Igor.SpringMPS.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data //delete because Lombok toString() cyclic call
@RequiredArgsConstructor
//@NoArgsConstructor //(access = AccessLevel.PRIVATE, force = true)
@Entity
@Table (name = "transformersubstations")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
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

    //@JsonManagedReference

    //@ManyToOne(fetch=FetchType.LAZY) //if use LAZY, in JSON we get SerializationFeature.FAIL_ON_EMPTY_BEANS
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private User user;

}

