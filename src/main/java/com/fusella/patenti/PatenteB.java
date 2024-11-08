package com.fusella.patenti;

public class PatenteB implements Patente {
    private String numero;
    private String categoria;

    public PatenteB(String numero) {
        this.numero = numero;
        this.categoria = "B";
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