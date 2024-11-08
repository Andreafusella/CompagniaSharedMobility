package com.fusella.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Elettriche extends NonMotore {
    private double corrente;

    public Elettriche(){
        this.corrente = 100;
    }
}