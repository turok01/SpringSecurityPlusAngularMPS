package com.Igor.SpringMPS.entities;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TransformerSubst {
    private final String nameSubst;
    private final String IP;
    private final String zone;
}
