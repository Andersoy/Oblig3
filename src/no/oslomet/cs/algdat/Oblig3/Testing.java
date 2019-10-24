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
        System.out.println(tre.fjernAlle(4)); // 3
        tre.fjernAlle(7); tre.fjern(8);
        System.out.println(tre.antall()); // 5
        System.out.println(tre + " " + tre.omvendtString());
        // [1, 2, 6, 9, 10] [10, 9, 6, 2, 1]
        // OBS: Hvis du ikke har gjort oppgave 4 kan du her bruke toString()
    }

    // OPPGAVE 6 ////////////////////////////////////////////////
    @org.junit.jupiter.api.Test
    void oppgave6() {
        ObligSBinTre<Character> tre = new ObligSBinTre<>(Comparator.naturalOrder());
        char[] verdier = "IATBHJCRSOFELKGDMPQN".toCharArray();
        for (char c : verdier) tre.leggInn(c);
        System.out.println(tre.høyreGren() + "​ " + tre.lengstGren());
        // Utskrift: [I, T, J, R, S] [I, A, B, H, C, F, E, D]
    }

    // OPPGAVE 7 ////////////////////////////////////////////////
    @org.junit.jupiter.api.Test
    void oppgave7() {
        ObligSBinTre<Character> tre = new ObligSBinTre<>(Comparator.naturalOrder());
        char[] verdier = "IATBHJCRSOFELKGDMPQN".toCharArray();
        for (char c : verdier) tre.leggInn(c);
        String[] s = tre.grener();
        for (String gren: s){
            System.out.println(gren);
        }

        // Utskrift: ​
        // [I, A, B, ​H, C, F, E, D]
        // [I, A, B, ​H, C, F, G]
        // [I, T, J, R, O, L, K]
        // [I, T, J,​ R, O, L, M, N]
        // [I, T, J, R, O, P, Q]
        // [I, T, J, R, S]

    }

    // OPPGAVE 8 ////////////////////////////////////////////////
    @org.junit.jupiter.api.Test
    void oppgave8() {
        ObligSBinTre<Character> tre = new ObligSBinTre<>(Comparator.naturalOrder());
        char[] verdier = "IATBHJCRSOFELKGDMPQN".toCharArray();
        for (char c : verdier) tre.leggInn(c);
        System.out.println(tre.postString());
        // [D, E, G, F, C, H, B, A, K, N, M, L, Q, P, O, S, R, J, T, I]


        int[] a = {4,7,2,9,4,10,8,7,4,6};
        ObligSBinTre<Integer> tre2 = new ObligSBinTre<>(Comparator.naturalOrder());
        for (int verdi : a) {
            tre2.leggInn(verdi);
        }
        System.out.println(tre2.postString()); // [2, 6, 4, 4, 7, 8, 10, 9, 7, 4]

    }

    // OPPGAVE 9 ////////////////////////////////////////////////
    @org.junit.jupiter.api.Test
    void oppgave9() {
        ObligSBinTre<Character> tre = new ObligSBinTre<>(Comparator.naturalOrder());
        char[] verdier = "IATBHJCRSOFELKGDMPQN".toCharArray();
        for (char c : verdier){
            tre.leggInn(c);
        }

        for (Character c : tre){
            System.out.print(c + " "); // D G K N Q S
        }

        System.out.println();

        int[] a = {4,7,2,9,4,10,8,7,4,6};
        ObligSBinTre<Integer> tre2 = new ObligSBinTre<>(Comparator.naturalOrder());
        for (int verdi : a) {
            tre2.leggInn(verdi);
        }
        for (Integer k : tre2) System.out.print(k + " "); // 2 6 7 10
    }

    // OPPGAVE 10 ////////////////////////////////////////////////
    @org.junit.jupiter.api.Test
    void oppgave10() {

        ObligSBinTre<Character> tre = new ObligSBinTre<>(Comparator.naturalOrder());
        char[] verdier = "IATBHJCRSOFELKGDMPQN".toCharArray();
        for (char c : verdier) tre.leggInn(c);

        while (!tre.tom()) {
            System.out.println(tre);
            tre.fjernHvis(x -> true); }
        /*
        [A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T]
        [A, B, C, E, F, H, I, J, L, M, O, P, R, T]
        [A, B, C, F, H, I, J, L, O, R, T]
        [A, B, C, H, I, J, O, R, T]
        [A, B, H, I, J, R, T]
        [A, B, I, J, T]
        [A, I, T]
        [I]
        */


        int[] a = {4,7,2,9,4,10,8,7,4,6};
        ObligSBinTre<Integer> tre2 = new ObligSBinTre<>(Comparator.naturalOrder());
        for (int verdi : a) {
            tre2.leggInn(verdi);
        }
        while (!tre2.tom()) {
            System.out.println(tre2);
            tre2.fjernHvis(x -> true); }

        /*
        [2, 4, 4, 4, 6, 7, 7, 8, 9, 10]
        [4, 4, 4, 7, 8, 9]
        [4, 4, 7, 9]
        [4, 7]
        [4]
        */

    }




}
