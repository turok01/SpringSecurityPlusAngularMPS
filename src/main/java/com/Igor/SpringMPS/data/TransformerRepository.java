package com.Igor.SpringMPS.data;

import com.Igor.SpringMPS.entities.TransformerSubst;
import org.springframework.data.repository.CrudRepository;

public interface TransformerRepository extends
        CrudRepository<TransformerSubst, Integer> {
}
