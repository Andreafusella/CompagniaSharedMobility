package com.fusella.patenti;

public class PatenteC implements Patente{
    private String numero;
    private String categoria;

    public PatenteC(String numero) {
        this.numero = numero;
        this.categoria = "C";
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