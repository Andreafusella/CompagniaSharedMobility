package com.fusella.patenti;

public class PatenteA implements Patente{
    private String numero;
    private String categoria;

    public PatenteA(String numero) {
        this.numero = numero;
        this.categoria = "A";
    }

    @Override
    public String getNumero() {
        return numero;
    }

    @Override
    public String getCategoria() {
        return categoria;
    }
}