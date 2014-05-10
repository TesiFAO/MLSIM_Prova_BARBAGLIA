package it.unimarconi.commons;

import junit.framework.TestCase;

import java.util.ArrayList;

public class GCMTest extends TestCase {

    private int a = 5;

    private int b = 5;

    private int seed = 1;

    /**
     * Il test verifica che i valori generati dal metodo
     * <code>getNext()</code> siano compresi tra 0 ed il modulo
     */
    public void testGetNext() {
        GCM gcm = new GCM(a, seed, b);
        double tmp = gcm.getNext();
        while (tmp != seed) {
            assertTrue(tmp > 0 && tmp < Math.pow(2.0, b));
            tmp = gcm.getNext();
        }
    }

    /**
     * Il test verifica che i valori generati dal metodo
     * <code>getNextRi()</code> siano compresi tra 0 ed 1
     */
    public void testGetNextRi() {
        GCM gcm = new GCM(a, seed, b);
        double tmp = gcm.getNextRi();
        while (tmp != (seed / Math.pow(2.0, b))) {
            assertTrue(tmp > 0 && tmp < 1);
            tmp = gcm.getNextRi();
        }
    }

    /**
     * Il test verifica che i valori generati dal metodo
     * <code>getNext()</code> appartengano ad una delle sequenze previste
     * dal Corollario A, ovvero 4v + 1 oppure 4v + 3, con v compreso tra 0 e pow(2, b-2)-1
     */
    public void testCorollario() {
        GCM gcm = new GCM(a, seed, b);
        double[] c1 = gcm.generate4v1();
        double[] c2 = gcm.generate4v3();
        ArrayList<Double> l1 = Utils.toList(c1);
        ArrayList<Double> l2 = Utils.toList(c2);
        double tmp = gcm.getNext();
        while (tmp != seed) {
            assertTrue(l1.contains(tmp) || l2.contains(tmp));
            tmp = gcm.getNext();
        }
    }

}