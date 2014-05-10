package it.unimarconi.commons;

public class Generatore {

    private GCM gcm;

    private int a;

    private int b;

    private double seed1;

    private double seed2;

    private double min;

    private double max;

    private double avg;

    private double p;

    public Generatore(int a, double seed, int b, double min, double max) {
        this.setA(a);
        this.setSeed1(seed);
        this.setB(b);
        this.setGcm(new GCM(this.getA(), this.getSeed1(), this.getB()));
        this.setMin(min);
        this.setMax(max);
    }

    public Generatore(int a, double seed, int b, double avg) {
        this.setA(a);
        this.setSeed1(seed);
        this.setB(b);
        this.setGcm(new GCM(this.getA(), this.getSeed1(), this.getB()));
        this.setAvg(avg);
    }

    public Generatore(int a, double seed1, double seed2, int b, double avg, double p) {
        this.setA(a);
        this.setSeed1(seed1);
        this.setSeed2(seed2);
        this.setB(b);
        this.setP(p);
        this.setGcm(new GCM(this.getA(), this.getSeed1(), this.getB()));
        this.setAvg(avg);
    }

    public double getNextRange() {
        return this.getMin() + this.getGcm().getNextRi() * (this.getMax() - this.getMin());
    }

    public double getNextExp() {
        return -1.0 * this.getAvg() * Math.log(this.getGcm().getNextRi());
    }

    public double getNextExp(double avg) {
        return -1.0 * avg * Math.log(this.getGcm().getNextRi());
    }

    public double getNextHyperExp() {
        GCM gcm1 = new GCM(this.getA(), this.getSeed1(), this.getB());
        double ri = gcm1.getNextRi();
        double xi = getNextExp(1.0);
        if (ri <= this.getP())
            return xi * (this.getAvg() / (2.0 * this.getP()));
        else
            return xi * (this.getAvg() / (2.0 * (1.0 - this.getP())));
    }

    public GCM getGcm() {
        return gcm;
    }

    public void setGcm(GCM gcm) {
        this.gcm = gcm;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public double getSeed1() {
        return seed1;
    }

    public void setSeed1(double seed1) {
        this.seed1 = seed1;
    }

    public double getSeed2() {
        return seed2;
    }

    public void setSeed2(double seed2) {
        this.seed2 = seed2;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    public double getP() {
        return p;
    }

    public void setP(double p) {
        this.p = p;
    }

    @Override
    public String toString() {
        return "Generatore{" +
                "gcm=" + gcm +
                ", a=" + a +
                ", b=" + b +
                ", seed1=" + seed1 +
                ", seed2=" + seed2 +
                ", min=" + min +
                ", max=" + max +
                ", avg=" + avg +
                ", p=" + p +
                '}';
    }

}