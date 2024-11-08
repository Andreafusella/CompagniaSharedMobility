package com.fusella.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter

public abstract class Veicolo {
    private UUID uuid;
    private boolean affitto;
    private double latitudine;
    private double longitudine;
    private double velocitaMedia;

    public Veicolo() {
        uuid = UUID.randomUUID();
        this.affitto = false;
    }
    public void setLocation(double latitudine, double longitudine) {
        this.latitudine = latitudine;
        this.longitudine = longitudine;
    }

    public abstract double getPrezzoMinuto();
    public abstract double getVelocitaMedia();
    public abstract double[] getCoordinate();
}