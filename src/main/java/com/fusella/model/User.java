package com.fusella.model;

import com.fusella.patenti.Patente;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter

public class User {
    private UUID uuid;
    private String firstName;
    private String lastName;
    private LocalDate dataNascita;
    private String cf;
    private double credito;
    private List<Patente> patenti = new ArrayList<>();
    private boolean casco = false;

    public User(String firstName, String lastName, LocalDate dataNascita, String cf, double credito) {
        uuid = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.dataNascita = dataNascita;
        this.cf = cf;
        this.credito = credito;
    }

    public void addCredit(double credito) {
        this.credito += credito;
    }

    public void removeCredit(double credito) {
        this.credito -= credito;
    }

    public void addPatente(Patente patente) {
        patenti.add(patente);
    }

    public void removePatente(Patente patente) {
        patenti.remove(patente);
    }


}