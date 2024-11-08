package com.fusella.affitto;

import com.fusella.model.*;
import com.fusella.patenti.PatenteB;

import java.util.Locale;
import java.util.Scanner;
import java.util.UUID;

public class Affitto {

    public static void affitto(UUID id, UUID idVeicolo, Database db) {

        User user = db.getUsers(id);
        Veicolo veicolo = db.getVeicolo(idVeicolo);

        boolean hasPatenteB = user.getPatenti().stream().anyMatch(patente -> patente instanceof PatenteB);
        double costoMinuto = veicolo.getPrezzoMinuto();


        if (veicolo instanceof Motore && hasPatenteB) {

            double[] listCostoTotoaleKmLatLon = calcoloAffitto(costoMinuto, veicolo, db);
            double consumo = listCostoTotoaleKmLatLon[0] / 2;

            if (user.getCredito() >= listCostoTotoaleKmLatLon[0] && ((Motore) veicolo).getCarburante() >= consumo) {
                db.removeSaldo(id, listCostoTotoaleKmLatLon[0]);
                System.out.printf("Hai percorso: %.2f Km. Costo totale: %.2f. Nel saldo rimangono: %.2f%n", listCostoTotoaleKmLatLon[1], listCostoTotoaleKmLatLon[0], user.getCredito());
                db.reduceDurabily(idVeicolo, consumo);
                db.setLocation(idVeicolo, listCostoTotoaleKmLatLon[2], listCostoTotoaleKmLatLon[3]);
            } else {
                System.out.println("Non possiedi abbastanza saldo, o la durabilità del mezzo non può sostenere questo viaggio");
            }


        } else if (veicolo instanceof Elettriche) {

            double[] listCostoTotoaleKmLatLon = calcoloAffitto(costoMinuto, veicolo, db);
            double consumo = listCostoTotoaleKmLatLon[0] / 2;

            if (user.getCredito() >= listCostoTotoaleKmLatLon[0] && ((Elettriche) veicolo).getCorrente() >= consumo) {

                db.removeSaldo(id, listCostoTotoaleKmLatLon[0]);
                System.out.printf("Hai percorso: %.2f Km. Costo totale: %.2f. Nel saldo rimangono: %.2f%n", listCostoTotoaleKmLatLon[1], listCostoTotoaleKmLatLon[0], user.getCredito());
                db.reduceDurabily(idVeicolo, consumo);
                db.setLocation(idVeicolo, listCostoTotoaleKmLatLon[2], listCostoTotoaleKmLatLon[3]);
            } else {
                System.out.println("Non possiedi abbastanza saldo, o la durabilità del mezzo non può sostenere questo viaggio");
            }

        } else if (veicolo instanceof NonMotore && user.isCasco()) {
            double[] listCostoTotoaleKmLatLon = calcoloAffitto(costoMinuto, veicolo, db);

            if (user.getCredito() >= listCostoTotoaleKmLatLon[0]) {
                db.removeSaldo(id, listCostoTotoaleKmLatLon[0]);
                System.out.printf("Hai percorso: %.2f Km. Costo totale: %.2f. Nel saldo rimangono: %.2f%n", listCostoTotoaleKmLatLon[1], listCostoTotoaleKmLatLon[0], user.getCredito());
                db.setLocation(idVeicolo, listCostoTotoaleKmLatLon[2], listCostoTotoaleKmLatLon[3]);
            } else {
                System.out.println("Non possiedi abbastanza saldo necessario");
            }
        } else {
            System.out.println("Non hai la patente adatta o non possiedi il casco");
        }

    }

    //perchè non posso passare un metodo che non sia static ad un metodo statico??
    private static double[] calcoloAffitto(double costoMinuto, Veicolo veicolo, Database db) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        double[] listLatLonIniziale = veicolo.getCoordinate();
        if (listLatLonIniziale[0] == 0.0) {
            System.out.println("Errore");
            return null;
        }
        double latIniziale = listLatLonIniziale[0];
        double lonIniziale = listLatLonIniziale[1];

//        System.out.println("Inserire la latitudine finale: ");
//        double latFinale = scanner.nextDouble();
//        System.out.println("Inserire la longitudine finale: ");
//        double lonFinale = scanner.nextDouble();
        double latFinale = 42.449168;
        double lonFinale = 14.220204;

        double velocitaMedia = veicolo.getVelocitaMedia();

        double[] calcoloTempoMinuti = calcoloTempoMinuti(latIniziale, lonIniziale, latFinale, lonFinale, velocitaMedia);
        double calcoloAbbreviato = Math.round((calcoloTempoMinuti[0] * costoMinuto) * 10.0) / 10.0;

        calcoloTempoMinuti[0] = calcoloAbbreviato;


        return calcoloTempoMinuti;
    }

    private static double calcoloDistanzaKm(double lat1, double lon1, double lat2, double lon2) {
        double R = 6371; // Raggio della Terra in km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    private static double[] calcoloTempoMinuti(double lat1, double lon1, double lat2, double lon2, double velocitaMedia) {
        double distanza = calcoloDistanzaKm(lat1, lon1, lat2, lon2);
        double risultato = Math.round(distanza * 10.0) / 10.0;
        double[] valoriRitornati = {(risultato / velocitaMedia) * 60, distanza, lat2, lon2};
        return valoriRitornati;
    }


}