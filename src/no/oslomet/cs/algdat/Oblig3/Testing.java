package no.oslomet.cs.algdat.Oblig3;

import java.util.Comparator;

public class Testing {

    // OPPGAVE 0 ////////////////////////////////////////////////
    @org.junit.jupiter.api.Test
    void oppgave0() {

        ObligSBinTre<Character> tre = new ObligSBinTre<>(Comparator.naturalOrder());

        System.out.println(tre.antall()); // Utskrift: 0

    }  // slutt på Oppgave 1



    // OPPGAVE 1 ////////////////////////////////////////////////
    @org.junit.jupiter.api.Test
    void oppgave1() {
        Integer[] a = {4,7,2,9,5,10,8,1,3,6};
        ObligSBinTre<Integer> tre = new ObligSBinTre<>(Comparator.naturalOrder());
        for (int verdi : a) {
            tre.leggInn(verdi);
        }
        System.out.println(tre.antall()); // Utskrift: 10
    }


    // OPPGAVE 2 ////////////////////////////////////////////////
    @org.junit.jupiter.api.Test
    void oppgave2() {

        Integer[] a = {4,7,2,9,4,10,8,7,4,6};
        ObligSBinTre<Integer> tre = new ObligSBinTre<>(Comparator.naturalOrder());
        for(int verdi : a) {
            tre.leggInn(verdi);
        }
        System.out.println(tre.antall());
        System.out.println(tre.antall(5));
        System.out.println(tre.antall(4));
        System.out.println(tre.antall(7));
        System.out.println(tre.antall(10));
        // Utskrift: 10 ​// Utskrift: 0 ​// Utskrift: 3 ​// Utskrift: 2 ​// Utskrift: 1

    }

    // OPPGAVE 3 ////////////////////////////////////////////////
    @org.junit.jupiter.api.Test
    void oppgave3() {

        int[] a = {4,7,2,9,4,10,8,7,4,6,1};
        ObligSBinTre<Integer> tre = new ObligSBinTre<>(Comparator.naturalOrder());
        for(int verdi: a){
            tre.leggInn(verdi);
        }
        tre.toString();
        System.out.println(tre); // [1, 2, 4, 4, 4, 6, 7, 7, 8, 9, 10]

    }


    // OPPGAVE 4 ////////////////////////////////////////////////
    @org.junit.jupiter.api.Test
    void oppgave4() {
        int[] a = {4,7,2,9,4,10,8,7,4,6,1};
        ObligSBinTre<Integer> tre = new ObligSBinTre<>(Comparator.naturalOrder());
        for (int verdi: a) {
            tre.leggInn(verdi);
        }
        System.out.println(tre.omvendtString()); //[10, 9, 8, 7, 7, 6, 4, 4, 4, 2, 1]
    }


    // OPPGAVE 5 ////////////////////////////////////////////////
    @org.junit.jupiter.api.Test
    void oppgave5() {
        int[] a = {4,7,2,9,4,10,8,7,4,6,1};
        ObligSBinTre<Integer> tre = new ObligSBinTre<>(Comparator.naturalOrder()); for (int verdi : a) tre.leggInn(verdi);
        System.out.println(tre.fjernAlle(4)); // 3 tre.fjernAlle(7); tre.fjern(8);
        System.out.println(tre.antall()); // 5
        System.out.println(tre + " " + tre.omvendtString());
        // [1, 2, 6, 9, 10] [10, 9, 6, 2, 1]
        // OBS: Hvis du ikke har gjort oppgave 4 kan du her bruke toString()
    }
}
