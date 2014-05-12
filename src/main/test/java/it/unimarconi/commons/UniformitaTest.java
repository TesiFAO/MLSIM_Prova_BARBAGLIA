package it.unimarconi.commons;

import junit.framework.TestCase;

public class UniformitaTest extends TestCase{

    private int a = 10037;

    private int seed = 161;

    private int b = 19;

    private int d = 64;

    private int prove = 3;

    public void testUniformita() {
        Uniformita u = new Uniformita(a, seed, b, d, prove);
        boolean[] out = u.eseguiTest();
        for (int i = 0 ; i < out.length ; i++)
            System.out.println("Test 1: " + out[i]);
    }

}