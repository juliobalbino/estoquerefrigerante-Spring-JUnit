package com.juliobalbino.estoquerefrigerante.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SodaType {

    COCA("Coca"),
    LARANJA("Laranja"),
    UVA("Uva"),
    GUARANA("Guarana"),
    LIMAO("Limao");

    private final String description;

}
