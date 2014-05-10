package it.unimarconi.commons;

public class GCM {

    private int a;

    private double m;

    private int b;

    private double seed;

    /**
     * @param a    Parametro a del generatore congruente moltiplicativo
     * @param seed Seme iniziale X0 per il generatore congruente moltiplicativo
     * @param b    Potenza di 2 per generare il modulo m = 2 ^b
     */
    public GCM(int a, double seed, int b) {
        this.setA(a);
        this.setB(b);
        this.setSeed(seed);
        this.setM(Math.pow(2.0, this.getB()));
    }

    /**
     * Restituisce un valore intero generato tramite il generatore congruente moltiplicativo
     * <p/>
     * @return Valore generato tramite il generatore congruente moltiplicativo
     */
    public double getNext() {
        double seed = (this.getA() * this.getSeed()) % this.getM();
        this.setSeed(seed);
        return this.getSeed();
    }

    /**
     * Restituisce un valore compreso tra 0 ed 1 generato tramite il generatore congruente moltiplicativo
     * <p/>
     * @return Valore compreso tra 0 ed 1 generato tramite il generatore congruente moltiplicativo
     */
    public double getNextRi() {
        double seed = (this.getA() * this.getSeed()) % this.getM();
        this.setSeed(seed);
        return this.getSeed() / this.getM();
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public double getM() {
        return m;
    }

    public void setM(double m) {
        this.m = m;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public double getSeed() {
        return seed;
    }

    public void setSeed(double seed) {
        this.seed = seed;
    }

    @Override
    public String toString() {
        return "GCM{" +
                "a=" + a +
                ", m=" + m +
                ", b=" + b +
                ", seed=" + seed +
                '}';
    }

}