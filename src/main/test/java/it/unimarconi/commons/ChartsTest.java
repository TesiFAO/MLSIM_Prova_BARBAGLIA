package it.unimarconi.commons;

import junit.framework.TestCase;

public class ChartsTest extends TestCase {

    private int a = 5;

    private int b = 12;

    private int seed1 = 1;

    private int seed2 = 15;

    private int numeroClassi = 10;

    private double min = 30.0;

    private double max = 50.0;

    private double p = 0.38;

    private int avg = 20;

    public void testGeneraChartsRn() {
        System.out.println("Sequenza Rn");
        min = 0.0;
        max = 1.0;
        Charts c = new Charts(a, seed1, b, min, max, numeroClassi);
        c.generaChartsRn();
        System.out.println();
    }

    public void testGeneraChartsSequenzaUniforme() {
        System.out.println("Sequenza Uniformemente Distribuita in (30, 50)");
        Charts c = new Charts(a, seed1, b, min, max, numeroClassi);
        c.generaChartsSequenzaUniforme();
        System.out.println();
    }

    public void testGeneraChartsExp() {
        System.out.println("Sequenza Esponenziale di Media 20");
        Charts c = new Charts(a, seed1, b, avg, 10);
        c.generaChartsExp();
        System.out.println();
    }

    public void testGeneraChartsHyperExp() {
        System.out.println("Sequenza Iperesponenziale di Media 20 e Parametro p = 0.38");
        a = 61;
        Charts c = new Charts(a, seed1, seed2, b, avg, p, 10);
        c.generaChartsHyperExp();
        System.out.println();
    }

}