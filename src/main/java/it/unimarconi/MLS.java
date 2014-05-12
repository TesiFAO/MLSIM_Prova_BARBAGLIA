package it.unimarconi;

import it.unimarconi.commons.GCM;
import it.unimarconi.commons.Generatore;
import it.unimarconi.commons.Stats;
import it.unimarconi.commons.Utils;
import it.unimarconi.test.Seriale;
import it.unimarconi.test.Uniformita;

import java.util.ArrayList;
import java.util.Collections;

public class MLS {

    public static void main(String[] args) {

        System.out.println("Pratica 1\n");
        int a = 5;
        int seed = 1;
        int b = 9;
        GCM gcm = new GCM(a, seed, b);
        ArrayList<Double> l = new ArrayList<Double>();
        double tmp = gcm.getNext();
        while (!l.contains(tmp)) {
            l.add(tmp);
            tmp = gcm.getNext();
        }
        Collections.sort(l);
        double[] s1 = gcm.generate4v1();
        double[] s2 = gcm.generate4v3();
        System.out.println("Sequenza generata (primi 50 valori):");
        Utils.print(l, 50);
        System.out.println();
        System.out.println("Sequenza Corollario A: 4v + 1 (primi 50 valori):");
        Utils.print(s1, 50);
        System.out.println();
        System.out.println("Sequenza Corollario A: 4v + 3 (primi 50 valori):");
        Utils.print(s2, 50);
        System.out.println();

        System.out.println("Pratica 2\n");

        a = 5;
        seed = 1;
        int seed2 = 15;
        b = 12;
        int media = 20;
        double p = 0.38;

        Generatore g = new Generatore(a, seed, b, 30, 50);
        ArrayList<Double> uni = new ArrayList<Double>();
        tmp = g.getNextRange();
        while (!uni.contains(tmp)) {
            uni.add(tmp);
            tmp = g.getNextRange();
        }
        System.out.println("Sequenza uniforme in (30, 50) (primi 50 valori):");
        Utils.print(l, 50);
        System.out.println();

        g = new Generatore(a, seed, b, media);
        ArrayList<Double> exp = new ArrayList<Double>();
        tmp = g.getNextExp();
        while (!exp.contains(tmp)) {
            exp.add(tmp);
            tmp = g.getNextExp();
        }
        System.out.println("Sequenza esponenziale di media 20 (primi 50 valori):");
        Utils.print(l, 50);
        System.out.println();

        g = new Generatore(a, seed, seed2, b, media, p);
        ArrayList<Double> hyper = new ArrayList<Double>();
        tmp = g.getNextHyperExp();
        while (!hyper.contains(tmp)) {
            hyper.add(tmp);
            tmp = g.getNextHyperExp();
        }
        System.out.println("Sequenza iperesponenziale di media 20 e p = 0.38 (primi 50 valori):");
        Utils.print(l, 50);
        System.out.println();

        System.out.println("Pratica 3\n");

        System.out.println("Sequenza uniforme in (30, 50):");
        System.out.println("Media: " + Stats.media(uni));
        System.out.println("Varianza: " + Stats.sd(uni));
        System.out.println();

        System.out.println("Sequenza esponenziale di media 20:");
        System.out.println("Media: " + Stats.media(exp));
        System.out.println("Varianza: " + Stats.sd(exp));
        System.out.println();

        System.out.println("Sequenza iperesponenziale di media 20 e p = 0.38:");
        System.out.println("Media: " + Stats.media(hyper));
        System.out.println("Varianza: " + Stats.sd(hyper));
        System.out.println();

        System.out.println("Pratica 4\n");

        a = 10037;
        seed = 161;
        b = 19;
        int d = 64;
        int prove = 3;

        Uniformita u = new Uniformita(a, seed, b, d, prove);
        boolean[] out = u.eseguiTest();

        Seriale s = new Seriale(a, seed, b, d, prove);
        s.eseguiTest();

    }

}