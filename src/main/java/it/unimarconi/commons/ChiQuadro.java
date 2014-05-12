package it.unimarconi.commons;

import java.util.List;

public class ChiQuadro {

    public static double Z25 = -0.674;

    public static double Z75 = 0.674;

    /**
     * Variabile V distribuita come chi-quadro con k-1 gradi di liberta
     *
     * @param yss   Lista di frequenze
     * @param n     Dimensione del vettore
     * @param ps    Probabilita teorica
     * @return      Valore di v
     */
    public static double calcolaV(List<Double> yss, double n, double ps) {
        double v = 0.0;
        double nps = n * ps;
        for (int i = 0 ; i < yss.size() ; i++)
            v += (Math.pow(yss.get(i) - nps, 2) / nps);
        return v;
    }

    /**
     * Calcola il valore dell'alpha percentile
     *
     * @param df    Gradi di liberta
     * @param za    Alpha percentile della distribuzione normale
     * @return
     */
    public static double calcolaAlphaPercentile(double df, double za) {
        double a = 1.0;
        double b = 2.0 / (9.0 * df);
        double c = za * Math.sqrt(2.0 / (9.0 * df));
        double abc = a - b + c;
        double cube = Math.pow(abc, 3);
        return (df * cube);
    }

}