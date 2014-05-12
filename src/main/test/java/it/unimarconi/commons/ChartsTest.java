package it.unimarconi.commons;

import junit.framework.TestCase;

public class ChartsTest extends TestCase {

    private int a = 5;

    private int b = 12;

    private int seed = 1;

    private int numeroClassi = 10;

    private double min = 0.0;

    private double max = 1.0;

    public void testGeneraChartsRn() {
        Charts c = new Charts(a, seed, b, min, max, numeroClassi);
        c.generaChartsRn();
    }

}