package it.unimarconi.commons;

import junit.framework.TestCase;

import java.util.ArrayList;

public class GeneratoreTest extends TestCase {

    private int a = 5;

    private int b = 5;

    private int seed1 = 1;

    private int seed2 = 1;

    private double p = 0.38;

    private int min = 30;

    private int max = 50;

    private int avg = 20;

    public void testGetNextRange_30_50() {
        Generatore g = new Generatore(a, seed1, b, min, max);
        ArrayList<Double> l = new ArrayList<Double>();
        double tmp = g.getNextRange();
        while (!l.contains(tmp)) {
            l.add(tmp);
            assertTrue(tmp > min && tmp < max);
            tmp = g.getNextRange();
        }
    }

    public void testGetNextRange_0_1() {
        min = 0;
        max = 1;
        Generatore g = new Generatore(a, seed1, b, min, max);
        ArrayList<Double> l = new ArrayList<Double>();
        double tmp = g.getNextRange();
        while (!l.contains(tmp)) {
            l.add(tmp);
            assertTrue(tmp > min && tmp < 50);
            tmp = g.getNextRange();
        }
    }

    public void testGetNextExp() {
        Generatore g = new Generatore(a, seed1, b, avg);
        ArrayList<Double> l = new ArrayList<Double>();
        double tmp = g.getNextExp();
        while (!l.contains(tmp)) {
            l.add(tmp);
            tmp = g.getNextExp();
        }
        assertTrue(Stats.media(l) < 23.0);
        assertTrue(Stats.sd(l) < (1.2 * Math.pow(avg, 2.0)));
    }

    public void testGetNextHyperExp() {
        a = 61;
        seed1 = 1;
        seed2 = 15;
        b = 12;
        Generatore g = new Generatore(a, seed1, seed2, b, avg, p);
        ArrayList<Double> l = new ArrayList<Double>();
        double tmp = g.getNextHyperExp();
        while (!l.contains(tmp)) {
            l.add(tmp);
            tmp = g.getNextHyperExp();
        }
        assertTrue(Stats.media(l) < 23.0);
        assertTrue(Stats.sd(l) < (1.2 * Math.pow(avg, 2.0)));
    }

}