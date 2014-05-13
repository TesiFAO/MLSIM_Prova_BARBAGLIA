package it.unimarconi;

import it.unimarconi.commons.*;
import it.unimarconi.test.Seriale;
import it.unimarconi.test.Uniformita;

import java.util.ArrayList;
import java.util.Collections;

public class MLS {

    public static void main(String[] args) {
        pratica1();
        pratica2();
        pratica4();
    }

    public static void pratica4() {
        System.out.println("Pratica 4: Posto m = 219, costruire in C++ o Java un generatore congruente moltiplicativo che generi interi Xn in uno degli insiemi di cui al quesito Teoria 1. Generare poi da detto insieme una sequenza <Xn> e condurre sulla relativa sequenza <Rn> un test di Uniformità e un test Seriale. Per i test fissare d = 64. Condurre ogni test 3 volte, ciascuna volta su una sequenza pari a 1/3 dell’intero periodo. Scegliere il moltiplicatore a di Xn in modo che ogni test risulti accettabile almeno 2 volte su 3\n");
        int a = 10037;
        int seed = 161;
        int b = 19;
        int d = 64;
        int prove = 3;
        Uniformita u = new Uniformita(a, seed, b, d, prove);
        boolean[] out = u.eseguiTest();
        Seriale s = new Seriale(a, seed, b, d, prove);
        s.eseguiTest();
    }

    public static void pratica2() {
        System.out.println("Pratica 2: Posto m = 2ˆ12, a = 5 (mod 8) e rispetto delle condizioni di periodo massimo, usando gli algoritmi di cui ai punti Teoria 4 e Teoria 5 costruire in C++ o Java un generatore che produca sequenze <Rn> e, con questo, generatori che producano: \n" +
                "una sequenza uniforme in (30, 50) \n" +
                "una sequenza esponenziale di media 20 \n" +
                "una sequenza iper-esponenziale di media 20 e parametro p = 0.38\n");
        int a = 5;
        int seed = 1;
        int seed2 = 15;
        int b = 12;
        int media = 20;
        double p = 0.38;
        double min = 0.0;
        double max = 1.0;
        int numeroClassi = 10;
        ArrayList<Double> l = new ArrayList<Double>();
        Generatore g = new Generatore(a, seed, b, 30, 50);
        ArrayList<Double> uni = new ArrayList<Double>();
        double tmp = g.getNextRange();
        while (!uni.contains(tmp)) {
            uni.add(tmp);
            tmp = g.getNextRange();
        }
        System.out.println("Sequenza uniforme in (30, 50) (primi 50 valori):");
        Utils.print(uni, 50);
        System.out.println();
        Charts c = new Charts(a, seed, b, min, max, numeroClassi);
        c.generaChartsSequenzaUniforme();
        System.out.println();

        g = new Generatore(a, seed, b, media);
        ArrayList<Double> exp = new ArrayList<Double>();
        tmp = g.getNextExp();
        while (!exp.contains(tmp)) {
            exp.add(tmp);
            tmp = g.getNextExp();
        }
        System.out.println("Sequenza esponenziale di media 20 (primi 50 valori):");
        Utils.print(exp, 50);
        System.out.println();
        c = new Charts(a, seed, b, media, 10);
        c.generaChartsExp();
        System.out.println();

        g = new Generatore(a, seed, seed2, b, media, p);
        ArrayList<Double> hyper = new ArrayList<Double>();
        tmp = g.getNextHyperExp();
        while (!hyper.contains(tmp)) {
            hyper.add(tmp);
            tmp = g.getNextHyperExp();
        }
        System.out.println("Sequenza iperesponenziale di media 20 e p = 0.38 (primi 50 valori):");
        Utils.print(hyper, 50);
        System.out.println();
        a = 61;
        c = new Charts(a, seed, seed2, b, media, p, 10);
        c.generaChartsHyperExp();
        System.out.println();

        System.out.println("Pratica 3: Usando i generatori di cui al punto Pratica 2, produrre un istogramma per una sequenza <Rn> e uno per ciascuna delle restanti tre sequenze di cui al punto, e verificarne media e varianza\n");

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

    }

    public static void pratica1() {
        System.out.println("Pratica 1: Posto m = 2ˆ9 a ≡ 5 (mod 8) e rispetto delle condizioni di periodo massimo, costruire in C++ o Java un generatore congruente moltiplicativo di interi Xn che consenta di verificare quali e quanti insiemi differenti di interi è possibile generare, e per quali valori del seme X0.\n");
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
        System.out.println("Sequenza generata per X0 = 1 (primi 50 valori):");
        Utils.print(l, 50);
        System.out.println();
        seed = 3;
        gcm = new GCM(a, seed, b);
        l = new ArrayList<Double>();
        tmp = gcm.getNext();
        while (!l.contains(tmp)) {
            l.add(tmp);
            tmp = gcm.getNext();
        }
        Collections.sort(l);
        System.out.println("Sequenza generata per X0 = 3 (primi 50 valori):");
        Utils.print(l, 50);
        System.out.println();
        System.out.println("Sequenza Corollario A: 4v + 1 (primi 50 valori):");
        Utils.print(s1, 50);
        System.out.println();
        System.out.println("Sequenza Corollario A: 4v + 3 (primi 50 valori):");
        Utils.print(s2, 50);
        System.out.println();
    }

}