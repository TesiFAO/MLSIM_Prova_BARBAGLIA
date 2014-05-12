package it.unimarconi.test;

import it.unimarconi.commons.ChiQuadro;
import it.unimarconi.commons.GCM;

import java.util.ArrayList;
import java.util.List;

public class Seriale {

    private int a;

    private int seed;

    private int b;

    private int d;

    private int prove;

    private GCM gcm;

    public Seriale(int a, int seed, int b, int d, int prove) {
        this.setA(a);
        this.setSeed(seed);
        this.setB(b);
        this.setD(d);
        this.setProve(prove);
        this.setGcm(new GCM(this.getA(), this.getSeed(), this.getB()));
    }

    public void eseguiTest() {



        double min = ChiQuadro.calcolaAlphaPercentile(Math.pow(this.getD(), 2) - 1, ChiQuadro.Z25);
        double max = ChiQuadro.calcolaAlphaPercentile(Math.pow(this.getD(), 2) - 1, ChiQuadro.Z75);

        List<Integer> ds = new ArrayList<Integer>();

        for (int i = 0 ; i < (int)Math.pow(2.0, this.getB() - 2.0) ; i++) {
            double ri = this.getGcm().getNextRi();
            double zi = Math.floor(1.0 * this.getD() * ri);
            ds.add((int)zi);
        }

        int[][] matrix = new int[this.getD()][this.getD()];
        for (int i = 0 ; i < matrix.length ; i++)
            for (int j = 0 ; j < matrix[i].length ; j++)
                matrix[i][j] = 0;

        int length = (int)(Math.pow(2.0, (this.getB() - 2.0)));
        int subLength = length / 3;

        for (int startIDX = 0 ; startIDX < 2 ; startIDX ++) {

            boolean[] out = new boolean[this.getProve()];

            for (int k = 0; k < this.getProve(); k++) {
                List<Integer> tmp = new ArrayList<Integer>();
                for (int i = k * subLength; i < (1 + k) * subLength; i++) {
                    tmp.add(ds.get(i));
                }
                if (k == this.getProve() - 1) {
                    for (int i = (1 + k) * subLength; i < ds.size(); i++)
                        tmp.add(ds.get(i));
                }

                double v = subSeriale(tmp, startIDX);
                if (v < max && v > min)
                    out[k] = true;
                else
                    out[k] = false;
//                System.out.println(tmp.size() + " elements, startIDX: " + startIDX + " ---> " + min + " < " + v + " < " + max);
            }

            int count = 0;
            for (int i = 0 ; i < out.length ; i++)
                if (out[i])
                    count++;

            System.out.print("Test di Seriale: [a: " + this.getA() + "]");
            System.out.print("[seed: " + this.getSeed() + "]");
            System.out.print("[b: " + this.getB() + "]");
            System.out.print("[d: " + this.getD() + "]\t\t");
            System.out.println(count + " successi su " + this.getProve() + " prove.\n");

        }



    }

    public double subSeriale(List<Integer> zs, int startIdx) {

//        System.out.println("SUB-SEQUENCE: " + zs.size());

        int[][] matrix = new int[this.getD()][this.getD()];
        for (int i = 0 ; i < matrix.length ; i++)
            for (int j = 0 ; j < matrix[i].length ; j++)
                matrix[i][j] = 0;

        for (int i = startIdx ; i < zs.size() - 1; i += 2) {
            int old = matrix[zs.get(i)][zs.get(1 + i)];
            matrix[zs.get(i)][zs.get(1 + i)] = 1 + old;
        }

        List<Double> freqs = new ArrayList<Double>();
        for (int i = 0 ; i < matrix.length ; i++)
            for (int j = 0; j < matrix[i].length; j++)
                freqs.add(new Double(matrix[i][j]));


        return ChiQuadro.calcolaV(freqs, (zs.size() / 2), (1.0 / (this.getD() * this.getD())));
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