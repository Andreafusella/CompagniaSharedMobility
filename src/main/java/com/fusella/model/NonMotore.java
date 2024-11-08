package com.fusella.model;

public class NonMotore extends Veicolo{
    private double prezzoMinuto = 0.25;
    private double velocitaMedia = 20;

    public NonMotore() {
        super();
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
    public double getVelocitaMedia() {
        return velocitaMedia;
    }
}