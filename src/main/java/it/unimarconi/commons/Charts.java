package it.unimarconi.commons;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Charts {

    private GCM gcm1;

    private GCM gcm2;

    private int a;

    private long seed;

    private int b;

    private double min;

    private double max;

    private long seed1;

    private long seed2;

    private int avg;

    private double p;

    private ArrayList<Double> sequenza;

    private int numeroClassi;

    private ArrayList<Integer> classi;

    private ArrayList<Double> frequenzeRelative;

    private DecimalFormat df = new DecimalFormat("#0.0000");

    private DecimalFormat df2 = new DecimalFormat("#0.00");

    public Charts(int a, long seed, int b, double min, double max, int numeroClassi) {
        this.setA(a);
        this.setSeed1(seed);
        this.setB(b);
        this.setGcm1(new GCM(this.getA(), this.getSeed1(), this.getB()));
        this.setMin(min);
        this.setMax(max);
        this.setNumeroClassi(numeroClassi);
        this.setSequenza(new ArrayList<Double>());
        this.setClassi(new ArrayList<Integer>());
        this.setFrequenzeRelative(new ArrayList<Double>());
    }

    public Charts(int a, long seed, int b, int avg, int numeroClassi) {
        this.setA(a);
        this.setSeed1(seed);
        this.setB(b);
        this.setGcm1(new GCM(this.getA(), this.getSeed1(), this.getB()));
        this.setAvg(avg);
        this.setNumeroClassi(numeroClassi);
        this.setSequenza(new ArrayList<Double>());
        this.setClassi(new ArrayList<Integer>());
        this.setFrequenzeRelative(new ArrayList<Double>());
    }

    public Charts(int a, long seed1, long seed2, int b, int avg, double p, int numeroClassi) {
        this.setA(a);
        this.setSeed1(seed1);
        this.setSeed2(seed2);
        this.setP(p);
        this.setB(b);
        this.setGcm1(new GCM(this.getA(), this.getSeed1(), this.getB()));
        this.setAvg(avg);
        this.setNumeroClassi(numeroClassi);
        this.setSequenza(new ArrayList<Double>());
        this.setClassi(new ArrayList<Integer>());
        this.setFrequenzeRelative(new ArrayList<Double>());
    }

    public void generaChartsRn() {
        double ri = this.getGcm1().getNextRi();
        while (!this.getSequenza().contains(ri)) {
            this.getSequenza().add(ri);
            ri = this.getGcm1().getNextRi();
        }
        List<String> soglie = generaSoglie();
        List<Integer> classi = generaClassi();
        List<Double> frequenzeRelative = generaFrequenzeRelative();
        List<Double> densita = generaDensita();
        List<Double> cumulate = generaCumulate();
        print(soglie, classi, frequenzeRelative, densita, cumulate);
    }

    public void generaChartsSequenzaUniforme() {
        Generatore g = new Generatore(this.getA(), this.getSeed1(), this.getB(), this.getMin(), this.getMax());
        double ri = g.getNextRange();
        while (!this.getSequenza().contains(ri)) {
            this.getSequenza().add(ri);
            ri = g.getNextRange();
        }
        List<String> soglie = generaSoglie();
        List<Integer> classi = generaClassi();
        List<Double> frequenzeRelative = generaFrequenzeRelative();
        List<Double> densita = generaDensita();
        List<Double> cumulate = generaCumulate();
        print(soglie, classi, frequenzeRelative, densita, cumulate);
    }

    public void generaChartsExp() {
        Generatore g = new Generatore(this.getA(), this.getSeed1(), this.getB(), this.getAvg());
        double ri = g.getNextExp();
        while (!this.getSequenza().contains(ri)) {
            this.getSequenza().add(ri);
            ri = g.getNextExp();
        }
        this.setMin(Stats.min(this.getSequenza()));
        this.setMax(Stats.max(this.getSequenza()));
        List<String> soglie = generaSoglie();
        List<Integer> classi = generaClassi();
        List<Double> frequenzeRelative = generaFrequenzeRelative();
        List<Double> densita = generaDensita();
        List<Double> cumulate = generaCumulate();
        print(soglie, classi, frequenzeRelative, densita, cumulate);
    }

    public void generaChartsHyperExp() {
        Generatore g = new Generatore(this.getA(), this.getSeed1(), this.getSeed2(), this.getB(), this.getAvg(), this.getP());
        double ri = g.getNextHyperExp();
        while (!this.getSequenza().contains(ri)) {
            this.getSequenza().add(ri);
            ri = g.getNextHyperExp();
        }
        this.setMin(Stats.min(this.getSequenza()));
        this.setMax(Stats.max(this.getSequenza()));
        List<String> soglie = generaSoglie();
        List<Integer> classi = generaClassi();
        List<Double> frequenzeRelative = generaFrequenzeRelative();
        List<Double> densita = generaDensita();
        List<Double> cumulate = generaCumulate();
        print(soglie, classi, frequenzeRelative, densita, cumulate);
    }

    private List<String> generaSoglie() {
        List<String> l = new ArrayList<String>();
        double step = (this.getMax() - this.getMin()) / this.getNumeroClassi();
        for (int i = 0 ; i < this.getNumeroClassi() - 1; i++)
            l.add("'" + (df2.format(this.getMin() + i * step)) + " to " + df2.format((this.getMin() + (1 + i) * step)) + "'");
        l.add("'" + (df2.format(this.getMin() + (this.getNumeroClassi() - 1) * step) + " to " + df2.format(this.getMax())) + "'");
        return l;
    }

    private List<Integer> generaClassi() {
        for (int i = 0 ; i < this.getNumeroClassi() ; i++)
            this.getClassi().add(0);
        double step = (this.getMax() - this.getMin()) / this.getNumeroClassi();
        for (int i = 0 ; i < this.getSequenza().size() ; i++) {
            Double d = this.getSequenza().get(i);
            int classe = (int) ((d / step) - (this.getMin() / 2.0));
            if (classe >= this.getNumeroClassi())
                classe = this.getNumeroClassi() - 1;
            this.getClassi().set(classe, 1 + this.getClassi().get(classe));
        }
        return this.getClassi();
    }

    private List<Double> generaFrequenzeRelative() {
        for (Integer classe : this.getClassi())
            this.getFrequenzeRelative().add(1.0 * classe / this.getSequenza().size());
        return this.getFrequenzeRelative();
    }

    private List<Double> generaDensita() {
        List<Double> l = new ArrayList<Double>();
        double step = (this.getMax() - this.getMin()) / this.getNumeroClassi();
        for (Double fr : this.getFrequenzeRelative())
            l.add(fr / step);
        return l;
    }

    private List<Double> generaCumulate() {
        List<Double> l = new ArrayList<Double>();
        double sum = 0.0;
        for (Double fr : this.getFrequenzeRelative()) {
            sum += fr;
            l.add(sum);
        }
        return l;
    }

    public void print(List<String> soglie, List<Integer> classi, List<Double> frequenzeRelative, List<Double> densita, List<Double> cumulate) {
        String s1 = "Soglie: [";
        String s2 = "Classi: [";
        String s3 = "Frequenze Relative: [";
        String s4 = "Densita: [";
        String s5 = "Cumulate: [";
        for (int i = 0 ; i < this.getNumeroClassi() ; i++) {
            s1 += soglie.get(i);
            s2 += df.format(classi.get(i));
            s3 += df.format(frequenzeRelative.get(i));
            s4 += df.format(densita.get(i));
            s5 += df.format(cumulate.get(i));
            if (i < this.getNumeroClassi() - 1) {
                s1 += ", ";
                s2 += ", ";
                s3 += ", ";
                s4 += ", ";
                s5 += ", ";
            }
        }
        s1 += "]";
        s2 += "]";
        s3 += "]";
        s4 += "]";
        s5 += "]";
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        System.out.println(s4);
        System.out.println(s5);
    }

    public GCM getGcm1() {
        return gcm1;
    }

    public void setGcm1(GCM gcm1) {
        this.gcm1 = gcm1;
    }

    public GCM getGcm2() {
        return gcm2;
    }

    public void setGcm2(GCM gcm2) {
        this.gcm2 = gcm2;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public long getSeed() {
        return seed;
    }

    public void setSeed(long seed) {
        this.seed = seed;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
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

    public long getSeed1() {
        return seed1;
    }

    public void setSeed1(long seed1) {
        this.seed1 = seed1;
    }

    public long getSeed2() {
        return seed2;
    }

    public void setSeed2(long seed2) {
        this.seed2 = seed2;
    }

    public int getAvg() {
        return avg;
    }

    public void setAvg(int avg) {
        this.avg = avg;
    }

    public double getP() {
        return p;
    }

    public void setP(double p) {
        this.p = p;
    }

    public ArrayList<Double> getSequenza() {
        return sequenza;
    }

    public void setSequenza(ArrayList<Double> sequenza) {
        this.sequenza = sequenza;
    }

    public int getNumeroClassi() {
        return numeroClassi;
    }

    public void setNumeroClassi(int numeroClassi) {
        this.numeroClassi = numeroClassi;
    }

    public ArrayList<Integer> getClassi() {
        return classi;
    }

    public void setClassi(ArrayList<Integer> classi) {
        this.classi = classi;
    }

    public ArrayList<Double> getFrequenzeRelative() {
        return frequenzeRelative;
    }

    public void setFrequenzeRelative(ArrayList<Double> frequenzeRelative) {
        this.frequenzeRelative = frequenzeRelative;
    }

}