package it.unimarconi.commons;

import junit.framework.TestCase;

public class GCMTest extends TestCase {

    public void testGetNext() {
        int a = 5;
        int b = 5;
        int seed = 1;
        GCM gcm = new GCM(a, seed, b);
        System.out.println("seed: " + seed);
        double tmp = gcm.getNext();
        while (tmp != seed) {
            System.out.println(tmp);
            tmp = gcm.getNext();
        }
    }

    public void testGetNextRi() {
        int a = 5;
        int b = 5;
        double seed = 1.0;
        GCM gcm = new GCM(a, seed, b);
        System.out.println("seed: " + (seed / Math.pow(2.0, b)));
        double tmp = gcm.getNextRi();
        while (tmp != (seed / Math.pow(2.0, b))) {
            System.out.println(tmp);
            tmp = gcm.getNextRi();
        }
    }

}