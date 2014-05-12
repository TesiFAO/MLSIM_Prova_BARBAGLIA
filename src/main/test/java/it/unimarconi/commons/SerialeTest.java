package it.unimarconi.commons;

import it.unimarconi.test.Seriale;
import it.unimarconi.test.Uniformita;
import junit.framework.TestCase;

public class SerialeTest extends TestCase{

    private int a = 10037;

    private int seed = 161;

    private int b = 19;

    private int d = 64;

    private int prove = 3;

    public void testSeriale() {
        Seriale u = new Seriale(a, seed, b, d, prove);
        u.eseguiTest();
    }

}