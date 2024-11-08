package com.fusella;

import com.fusella.affitto.Affitto;
import com.fusella.model.*;
import com.fusella.model.Database;
import com.fusella.patenti.Patente;
import com.fusella.patenti.PatenteA;
import com.fusella.patenti.PatenteB;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Database db = new Database();


        Patente patenteA = new PatenteA("23590");
        Patente patenteB = new PatenteB("23w90");

        User user1 = new User("Andrea", "Fusella", LocalDate.of(2003, 10, 1), "codiceFiscale1", 100.0);
        db.addUsers(user1);
        user1.setCasco(true);

        User user2 = new User("Fraco", "Gino", LocalDate.of(2001, 3, 25), "codiceFiscale2", 20.0);
        db.addUsers(user2);
        user2.setCasco(true);

        User user3 = new User("Pasto", "Pino", LocalDate.of(1990, 2, 5), "codiceFiscale3", 40.0);

        db.addPatente(patenteB, user1.getUuid());

        Motore moto1 = new Motore("AV324BC");

        Elettriche monopattino = new Elettriche();
        NonMotore bici = new NonMotore();

        db.addVeicolo(moto1);
        db.addVeicolo(monopattino);
        db.addVeicolo(bici);

        db.setLocation(moto1.getUuid(), 42.89066, 13.91025);
        db.setLocation(monopattino.getUuid(), 42.468777, 14.204406);
        db.setLocation(bici.getUuid(), 42.89066, 13.91025);

//        Affitto.affitto(user1.getUuid(), moto1.getUuid(), db);
        Affitto.affitto(user1.getUuid(), monopattino.getUuid(), db);
//        Affitto.affitto(user1.getUuid(), monopattino.getUuid(), db);
//        Affitto.affitto(user2.getUuid(), bici.getUuid(), db);


    }
}