package it.unimarconi.commons;

import java.util.List;

public class ChiQuadro {

    public static double Z25 = -0.674;

    public static double CHI_25;

    public static double Z75 = 0.674;

    public static double CHI_75;

    public static double calcolaV(List<Double> yss, double n, double ps) {
        double v = 0.0;
        double nps = n * ps;
        for (int i = 0 ; i < yss.size() ; i++)
            v += (Math.pow(yss.get(i) - nps, 2) / nps);
        return v;
    }

    public static double calcolaChiQuadro(double df, double za) {
        double a = 1.0;
        double b = 2.0 / (9.0 * df);
        double c = za * Math.sqrt(2.0 / (9.0 * df));
        double abc = a - b + c;
        double cube = Math.pow(abc, 3);
        return (df * cube);
    }

}