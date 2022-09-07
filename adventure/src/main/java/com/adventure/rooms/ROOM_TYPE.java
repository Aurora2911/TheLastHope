package com.adventure.rooms;

import java.lang.String;

public enum ROOM_TYPE {
    GARAGE,
    FIRST_FLOOR,
    GROUND_FLOOR,
    BASEMENT,
    SAFE,
    WAREHOUSE,
    ACCOMODATION1,
    ACCOMODATION2,
    ACCOMODATION3,
    ACCOMODATION4,
    SURVEILLANCE,
    UFFICIALS,
    OPERATIVE,
    ENTRANCE;

    public static String getDescription(ROOM_TYPE rt) {
        switch (rt) {
            case GARAGE:
                return "Garage: carino, ma nel mio ho una bella ferrari";
            case FIRST_FLOOR:
                return "Primo piano: rustico, sembra abbandonato da tempo";
            case GROUND_FLOOR:
                return "Piano terra: piu' accogliente rispetto al resto delle case in Ucraina";
            case BASEMENT:
                return "Seminterrato: cosi' buio che spero di non sbattere la testa da qualche parte";
            case WAREHOUSE:
                return "Magazzino: presenti scorte militari russe ed abiti russi.";
            case ACCOMODATION1:
                return "Alloggio: alloggio russo.";
            case ACCOMODATION2:
                return "Alloggio: alloggio russo.";
            case ACCOMODATION3:
                return "Alloggio: alloggio russo.";
            case ACCOMODATION4:
                return "Alloggio: alloggio russo.";
            case SURVEILLANCE:
                return "Camera di sorveglianza: e' presente il sorvegliante della base.";
            case UFFICIALS:
                return "Stanza dell'ufficiale: stanza dell'ufficiale della base.";
            case OPERATIVE:
                return "Stanza operativa: son presenti i calcolatori del sistema russo.";
            case SAFE:
                return "Stanza segreta: un vero e proprio centro di comando in miniatura!";
            default:
                return "A room";
        }
    }
}