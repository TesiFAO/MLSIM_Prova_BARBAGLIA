package it.unimarconi.test;

import it.unimarconi.commons.ChiQuadro;
import it.unimarconi.commons.GCM;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

public class Uniformita {

    private int a;

    private int seed;

    private int b;

    private int d;

    private int prove;

    private GCM gcm;

    public Uniformita(int a, int seed, int b, int d, int prove) {
        this.setA(a);
        this.setSeed(seed);
        this.setB(b);
        this.setD(d);
        this.setProve(prove);
        this.setGcm(new GCM(this.getA(), this.getSeed(), this.getB()));
    }

    public boolean[] eseguiTest() {

        boolean[] out = new boolean[this.getProve()];

        double min = ChiQuadro.calcolaAlphaPercentile(this.getD() - 1, ChiQuadro.Z25);
        double max = ChiQuadro.calcolaAlphaPercentile(this.getD() - 1, ChiQuadro.Z75);

        List<Double> ds = new ArrayList<Double>();

        for (int i = 0 ; i < (int)Math.pow(2.0, this.getB() - 2.0) ; i++) {
            double ri = this.getGcm().getNextRi();
            double zi = Math.floor(1.0 * this.getD() * ri);
            ds.add(zi);
        }

        List<List<Double>> testSet = new ArrayList<List<Double>>();
        double size = 1.0 * ds.size() / this.getProve();
        for (int i = 0 ; i < this.getProve() ; i++) {
            List<Double> chunk = new ArrayList<Double>();
            for (int j = i * (int)size ; j < (i + 1) * (int)size ; j++)
                chunk.add(ds.get(j));
            testSet.add(chunk);
        }

        for (int k = 0 ; k < this.getProve() ; k++) {
            Collections.sort(testSet.get(k));
            LinkedHashMap<Double, Double> freqs = new LinkedHashMap<Double, Double>();
            for (Double tmp : testSet.get(k)) {
                try {
                    freqs.put(tmp, 1.0 + freqs.get(tmp));
                } catch (Exception e) {
                    if (tmp <= this.getD())
                        freqs.put(tmp, 1.0);
                }
            }
            List<Double> yss = new ArrayList<Double>();
            for (Double freq : freqs.values())
                yss.add(freq);
            double v = ChiQuadro.calcolaV(yss, size, (1.0 / this.getD()));
//            System.out.println(min + " < " + v + " < " + max + " ?");
            if (v > min && v < max) {
                out[k] = true;
            } else {
                out[k] = false;
            }
        }

        int count = 0;
        for (int i = 0 ; i < out.length ; i++)
            if (out[i])
                count++;

        System.out.print("Test di Uniformita: [a: " + this.getA() + "]");
        System.out.print("[seed: " + this.getSeed() + "]");
        System.out.print("[b: " + this.getB() + "]");
        System.out.print("[d: " + this.getD() + "]\t\t");
        System.out.println(count + " successi su " + this.getProve() + " prove.\n");

        return out;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getSeed() {
        return seed;
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public int getProve() {
        return prove;
    }

    public void setProve(int prove) {
        this.prove = prove;
    }

    public GCM getGcm() {
        return gcm;
    }

    public void setGcm(GCM gcm) {
        this.gcm = gcm;
    }

}