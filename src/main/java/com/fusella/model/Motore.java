package com.fusella.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Motore extends Veicolo{
    private String targa;
    private double prezzoMinuto = 0.65;
    private double carburante;
    private double velocitaMedia = 80;

    public Motore(String targa) {
        this.targa = targa;
        this.carburante = 100;
    }

    @Override
    public double getPrezzoMinuto() {
        return prezzoMinuto;
    }

    @Override
    public double[] getCoordinate() {
        double[] coordinate = {super.getLatitudine(), super.getLongitudine()};
        return coordinate;
    }

    @Override
    public String toString() {
        return targa + carburante + super.getUuid();
    }

}