package com.fusella.model;

import com.fusella.patenti.Patente;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Database {

    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Veicolo> motoreList = new ArrayList<>();
    private ArrayList<Veicolo> nonMotoreList = new ArrayList<>();
    private ArrayList<Veicolo> ElettricheList = new ArrayList<>();


    //AGGIUNGE UN NUOO UTENTE ALLA LISTA DEGLI UTENTI
    public void addUsers(User user) {
        users.add(user);
    }

    //AGGIUNGE UN NUOVO VEICOLO ALLA LISTA DEI VEICOLI
    public void addVeicolo(Veicolo veicolo) {
        if (veicolo instanceof Motore) {
            motoreList.add(veicolo);
            return;
        } else if (veicolo instanceof Elettriche) {
            ElettricheList.add(veicolo);
            return;
        } else if (veicolo instanceof NonMotore) {
            nonMotoreList.add(veicolo);
            return;
        } else {
            System.out.println("Non esiste alcun tipo di questo veicolo");
            return;
        }
    }

    //RIMUOVE IL SALDO
    public void removeSaldo(UUID id, double saldo) {
        for (User user : users) {
            if (user != null && user.getUuid().equals(id)) {
                user.removeCredit(saldo);
                return;
            }
        }
        System.out.println("Utente non trovato");
        return;
    }

    //AGGIUNGE UNA PATENTE ALL'USER
    public void addPatente(Patente patente, UUID id) {
        for (User user : users) {
            if (user != null && user.getUuid().equals(id)) {
                user.addPatente(patente);
            }
        }
    }

    //RITORNA LA LISTA DELLE PATENTI DI UN USER
    public List<Patente> getPatenti(UUID id) {
        for (User user : users) {
            if (user != null && user.getUuid().equals(id)) {
                return user.getPatenti();
            }
        }
        System.out.println("Patente non trovata");
        return null;
    }

    //RITORNA UN USER
    public User getUsers(UUID id) {
        for (User user : users) {
            if (user != null && user.getUuid().equals(id)) {
                return user;
            }
        }
        System.out.println("Utente non trovato");
        return null;
    }

    //SETTA LA LOCATION DEI VEICOLI
    public void setLocation(UUID id, double latitudine, double longitudine) {
        for (Veicolo motore : motoreList) {
            if (motore != null && motore.getUuid().equals(id)) {
                motore.setLocation(latitudine, longitudine);
                return;
            }
        }
        for (Veicolo nonMotore : nonMotoreList) {
            if (nonMotore != null && nonMotore.getUuid().equals(id)) {
                nonMotore.setLocation(latitudine, longitudine);
                return;
            }
        }
        for (Veicolo elettrico : ElettricheList) {
            if (elettrico != null && elettrico.getUuid().equals(id)) {
                elettrico.setLocation(latitudine, longitudine);
                return;
            }
        }
    }

    //RITORNA UN VEICOLO
    public Veicolo getVeicolo(UUID id) {
        for (Veicolo motore : motoreList) {
            if (motore != null && motore.getUuid().equals(id)) {
                return motore;
            }
        }
        for (Veicolo nonMotore : nonMotoreList) {
            if (nonMotore != null && nonMotore.getUuid().equals(id)) {
                return nonMotore;
            }
        }
        for (Veicolo elettrico : ElettricheList) {
            if (elettrico != null && elettrico.getUuid().equals(id)) {
                return elettrico;
            }
        }
        return null;
    }

    //RIDUCE LA DURABILITA' AI VEICOLI
    public void reduceDurabily(UUID id, double consumo) {
        for (Veicolo veicolo : motoreList) {
            if (veicolo != null && veicolo.getUuid().equals(id)) {
                Motore motore = (Motore) veicolo;
                motore.setCarburante(motore.getCarburante() - consumo);
            }
        }
        for (Veicolo veicolo : ElettricheList) {
            if (veicolo != null && veicolo.getUuid().equals(id)) {
                Elettriche elettriche = (Elettriche) veicolo;
                elettriche.setCorrente(elettriche.getCorrente() - consumo);
            }
        }
    }

    //AGGIUNGE DURABILITA' AI VEICOLI
    public void increasesDurability(UUID id, double consumo) {
        for (Veicolo veicolo : motoreList) {
            if (veicolo != null && veicolo.getUuid().equals(id)) {
                Motore motore = (Motore) veicolo;
                motore.setCarburante(motore.getCarburante() + consumo);
            }
        }
        for (Veicolo veicolo : ElettricheList) {
            if (veicolo != null && veicolo.getUuid().equals(id)) {
                Elettriche elettriche = (Elettriche) veicolo;
                elettriche.setCorrente(elettriche.getCorrente() + consumo);
            }
        }
    }
}